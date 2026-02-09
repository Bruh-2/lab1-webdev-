package org.example.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiResponse<T> {

    private T data;
    private int count;
    private LocalDateTime date;

    public ApiResponse(T data) {
        this.data = data;
        this.date = LocalDateTime.now();

        if (data instanceof Map) {
            this.count = ((Map<?, ?>) data).size();
        } else {
            this.count = 1;
        }
    }

    public T getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
