package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.mmdb.model.CelebrityRole;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <code>MovieResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.Movie}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.Movie}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.Movie
 * @since 1.0
 */
public class MovieResponse {

    private Long id;
    private String name;
    private Date releaseDate;
    private Time runtime;
    private Float rating;
    private String storyline;
    private String poster;
    private String rated;
    private Date createdAt;
    private List<GenreResponse> genres = new ArrayList<>();
    private List<MovieCelebrityResponse> celebrities = new ArrayList<>();
    private List<MovieCelebrityResponse> cast = new ArrayList<>();
    private List<MovieCelebrityResponse> writers = new ArrayList<>();
    private List<MovieCelebrityResponse> directors = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Time getRuntime() {
        return runtime;
    }

    public void setRuntime(Time runtime) {
        this.runtime = runtime;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<GenreResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponse> genres) {
        this.genres = genres;
    }

    @JsonIgnore
    public List<MovieCelebrityResponse> getCelebrities() {
        return celebrities;
    }

    /**
     * Divide the list of celebrity given as a parameter into three list based on the role of the celebrity.
     * <p>
     * If the role of the celebrity is <b>ACTOR</b> we set this celebrity to <code>actors</code> attribute using
     * <code>setActors</code> method; else if the role is <b>DIRECTOR</b> we set this celebrity to
     * <code>directors</code> attribute using <code>setDirectors</code> method; else if the role is <b>WRITER</b>
     * we set this celebrity to <code>writers</code> attribute using <code>setWriters</code> method.
     * @param movieCelebrities the list of celebrities who participated in the current movie.
     * @see CelebrityRole
     */
    public void setCelebrities(List<MovieCelebrityResponse> movieCelebrities) {
        // grouping the list based on the role
        Map<Object, List<MovieCelebrityResponse>> celebrities = movieCelebrities.stream()
                .collect(Collectors.groupingBy(MovieCelebrityResponse::getRole));

        // get each group
        this.setCast(celebrities.get(CelebrityRole.ACTOR));
        this.setDirectors(celebrities.get(CelebrityRole.DIRECTOR));
        this.setWriters(celebrities.get(CelebrityRole.WRITER));
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieCelebrityResponse> getCast() {
        return cast;
    }

    public void setCast(List<MovieCelebrityResponse> cast) {
        this.cast = cast;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieCelebrityResponse> getWriters() {
        return writers;
    }

    public void setWriters(List<MovieCelebrityResponse> writers) {
        this.writers = writers;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieCelebrityResponse> getDirectors() {
        return directors;
    }

    public void setDirectors(List<MovieCelebrityResponse> directors) {
        this.directors = directors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieResponse that = (MovieResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(runtime, that.runtime) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(storyline, that.storyline) &&
                Objects.equals(poster, that.poster) &&
                Objects.equals(rated, that.rated) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(genres, that.genres) &&
                Objects.equals(celebrities, that.celebrities) &&
                Objects.equals(cast, that.cast) &&
                Objects.equals(writers, that.writers) &&
                Objects.equals(directors, that.directors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, runtime, rating, storyline, poster, rated, createdAt, genres, celebrities, cast, writers, directors);
    }
}
