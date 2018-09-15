package com.movies.mmdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handle the bad request exception, if the endpoint url is invalid this class will be thrown with the
 * appropriate message.
 * <p>
 * The specific response status return is <code>BAD_REQUEST</code>
 * @author Ayoub Khial
 * @version 1.0
 * @see HttpStatus
 * @see ResponseStatus
 * @see RuntimeException
 * @since 1.0
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid parameters")
public class BadRequestException extends RuntimeException {

    /**
     * Throw with appropriate message
     * @param message The message of the exception
     */
    public BadRequestException(String message) {
        super(message);
    }
}
