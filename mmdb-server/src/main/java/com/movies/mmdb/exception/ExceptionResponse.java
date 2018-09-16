package com.movies.mmdb.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * A class that has enough fields to hold relevant information about errors that happen during REST calls.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public class ExceptionResponse {

    private int code;
    private String developerMessage;
    private String userMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime time;

    public ExceptionResponse() {
        this.time = LocalDateTime.now();
    }

    /**
     * The <code>code</code> property holds the operation call code.
     * <p>
     * It will be anything from 4xx to signalize client errors or 5xx to mean server errors.
     * A common scenario is a http code 400 that means a BAD_REQUEST, when the client, for example,
     * sends an improperly formatted field, like an invalid movie rating.
     * @return the code of the exception
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * The <code>developerMessage</code> property holds a system message describing the error in more detail.
     * @return the developer message about the error
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    /**
     * The <code>userMessage</code> holds a user-friendly message about the error.
     * @return a message about the error
     */
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     * The <code>time</code> property holds the date-time instance of when the error happened.
     * @return the time when the exception thrown
     */
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}