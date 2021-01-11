package com.application.exchangeRate.services;

import com.application.exchangeRate.clients.ExchangeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
public class CurrencyService {

    @Value("${openexchangeratesAppId}")
    private String app_id;
    private String baseCurrencyCode = "RUB";

    private ExchangeClient exchangeClient;

    public CurrencyService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public boolean ifRubleExchangeRateGreaterThanYesterday(String Code){
        double todayExchange = getTodayExchange(Code);
        double yesterdayExchange = getYesterdayExchange(Code);
        return todayExchange > yesterdayExchange;
    }

    private double getTodayExchange(String Code){
        String Date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Map<String, Double> rates = exchangeClient.getExchangeCourse(Date, app_id).getRates();
        return rates.get(baseCurrencyCode) / rates.get(Code);
    }

    private double getYesterdayExchange(String Code){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String Date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Map<String, Double> rates = exchangeClient.getExchangeCourse(Date, app_id).getRates();
        return rates.get(baseCurrencyCode) / rates.get(Code);
    }
}
