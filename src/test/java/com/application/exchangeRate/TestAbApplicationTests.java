package com.application.exchangeRate;

import com.application.exchangeRate.clients.ExchangeClient;
import com.application.exchangeRate.models.Exchange;
import com.application.exchangeRate.services.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TestAbApplicationTests {
	@Value("${openexchangeratesAppId}")
	private String app_id;

	@MockBean
	private ExchangeClient exchangeClient;

	@Autowired
	private CurrencyService currencyService;
	private String todayExchange;
	private String yesterdayExchange;
	Map<String, Double> todayRates;
	Map<String, Double> yesterdayRates;


	@BeforeEach
	public void init() {
		todayExchange = new SimpleDateFormat("yyyy-MM-dd")
				.format(Calendar.getInstance().getTime());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		yesterdayExchange = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime());

		todayRates = new HashMap<>();
		todayRates.put("AED",2.0);
		todayRates.put("AFN",1.0);
		todayRates.put("RUB",1.0);

		yesterdayRates = new HashMap<>();
		yesterdayRates.put("AED",1.0);
		yesterdayRates.put("AFN",2.0);
		yesterdayRates.put("RUB",1.0);
	}
	@Test
	void ifRubleExchangeRateGreaterThanYesterdayTest() {
		Mockito.when(exchangeClient.getExchangeCourse(todayExchange, app_id))
				.thenReturn(new Exchange(todayRates));
		Mockito.when(exchangeClient.getExchangeCourse(yesterdayExchange, app_id))
				.thenReturn(new Exchange(yesterdayRates));

		boolean uncorrectly = currencyService.ifRubleExchangeRateGreaterThanYesterday("AED");
		boolean correctly = currencyService.ifRubleExchangeRateGreaterThanYesterday("AFN");


		assertFalse(uncorrectly);
		assertTrue(correctly);

	}
}
