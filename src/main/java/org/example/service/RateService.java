package org.example.service;

import org.example.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateService {

    private final RateRepository repository;

    public RateService(RateRepository repository) {
        this.repository = repository;
    }

    public Map<String, Double> getAllRates(String filter) {
        if (filter == null) {
            return repository.getAll();
        }

        Map<String, Double> result = new HashMap<>();
        for (String key : repository.getAll().keySet()) {
            if (key.startsWith(filter)) {
                result.put(key, repository.get(key));
            }
        }
        return result;
    }

    public Double getRate(String currency) {
        return repository.get(currency);
    }

    public void addRate(String currency, Double rate) {
        repository.save(currency, rate);
    }

    public void updateRate(String currency, Double rate) {
        repository.save(currency, rate);
    }

    public void deleteRate(String currency) {
        repository.delete(currency);
    }
}
