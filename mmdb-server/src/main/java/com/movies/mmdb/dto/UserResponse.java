package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <code>UserResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.User}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.User}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.User
 * @since 1.0
 */
public class UserResponse {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String picture;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
