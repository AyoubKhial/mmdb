package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * <code>MovieMedia</code> class is a domain model class that contains the information of a single MovieMedia entry
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
@Table(name = "movie_media")
public class MovieMedia extends DateAudit {

    private Long id;
    private String url;
    private MediaType type;
    private Movie movie;

    public MovieMedia() {}

    public MovieMedia(Long id, String url, MediaType type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 400)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieMedia that = (MovieMedia) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(url, that.url) &&
                type == that.type &&
                Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, type, movie);
    }
}
