package com.application.exchangeRate.controllers;

import com.application.exchangeRate.services.CurrencyService;
import com.application.exchangeRate.services.GifService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    private CurrencyService currencyService;
    private GifService gifService;

    public CurrencyController(CurrencyService currencyService, GifService gifService) {
        this.currencyService = currencyService;
        this.gifService = gifService;
    }

    @GetMapping
    public ResponseEntity getGif(@RequestParam(name = "code") String Code) throws IOException {
            boolean ifRubleExchangeRateGreater = currencyService
                    .ifRubleExchangeRateGreaterThanYesterday(Code);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(gifService
                    .randomGif(ifRubleExchangeRateGreater));

    }
}