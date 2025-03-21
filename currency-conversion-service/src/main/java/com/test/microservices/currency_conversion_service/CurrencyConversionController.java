package com.test.microservices.currency_conversion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private  CurrencyExchangeProxy currencyExchangeProxy;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        HashMap<String, String> map = new HashMap<>();
        map.put("from",from);
        map.put("to", to);

        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, map);

        CurrencyConversion currencyConversion = currencyConversionResponseEntity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        return currencyConversion;

    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(from, to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));
        return currencyConversion;

    }
}
