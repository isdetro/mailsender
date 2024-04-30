package com.notificationmail.mail.model.helper;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class HttpResponse {
    String timeStamp;
    int statusCode;
    HttpStatus status;
    String message;
    String DeveloperMessage;
    String path;
    String requestMethod;
    Map<?,?> data;
}
