package com.movies.mmdb.service;

import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.util.PagedResponse;

/**
 * This class is holding the business logic about the <code>Movie</code> model.
 * @author Ayoub Khial
 * @version 1.0
 * @see MovieResponse
 * @since 1.0
 */
public interface MovieService {

    /**
     * This method will call {@link com.movies.mmdb.repository.MovieRepository#findAll(org.springframework.data.domain.Pageable)}
     * method and get a page of movies.
     * <p>
     * If there's no movies available an empty page will be in return.
     * @param page the number of the page
     * @param size the size of each page
     * @param sort the field we want to sort the page with
     * @param direction the direction of sorting
     * @return a page of movies
     */
    PagedResponse<MovieResponse> getAllMovies(String page, String size, String sort, String direction);

    /**
     * This method will call {@link com.movies.mmdb.repository.MovieRepository#findById(Object)} method and get a
     * movie based on his id.
     * <p>
     * If the id given didn't match any existent id, a {@link com.movies.mmdb.exception.ResourceNotFoundException}
     * will be thrown.
     * @param id the id of the wanted movie
     * @return a movie
     */
    MovieResponse getMovieById(String id);
}
