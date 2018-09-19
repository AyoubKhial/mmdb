package com.movies.mmdb.service.impl;

import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.exception.ResourceNotFoundException;
import com.movies.mmdb.model.Movie;
import com.movies.mmdb.repository.MovieRepository;
import com.movies.mmdb.service.MovieService;
import com.movies.mmdb.service.ValidatingRequestParameters;
import com.movies.mmdb.util.DTOModelMapper;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagedResponse<MovieResponse> getAllMovies(String page, String size, String sort, String direction) {
        // check if the page arguments are valid
        ValidatingRequestParameters.validatePageNumberAndSize(page, size);
        ValidatingRequestParameters.validateSortAndDirection(sort, direction, Movie.class);

        // get the sort direction from the arguments and convert it to Sort.Direction
        Sort.Direction sortDirection;
        if(direction.equalsIgnoreCase("asc")) {
            sortDirection = Sort.Direction.ASC;
        }
        else {
            sortDirection = Sort.Direction.DESC;
        }

        // create a pageable from the arguments
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sortDirection, sort);

        // get the movies as a page from the repository
        Page<Movie> moviePage = this.movieRepository.findAll(pageable);

        // if the page is empty, return pagedResponse with empty list
        if(moviePage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), moviePage.getNumber(),
                    moviePage.getSize(), moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());
        }

        // remove the undesirable fields from the page
        removeUndesirableFields(moviePage);

        // map the page into a list of movie DTO
        List<MovieResponse> movieResponseList = moviePage.map(DTOModelMapper::mapMovieToMovieResponse).getContent();

        // return the paged response
        return new PagedResponse<>(movieResponseList, moviePage.getNumber(),
                moviePage.getSize(), moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieResponse getMovieById(String id) {
        // get the movie with the given id if its exist, otherwise an exception will be thrown
        ValidatingRequestParameters.validateId(id);
        Long movieId = Long.valueOf(id);
        Movie movie = this.movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("movie", "id", movieId)
        );

        // map the movie to a movie response and return it
        return DTOModelMapper.mapMovieToMovieResponse(movie);
    }

    /**
     * Remove som unnecessarily fields to keep the response clean.
     * <p>
     * In the case where the client request for a list of movies we can't respond with all the information for
     * each movie, for that we remove certain unnecessarily fields and return just a summarizing response
     * @param moviePage the page we want to remove the fields from
     */
    public static Page<Movie> removeUndesirableFields(Page<Movie> moviePage) {
        moviePage.getContent().forEach(movie -> {
            movie.getMovieReviews().clear();
            movie.getMovieMedia().clear();
            movie.getMovieCelebrities().forEach(movieCelebrity -> {
                movieCelebrity.getCelebrity().setBiography(null);
                movieCelebrity.getCelebrity().setPicture(null);
                movieCelebrity.getCelebrity().setDateOfBirth(null);
            });
        });
        return moviePage;
    }
}
