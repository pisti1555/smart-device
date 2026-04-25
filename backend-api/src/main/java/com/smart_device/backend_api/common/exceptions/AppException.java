package com.smart_device.backend_api.common.exceptions;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class AppException extends RuntimeException {
    private final int statusCode;
    private final String title;
    private final String errorMessage;
    private final String timestamp;

    public AppException(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.title = GenerateTitle(statusCode);
        this.errorMessage = errorMessage;
        this.timestamp = Instant.now().toString();
    }

    private static String GenerateTitle(int statusCode) {
        return switch(statusCode) {
            // 4xx - Client errors
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 402 -> "Payment Required";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 406 -> "Not Acceptable";
            case 407 -> "Proxy Authentication Required";
            case 408 -> "Request Timeout";
            case 409 -> "Conflict";
            case 410 -> "Gone";
            case 422 -> "Unprocessable Entity";
            case 429 -> "Too Many Requests";

            // 5xx - Server errors
            case 500 -> "Internal Server Error";
            case 502 -> "Bad Gateway";
            case 503 -> "Service Unavailable";
            case 504 -> "Gateway Timeout";

            // Default
            default -> "Error";
        };
    }
}