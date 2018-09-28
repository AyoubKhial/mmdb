package com.movies.mmdb.repository;

import com.movies.mmdb.model.MediaType;
import com.movies.mmdb.model.MovieMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieMediaRepository extends JpaRepository<MovieMedia, Long> {

    Page<MovieMedia> findByTypeAndMovieId(MediaType type, Long id, Pageable pageable);
}
