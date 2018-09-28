package com.movies.mmdb.service;

import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.stereotype.Service;

@Service
public interface MovieMediaService {

    PagedResponse<MovieMediaResponse> getAllMediaOfMovie(String type, String id, String page, String size, String sort, String direction);
}
