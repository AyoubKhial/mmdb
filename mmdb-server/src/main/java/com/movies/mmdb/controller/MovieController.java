package com.movies.mmdb.controller;

import com.movies.mmdb.annotation.RestControllerApi;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.service.MovieService;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

/**
 * Movie controller that expose the movie related endpoints
 * <p>
 * We're using our custom annotation <code>RestControllerApi</code> which using <code>RestController</code>
 * annotation and <b>"/api"</b> as base path.
 * This controller has a required dependency <code>Movie service</code>
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
     * This method will expose a page of movies
     * @param page the page number
     * @param size the size of a single page
     * @param sort the sort of a page
     * @param direction the direction of the sort
     * @return a paged movie response
     */
    @GetMapping("/movies")
    public PagedResponse<MovieResponse> getAllMovies(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                     @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                     @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                                     @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        return this.movieService.getAllMovies(page, size, sort, direction);
    }
}
