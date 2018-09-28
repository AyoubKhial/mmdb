package com.movies.mmdb.controller;

import com.movies.mmdb.annotation.RestControllerApi;
import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.service.CelebrityService;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static com.movies.mmdb.util.ApplicationConstants.DEFAULT_PAGE_SIZE;

@RestControllerApi
public class CelebrityController {

    private CelebrityService celebrityService;

    @Autowired
    public CelebrityController(CelebrityService celebrityService) {
        this.celebrityService = celebrityService;
    }
    @GetMapping("/celebrities")
    public ResponseEntity<PagedResponse<CelebrityResponse>> getAllCelebrities(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) String page,
                                                                              @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) String size,
                                                                              @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                                                                              @RequestParam(value = "direction", defaultValue = "desc") String direction) {
        PagedResponse<CelebrityResponse> celebrityResponsePage = this.celebrityService.getAllCelebrities(page, size, sort, direction);
        return ResponseEntity.ok(celebrityResponsePage);
    }

}
