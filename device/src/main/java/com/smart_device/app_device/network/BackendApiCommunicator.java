package com.smart_device.app_device.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart_device.app_device._device.authentication.Authentication;
import com.smart_device.app_device.models.common.AppResult;
import com.smart_device.app_device.models.common.errors.AppError;
import com.smart_device.app_device.models.common.errors.ErrorModel;
import com.smart_device.app_device.models.common.errors.ValidationErrorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BackendApiCommunicator implements Communicator {
    private final String BASE_URL = "http://localhost:8080/api";

    private final Authentication authentication;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    @Autowired
    public BackendApiCommunicator(Authentication authentication, ObjectMapper mapper) {
        this.authentication = authentication;
        httpClient = HttpClient.newHttpClient();
        this.mapper = mapper;
    }

    @Override
    public <ReturnType> AppResult<ReturnType> GET(String endpoint, TypeReference<ReturnType> type) {
        HttpRequest httpRequest = newRequest(endpoint).GET().build();
        return send(httpRequest, type);
    }

    @Override
    public <RequestType, ReturnType> AppResult<ReturnType> POST(String endpoint, RequestType object, TypeReference<ReturnType> type) {
        try {
            HttpRequest httpRequest = newRequest(endpoint)
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(object)))
                    .build();
            return send(httpRequest, type);
        } catch (JsonProcessingException e) {
            return AppResult.error("Could not create request body from given object.");
        }
    }

    @Override
    public <RequestType, ReturnType> AppResult<ReturnType> PUT(String endpoint, RequestType object, TypeReference<ReturnType> type) {
        try {
            HttpRequest httpRequest = newRequest(endpoint)
                    .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(object)))
                    .build();

            return send(httpRequest, type);
        } catch (JsonProcessingException e) {
            return AppResult.error("Could not create request body from given object.");
        }
    }

    @Override
    public <ReturnType> AppResult<ReturnType> DELETE(String endpoint, TypeReference<ReturnType> type) {
        HttpRequest httpRequest = newRequest(endpoint).DELETE().build();
        return send(httpRequest, type);
    }



    // ------  Helper methods  ------

    private HttpRequest.Builder newRequest(String endpoint) {
        var builder = HttpRequest.newBuilder(URI.create(BASE_URL + endpoint))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");

        if (authentication.isAuthenticated()) {
            builder.header("Authorization", ("Basic " + authentication.getCredentials().getBasicAuthToken()));
        }

        return builder;
    }

    private <ReturnType> AppResult<ReturnType> send(HttpRequest request, TypeReference<ReturnType> type) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (isError(response)) {
                JsonNode jsonRoot = mapper.readTree(response.body());
                return handleError(jsonRoot);
            }

            if (response.body() == null || response.body().isBlank()) {
                return AppResult.success(null);
            }

            ReturnType result = mapper.readValue(response.body(), type);

            return AppResult.success(result);
        } catch (ConnectException ex) {
            return AppResult.error("Backend service is unavailable.");
        } catch (Exception ex) {
            return AppResult.error("An unexpected error occurred.");
        }
    }

    private boolean isError(HttpResponse<?> response) {
        return response.statusCode() < 200 || response.statusCode() > 299;
    }

    private <T> AppResult<T> handleError(JsonNode jsonRoot) {
        try {
            boolean isValidationError = jsonRoot.has("errors") && !jsonRoot.get("errors").isNull();

            AppError error = isValidationError ?
                    mapper.treeToValue(jsonRoot, ValidationErrorModel.class) :
                    mapper.treeToValue(jsonRoot, ErrorModel.class);

            return AppResult.error(error);
        } catch (JsonProcessingException e) {
            return AppResult.error("Error processing HTTP response body.");
        }
    }
}
