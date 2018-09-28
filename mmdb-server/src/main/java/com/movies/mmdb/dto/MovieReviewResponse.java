package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Objects;

/**
 * <code>MovieReviewResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.MovieReview}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.MovieReview}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.MovieReview
 * @since 1.0
 */
public class MovieReviewResponse {

    private Integer rating;
    private String title;
    private String text;
    private Date createdAt;
    private Date updatedAt;
    private UserResponse user;
    private MovieResponse movie;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public MovieResponse getMovie() {
        return movie;
    }

    public void setMovie(MovieResponse movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieReviewResponse that = (MovieReviewResponse) o;
        return Objects.equals(rating, that.rating) &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(user, that.user) &&
                Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, title, text, createdAt, user, movie);
    }
}
