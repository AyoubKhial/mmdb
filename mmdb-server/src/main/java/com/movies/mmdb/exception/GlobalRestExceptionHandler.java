package com.movies.mmdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * interceptor of exceptions thrown by the controllers.
 * <p>
 * The <code>RestControllerAdvice</code> used to enable a single <code>ExceptionHandler</code> to be applied
 * to multiple controllers. This way we can in just one place define how to treat such an exception and this
 * handler will be called when the exception is thrown from classes that are covered by this ControllerAdvice.
 * <p>
 * When annotating a method with <code>ExceptionHandler</code>, it will accept a wide range of auto-injected
 * parameters like <code>WebRequest</code>, <code>Locale</code> and others. We’ll just provide the correspondent
 * exception itself as a parameter for this method.
 * @author Ayoub Khial
 * @version 1.0
 * @see RestControllerAdvice
 * @see ResponseEntityExceptionHandler
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This signalizes Spring that every time <code>BadRequestException</code> is thrown, Spring should call
     * this method to handle it.
     * @param badRequestException the exception we want to handle.
     * @return json format response.
     * @see BadRequestException
     * @see ExceptionHandler
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequest(BadRequestException badRequestException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDeveloperMessage(badRequestException.getMessage());
        response.setUserMessage("Page not found.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * This signalizes Spring that every time <code>ResourceNotFoundException</code> is thrown, Spring should call
     * this method to handle it.
     * @param resourceNotFoundException the exception we want to handle.
     * @return json format response.
     * @see ResourceNotFoundException
     * @see ExceptionHandler
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException resourceNotFoundException) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setDeveloperMessage(resourceNotFoundException.getMessage());
        response.setUserMessage("Page not found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}