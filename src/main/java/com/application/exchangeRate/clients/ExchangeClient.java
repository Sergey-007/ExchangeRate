package com.application.exchangeRate.clients;

import com.application.exchangeRate.models.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange", url = "${APIBasePath}")
public interface ExchangeClient {
    @RequestMapping(value = "/historical/{date}.json", method = RequestMethod.GET, params = {"app_id"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    Exchange getExchangeCourse(@PathVariable String date, @RequestParam(value = "app_id") String app_id);
}
