package com.movies.mmdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handle the nt found exception, if the endpoint url is not exist this class will be thrown with the
 * appropriate message.
 * <p>
 * The specific response status return is <code>NOT_FOUND</code>
 * @author Ayoub Khial
 * @version 1.0
 * @see HttpStatus
 * @see ResponseStatus
 * @see RuntimeException
 * @since 1.0
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with {%s : '%s'}", resourceName, fieldName, fieldValue));
    }
}