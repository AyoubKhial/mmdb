package com.movies.mmdb.service.impl;

import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.model.MovieReview;
import com.movies.mmdb.repository.MovieReviewRepository;
import com.movies.mmdb.service.MovieReviewService;
import com.movies.mmdb.service.ValidatingRequestParameters;
import com.movies.mmdb.util.DTOModelMapper;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private MovieReviewRepository movieReviewRepository;

    @Autowired
    public MovieReviewServiceImpl(MovieReviewRepository movieReviewRepository) {
        this.movieReviewRepository = movieReviewRepository;
    }

    @Override
    public PagedResponse<MovieReviewResponse> getAllReviewsOfMovie(String id, String page, String size, String sort, String direction) {
        // validate the request parameters
        ValidatingRequestParameters.parameterShouldBeNumber("id", id);
        ValidatingRequestParameters.parameterShouldBeInteger("page", page);
        ValidatingRequestParameters.parameterShouldBeInteger("size", size);
        ValidatingRequestParameters.validatePageNumberParameter(page);
        ValidatingRequestParameters.validatePageSizeParameter(size);
        ValidatingRequestParameters.validateSortAndDirection(sort, direction, MovieReview.class);

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

        // get the movie reviews as a page from the repository
        Page<MovieReview> movieReviewPage = this.movieReviewRepository.findByMovieId(Long.parseLong(id), pageable);

        // if the page is empty, return pagedResponse with empty list
        if(movieReviewPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), movieReviewPage.getNumber(), movieReviewPage.getSize(),
                    movieReviewPage.getTotalElements(), movieReviewPage.getTotalPages(), movieReviewPage.isLast());
        }

        // map the page into a list of movie reviews DTO
        List<MovieReviewResponse> movieReviewResponseList = movieReviewPage.map(DTOModelMapper::mapMovieReviewToMovieReviewResponse).getContent();

        // return the paged response
        return new PagedResponse<>(movieReviewResponseList, movieReviewPage.getNumber(), movieReviewPage.getSize(),
                movieReviewPage.getTotalElements(), movieReviewPage.getTotalPages(), movieReviewPage.isLast());
    }
}
