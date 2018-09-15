package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * <code>MovieReview</code> class is a domain model class that contains the information of a single MovieReview entry
 * <p>
 * The entity which is represented by this class has a <b>unique constraint </b> on the following fields: <code>movie_id</code> and
 * <code>celebrity_id</code>
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
@Table(name = "movie_review", uniqueConstraints = @UniqueConstraint(columnNames={"movie_id", "user_id"}))
public class MovieReview extends DateAudit {

    private Long id;
    private Integer rating;
    private String title;
    private String text;
    private Movie movie;
    private User user;

    public MovieReview() {}

    public MovieReview(Long id, Integer rating, String title, String text) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(length = 150)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieReview that = (MovieReview) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, title, text, movie, user);
    }
}
