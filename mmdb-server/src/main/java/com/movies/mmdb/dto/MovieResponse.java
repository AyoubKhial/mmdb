package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.mmdb.model.CelebrityRole;
import com.movies.mmdb.model.MediaType;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private List<MovieMediaResponse> movieMedia = new ArrayList<>();
    private List<MovieMediaResponse> videos = new ArrayList<>();
    private List<MovieMediaResponse> photos = new ArrayList<>();
    private List<MovieReviewResponse> reviews = new ArrayList<>();
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
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Time getRuntime() {
        return runtime;
    }

    public void setRuntime(Time runtime) {
        this.runtime = runtime;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

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

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<GenreResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponse> genres) {
        this.genres = genres;
    }

    @JsonIgnore
    public List<MovieMediaResponse> getMovieMedia() {
        return movieMedia;
    }

    /**
     * Divide the list of media given as a parameter into two list based on the type of the media.
     * <p>
     * If the type of the media is <b>PHOTO</b> we set this media to <code>photos</code> attribute using
     * <code>setPhotos</code> method; else if the type is <b>VIDEO</b> we set this media to <code>videos</code>
     * attribute using <code>setVideos</code> method.
     * @param movieMedias the list of media.
     * @see MediaType
     */
    public void setMovieMedia(List<MovieMediaResponse> movieMedias) {
        this.setPhotos(movieMedias.stream().filter(movieMedia -> movieMedia.getType().equals(MediaType.PHOTO)).collect(Collectors.toList()));
        this.setVideos(movieMedias.stream().filter(movieMedia -> movieMedia.getType().equals(MediaType.VIDEO)).collect(Collectors.toList()));
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieMediaResponse> getVideos() {
        return videos;
    }

    public void setVideos(List<MovieMediaResponse> videos) {
        this.videos = videos;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieMediaResponse> getPhotos() {
        return photos;
    }

    public void setPhotos(List<MovieMediaResponse> photos) {
        this.photos = photos;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<MovieReviewResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReviewResponse> reviews) {
        this.reviews = reviews;
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
        this.setCast(movieCelebrities.stream().filter(movieCelebrity -> movieCelebrity.getRole().equals(CelebrityRole.ACTOR)).collect(Collectors.toList()));
        this.setDirectors(movieCelebrities.stream().filter(movieCelebrity -> movieCelebrity.getRole().equals(CelebrityRole.DIRECTOR)).collect(Collectors.toList()));
        this.setWriters(movieCelebrities.stream().filter(movieCelebrity -> movieCelebrity.getRole().equals(CelebrityRole.WRITER)).collect(Collectors.toList()));
    }

    public List<MovieCelebrityResponse> getCast() {
        return cast;
    }

    public void setCast(List<MovieCelebrityResponse> cast) {
        this.cast = cast;
    }

    public List<MovieCelebrityResponse> getWriters() {
        return writers;
    }

    public void setWriters(List<MovieCelebrityResponse> writers) {
        this.writers = writers;
    }

    public List<MovieCelebrityResponse> getDirectors() {
        return directors;
    }

    public void setDirectors(List<MovieCelebrityResponse> directors) {
        this.directors = directors;
    }
}
