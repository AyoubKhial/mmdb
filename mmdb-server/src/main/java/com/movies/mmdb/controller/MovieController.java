package com.movies.mmdb.controller;

import com.movies.mmdb.annotation.RestControllerApi;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.service.MovieService;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

/**
 * Movie controller that expose the movie related endpoints.
 * <p>
 * We're using our custom annotation <code>RestControllerApi</code> which using <code>RestController</code>
 * annotation and <b>"/api"</b> as base path.
 * This controller has a required dependency <code>Movie service</code>.
 * @author Ayoub Khial
 * @version 1.0
 * @see MovieService
 * @see RestControllerApi
 * @since 1.0
 */
@RestControllerApi
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * This method will expose a page of movies.
     * @param page the page number.
     * @param size the size of a single page.
     * @param sort the sort of a page.
     * @param direction the direction of the sort.
     * @return a response with a paged movies.
     */
    @GetMapping("/movies")
    public ResponseEntity<PagedResponse<MovieResponse>> getAllMovies(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                     @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                     @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                                                     @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        PagedResponse<MovieResponse> movieResponsePage = this.movieService.getAllMovies(page, size, sort, direction);
        return ResponseEntity.ok(movieResponsePage);
    }

    /**
     * This method will expose a single movie.
     * @param id the id of the movie.
     * @return a response with a movie.
     */
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable String id) {
        MovieResponse movieResponse = this.movieService.getMovieById(id);
        return ResponseEntity.ok(movieResponse);
    }

    /**
     * Get all movies based on some given criteria.
     * @param name the name of the movie.
     * @param minRating the minimum rating can a movie have.
     * @param maxRating the minimum rating can a movie have.
     * @param fromDate the movies from this date.
     * @param toDate the movies to this date
     * @param page the page number.
     * @param size the size of a single page.
     * @param sort the sort of a page.
     * @param direction the direction of the sort.
     * @return a response with the movies found with the criteria given.
     */
    @GetMapping("/movies/search")
    public ResponseEntity<PagedResponse<MovieResponse>> getMoviesByCriteria(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                           @RequestParam(name = "min_rating", required = false, defaultValue = "0") String minRating,
                                           @RequestParam(name = "max_rating", required = false, defaultValue = "10") String maxRating,
                                           @RequestParam(name = "from", required = false, defaultValue = "1800") @DateTimeFormat(pattern = "yyyy") String fromDate,
                                           @RequestParam(name = "to", required = false, defaultValue = "") @DateTimeFormat(pattern = "yyyy") String toDate,
                                           @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                           @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                           @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                           @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        PagedResponse<MovieResponse> movieResponsePage = this.movieService.getMoviesByCriteria(name, minRating, maxRating, fromDate, toDate, page, size, sort, direction);
        return ResponseEntity.ok(movieResponsePage);
    }

    /**
     * Get related movies to a movie.
     * @param id the id of the movie.
     * @param page the page number.
     * @param size the size of a single page.
     * @return a response with the related movies found.
     */
    @GetMapping("/movies/{id}/related")
    public ResponseEntity<PagedResponse<MovieResponse>> getRelatedMovies(@PathVariable String id,
                                                                            @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size) {
        PagedResponse<MovieResponse> movieResponsePage = this.movieService.getRelatedMovies(id, page, size);
        return ResponseEntity.ok(movieResponsePage);
    }
}
