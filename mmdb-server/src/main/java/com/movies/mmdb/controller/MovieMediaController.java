package com.movies.mmdb.controller;

import com.movies.mmdb.annotation.RestControllerApi;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.service.MovieMediaService;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

@RestControllerApi
public class MovieMediaController {

    private MovieMediaService movieMediaService;

    @Autowired
    public MovieMediaController(MovieMediaService movieMediaService) {
        this.movieMediaService = movieMediaService;
    }

    @GetMapping("/movies/{id}/{type}")
    public ResponseEntity<PagedResponse<MovieMediaResponse>> getAllMediaOfMovie(@PathVariable String id,
                                                                         @PathVariable String type,
                                                                         @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                         @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                         @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                                                         @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        PagedResponse<MovieMediaResponse> movieMediaResponsePage = this.movieMediaService.getAllMediaOfMovie(type, id, page, size, sort, direction);
        return ResponseEntity.ok(movieMediaResponsePage);
    }
}
