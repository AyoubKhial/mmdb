package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>Celebrity</code> class is a domain model class that contains the information of a single Celebrity entry
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
public class Celebrity extends DateAudit {

    private Long id;
    private String name;
    private String picture;
    private Date dateOfBirth;
    private String biography;
    private Set<CelebrityMedia> celebrityMedia = new HashSet<>();
    private Set<MovieCelebrity> movieCelebrities = new HashSet<>();

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

    @Column(length = 400)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(columnDefinition = "TEXT",nullable = false)
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @OneToMany(mappedBy = "celebrity", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<CelebrityMedia> getCelebrityMedia() {
        return celebrityMedia;
    }

    public void setCelebrityMedia(Set<CelebrityMedia> celebrityMedia) {
        this.celebrityMedia = celebrityMedia;
    }

    @OneToMany(mappedBy = "celebrity", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<MovieCelebrity> getMovieCelebrities() {
        return movieCelebrities;
    }

    public void setMovieCelebrities(Set<MovieCelebrity> movieCelebrities) {
        this.movieCelebrities = movieCelebrities;
    }
}
