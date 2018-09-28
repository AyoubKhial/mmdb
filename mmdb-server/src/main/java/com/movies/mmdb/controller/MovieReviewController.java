package com.movies.mmdb.controller;

import com.movies.mmdb.annotation.RestControllerApi;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.service.MovieReviewService;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

@RestControllerApi
public class MovieReviewController {

    private MovieReviewService movieReviewService;

    @Autowired
    public MovieReviewController(MovieReviewService movieReviewService) {
        this.movieReviewService = movieReviewService;
    }

    @GetMapping("/movies/{id}/reviews")
    public ResponseEntity<PagedResponse<MovieReviewResponse>> getAllReviewOfMovie(@PathVariable String id,
                                                                                 @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                                 @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                                 @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                                                                 @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        PagedResponse<MovieReviewResponse> movieMediaResponsePage = this.movieReviewService.getAllReviewsOfMovie(id, page, size, sort, direction);
        return ResponseEntity.ok(movieMediaResponsePage);
    }
}
