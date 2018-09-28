package com.movies.mmdb.repository;

import com.movies.mmdb.model.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {
}
