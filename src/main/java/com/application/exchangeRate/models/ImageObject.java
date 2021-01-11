package com.application.exchangeRate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageObject {
    private Map<String, String> original;
}
