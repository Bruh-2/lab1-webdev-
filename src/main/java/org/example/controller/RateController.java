package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.ApiResponse;
import org.example.dto.RateRequest;
import org.example.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateService service;

    public RateController(RateService service) {
        this.service = service;
    }

    // GET /rates?filter=B
    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Double>>> getAllRates(
            @RequestParam(required = false) String filter,
            @RequestHeader("User-Agent") String userAgent
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(service.getAllRates(filter))
        );
    }

    // GET /rates/{currency}
    @GetMapping("/{currency}")
    public ResponseEntity<ApiResponse<Double>> getRate(
            @PathVariable String currency
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(service.getRate(currency))
        );
    }

    // POST /rates
    @PostMapping
    public ResponseEntity<String> addRate(
            @Valid @RequestBody RateRequest request
    ) {
        service.addRate(request.getCurrency(), request.getRate());
        return ResponseEntity.ok("Rate added");
    }

    // PUT /rates/{currency}
    @PutMapping("/{currency}")
    public ResponseEntity<String> updateRate(
            @PathVariable String currency,
            @Valid @RequestBody RateRequest request
    ) {
        service.updateRate(currency, request.getRate());
        return ResponseEntity.ok("Rate updated");
    }

    // DELETE /rates/{currency}
    @DeleteMapping("/{currency}")
    public ResponseEntity<String> deleteRate(
            @PathVariable String currency
    ) {
        service.deleteRate(currency);
        return ResponseEntity.ok("Rate deleted");
    }
}
