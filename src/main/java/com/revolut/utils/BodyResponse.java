package com.revolut.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Response body for API
 * @author Kanat K.B.
 */
@Getter
@Setter
@Component
public class BodyResponse {
    private String status;
    private String message;

    public BodyResponse set(String status, String message) {
        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setStatus(status);
        bodyResponse.setMessage(message);
        return bodyResponse;
    }
}
