package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <code>Movie</code> class is a domain model class that contains the information of a single Movie entry
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
public class Movie extends DateAudit {

    private Long id;
    private String name;
    private Date releaseDate;
    private Time runtime;
    private Float rating;
    private String storyline;
    private String poster;
    private String rated;
    private List<Genre> genres = new ArrayList<>();
    private List<MovieMedia> movieMedia = new ArrayList<>();
    private List<MovieReview> movieReviews = new ArrayList<>();
    private List<MovieCelebrity> movieCelebrities = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(nullable = false)
    public Time getRuntime() {
        return runtime;
    }

    public void setRuntime(Time runtime) {
        this.runtime = runtime;
    }

    @Column(precision = 2, scale = 1, nullable = false)
    @ColumnDefault(value = "0.0")
    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    @Column(length = 400)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Column(length = 10, nullable = false)
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MovieMedia> getMovieMedia() {
        return movieMedia;
    }

    public void setMovieMedia(List<MovieMedia> movieMedia) {
        this.movieMedia = movieMedia;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MovieReview> getMovieReviews() {
        return movieReviews;
    }

    public void setMovieReviews(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MovieCelebrity> getMovieCelebrities() {
        return movieCelebrities;
    }

    public void setMovieCelebrities(List<MovieCelebrity> movieCelebrities) {
        this.movieCelebrities = movieCelebrities;
    }
}
