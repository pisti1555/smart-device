package com.smart_device.app_device.network;

import com.fasterxml.jackson.core.type.TypeReference;
import com.smart_device.app_device.models.common.AppResult;

public interface Communicator {
    <ReturnType> AppResult<ReturnType> GET(String endpoint, TypeReference<ReturnType> type);
    <RequestType, ReturnType> AppResult<ReturnType> POST(String endpoint, RequestType object, TypeReference<ReturnType> type);
    <RequestType, ReturnType> AppResult<ReturnType> PUT(String endpoint, RequestType object, TypeReference<ReturnType> type);
    <ReturnType> AppResult<ReturnType> DELETE(String endpoint, TypeReference<ReturnType> type);
}
