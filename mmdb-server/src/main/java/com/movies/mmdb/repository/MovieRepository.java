package com.movies.mmdb.repository;

import com.movies.mmdb.model.CelebrityRole;
import com.movies.mmdb.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Manage the <code>Movie</code> entity.
 * <p>
 * By extending JpaRepository we get a bunch of generic CRUD methods to create, update, delete, and find movies.
 * <p>
 * JpaRepository extends <code>PagingAndSortingRepository</code> which in turn extends CrudRepository interface.
 * So, JpaRepository inherits all the methods from those two interfaces.
 * @author Ayoub Khial
 * @version 1.0
 * @see JpaRepository
 * @see Movie
 * @since 1.0
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Get all movies that respects some criteria given.
     * @param name the name of the movie, or just a part of the name.
     * @param minRating the minimum rating of the movie.
     * @param MaxRating the maximum rating of the movie.
     * @param fromDate the movies from this date.
     * @param toDate the movies to this date
     * @param pageable the page information.
     * @return a page of movies if there's any movies found, otherwise an empty page will be in return.
     */
    Page<Movie> findByNameContainingAndRatingBetweenAndReleaseDateBetween(String name, float minRating, float MaxRating,
                                                                          Date fromDate, Date toDate, Pageable pageable);

    /**
     * Get all movies that are related to a given movie.
     * <p>
     * A movie is related to another if they share similar genre.
     * The movies returned are sorted by the number of genre they share with the given movie.
     * @param id the id of the movie
     * @param pageable the page information.
     * @return a page of movies if there's any movies found, otherwise an empty page will be in return.
     */
    @Query("SELECT m " +
            "FROM Movie m JOIN m.genres mg " +
            "WHERE mg.id IN " +
            "(SELECT g.id FROM Genre g JOIN g.movies gm WHERE gm.id = :id) " +
            "AND m.id <> :id " +
            "GROUP BY m ORDER BY count(m) DESC")
    Page<Movie> findRelatedMoviesToAMovieById(@Param("id") Long id, Pageable pageable);

    @Query("SELECT new Movie(m.id, m.name, m.releaseDate) " +
            "FROM Movie m JOIN m.movieCelebrities mc " +
            "WHERE mc.celebrity.id = :id AND mc.role = :role")
    Page<Movie> findMoviesByCelebrity(@Param("id") Long id, @Param("role") CelebrityRole role, Pageable pageable);
}
