package com.movies.mmdb.repository;

import com.movies.mmdb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
