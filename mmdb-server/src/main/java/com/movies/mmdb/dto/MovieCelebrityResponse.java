package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movies.mmdb.model.CelebrityRole;

import java.util.Objects;

/**
 * <code>MovieCelebrityResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.MovieCelebrity}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.MovieCelebrity}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.MovieCelebrity
 * @since 1.0
 */
public class MovieCelebrityResponse {

    private Boolean credited;
    private CelebrityRole role;
    private String characterName;
    private CelebrityResponse celebrity;

    public Boolean getCredited() {
        return credited;
    }

    public void setCredited(Boolean credited) {
        this.credited = credited;
    }

    @JsonIgnore
    public CelebrityRole getRole() {
        return role;
    }

    public void setRole(CelebrityRole role) {
        this.role = role;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CelebrityResponse getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(CelebrityResponse celebrity) {
        this.celebrity = celebrity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCelebrityResponse that = (MovieCelebrityResponse) o;
        return Objects.equals(credited, that.credited) &&
                role == that.role &&
                Objects.equals(characterName, that.characterName) &&
                Objects.equals(celebrity, that.celebrity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credited, role, characterName, celebrity);
    }
}
