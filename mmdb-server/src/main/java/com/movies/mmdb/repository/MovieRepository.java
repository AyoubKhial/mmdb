package com.movies.mmdb.repository;

import com.movies.mmdb.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
    Page<Movie> findByNameContainingAndRatingBetweenAndReleaseDateBetween(@Param("name") String name,
                                                                          @Param("min_rating") float minRating,
                                                                          @Param("max_rating") float MaxRating,
                                                                          @Param("from") Date fromDate,
                                                                          @Param("to") Date toDate,
                                                                          Pageable pageable);
}
