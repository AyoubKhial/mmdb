package com.movies.mmdb.repository;

import com.movies.mmdb.model.MovieReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    @Query("SELECT new MovieReview(mr.rating, mr.title, mr.text, mr.user, mr.createdAt, mr.updatedAt) " +
            "FROM MovieReview mr " +
            "WHERE mr.movie.id = :id")
    Page<MovieReview> findByMovieId(@Param("id") Long id, Pageable pageable);
}
