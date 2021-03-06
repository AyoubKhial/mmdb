package com.movies.mmdb.model;

import com.movies.mmdb.model.audit.DateAudit;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

/**
 * <code>MovieCelebrity</code> class is a domain model class that contains the information of a single MovieCelebrity entry
 * <p>
 * The entity which is represented by this class has a <b>unique constraint </b> on the following fields: <code>movie_id</code>,
 * <code>celebrity_id</code> and <code>role</code>
 * @author Ayoub Khial
 * @version 1.0
 * @see DateAudit
 * @since 1.0
 */
@Entity
@Table(name = "movie_celebrity", uniqueConstraints = @UniqueConstraint(columnNames={"movie_id", "celebrity_id", "role"}))
public class MovieCelebrity extends DateAudit {

    private Long id;
    private Boolean credited;
    private CelebrityRole role;
    private String characterName;
    private Movie movie;
    private Celebrity celebrity;

    public MovieCelebrity(){}

    public MovieCelebrity(Long id, Boolean credited, CelebrityRole role, String characterName, Celebrity celebrity) {
        this.id = id;
        this.credited = credited;
        this.role = role;
        this.characterName = characterName;
        this.celebrity = celebrity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ColumnDefault(value = "1")
    @Column(nullable = false)
    public Boolean getCredited() {
        return credited;
    }

    public void setCredited(Boolean credited) {
        this.credited = credited;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    public CelebrityRole getRole() {
        return role;
    }

    public void setRole(CelebrityRole role) {
        this.role = role;
    }

    @Column(length = 50)
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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
    @JoinColumn(name = "celebrity_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Celebrity getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(Celebrity celebrity) {
        this.celebrity = celebrity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCelebrity that = (MovieCelebrity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(credited, that.credited) &&
                role == that.role &&
                Objects.equals(characterName, that.characterName) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(celebrity, that.celebrity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, credited, role, characterName, movie, celebrity);
    }
}
