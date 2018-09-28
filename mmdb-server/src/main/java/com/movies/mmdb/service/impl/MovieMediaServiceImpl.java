package com.movies.mmdb.service.impl;

import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.model.MediaType;
import com.movies.mmdb.model.MovieMedia;
import com.movies.mmdb.repository.MovieMediaRepository;
import com.movies.mmdb.service.MovieMediaService;
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
public class MovieMediaServiceImpl implements MovieMediaService {

    private MovieMediaRepository movieMediaRepository;

    @Autowired
    public MovieMediaServiceImpl(MovieMediaRepository movieMediaRepository) {
        this.movieMediaRepository = movieMediaRepository;
    }

    @Override
    public PagedResponse<MovieMediaResponse> getAllMediaOfMovie(String type, String id, String page, String size, String sort, String direction) {
        // validate the request parameters
        ValidatingRequestParameters.validateMediaTypeParameter(type);
        ValidatingRequestParameters.parameterShouldBeNumber("id", id);
        ValidatingRequestParameters.parameterShouldBeInteger("page", page);
        ValidatingRequestParameters.parameterShouldBeInteger("size", size);
        ValidatingRequestParameters.validatePageNumberParameter(page);
        ValidatingRequestParameters.validatePageSizeParameter(size);
        ValidatingRequestParameters.validateSortAndDirection(sort, direction, MovieMedia.class);

        // get the sort direction from the arguments and convert it to Sort.Direction
        Sort.Direction sortDirection;
        if(direction.equalsIgnoreCase("asc")) {
            sortDirection = Sort.Direction.ASC;
        }
        else {
            sortDirection = Sort.Direction.DESC;
        }

        MediaType mediaType;
        if(type.equalsIgnoreCase("photo")) {
            mediaType = MediaType.PHOTO;
        }
        else {
            mediaType = MediaType.VIDEO;
        }

        // create a pageable from the arguments
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sortDirection, sort);

        // get the movies as a page from the repository
        Page<MovieMedia> movieMediaPage = this.movieMediaRepository.findByTypeAndMovieId(mediaType, Long.parseLong(id), pageable);

        // if the page is empty, return pagedResponse with empty list
        if(movieMediaPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), movieMediaPage.getNumber(),
                    movieMediaPage.getSize(), movieMediaPage.getTotalElements(), movieMediaPage.getTotalPages(), movieMediaPage.isLast());
        }

        // map the page into a list of movie DTO
        List<MovieMediaResponse> movieMediaResponseList = movieMediaPage.map(DTOModelMapper::mapMovieMediaToMovieMediaResponse).getContent();

        // return the paged response
        return new PagedResponse<>(movieMediaResponseList, movieMediaPage.getNumber(),
                movieMediaPage.getSize(), movieMediaPage.getTotalElements(), movieMediaPage.getTotalPages(), movieMediaPage.isLast());
    }
}
