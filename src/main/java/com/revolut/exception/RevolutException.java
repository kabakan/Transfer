package com.revolut.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception of RevolutTransfer
 * @author Kanat K.B.
 */
public class RevolutException extends Exception{
    private static final Logger LOGGER = LogManager.getLogger(RevolutException.class);
    private final String code;

    public String getCode() {
        return code;
    }

    public RevolutException(String message) {
        super(message);
        this.code = "unknown";
        LOGGER.error(getCode());
    }

    public RevolutException(String code, String message) {
        super(message);
        this.code = code;
        LOGGER.error(getCode());
    }

    public RevolutException(String message, Throwable cause) {
        super(message, cause);
        this.code = "unknown";
        LOGGER.error(getCode());
    }
}
