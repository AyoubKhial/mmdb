package com.movies.mmdb.service;

import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.util.PagedResponse;

public interface MovieReviewService {

    PagedResponse<MovieReviewResponse> getAllReviewsOfMovie(String id, String page, String size, String sort, String direction);
}
