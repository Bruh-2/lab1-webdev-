package org.example.service;

import org.example.exception.RateAlreadyExistsException;
import org.example.exception.RateNotFoundException;
import org.example.repository.RateRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RateService {

    private final RateRepository repository;
    private final Random random = new Random();

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

        Double rate = repository.get(currency);

        if (rate == null) {
            throw new RateNotFoundException("Rate not found: " + currency);
        }

        return rate;
    }

    public void addRate(String currency, Double rate) {

        if (repository.get(currency) != null) {
            throw new RateAlreadyExistsException("Rate already exists: " + currency);
        }

        repository.save(currency, rate);
    }

    public void updateRate(String currency, Double rate) {

        if (repository.get(currency) == null) {
            throw new RateNotFoundException("Rate not found: " + currency);
        }

        repository.save(currency, rate);
    }

    public void deleteRate(String currency) {

        if (repository.get(currency) == null) {
            throw new RateNotFoundException("Rate not found: " + currency);
        }

        repository.delete(currency);
    }

    public boolean shouldThrowRandomError() {
        return random.nextBoolean();
    }
}
