package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RateRepository {

    private Map<String, Double> rates = new HashMap<>();

    public Map<String, Double> getAll() {
        return rates;
    }

    public Double get(String currency) {
        return rates.get(currency);
    }

    public void save(String currency, Double rate) {
        rates.put(currency, rate);
    }

    public void delete(String currency) {
        rates.remove(currency);
    }
}
