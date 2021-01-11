package com.application.exchangeRate.models;

import lombok.Data;
import java.util.Map;

@Data
public class Exchange {
    private Map<String, Double> rates;

    public Exchange() {
    }
    public Exchange(Map<String, Double> rates) {
        this.rates = rates;
    }
}
