package com.movies.mmdb.service;

import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.util.PagedResponse;

public interface CelebrityService {

    PagedResponse<CelebrityResponse> getAllCelebrities(String page, String size, String sort, String direction);

    CelebrityResponse getCelebrityById(String id);
}
