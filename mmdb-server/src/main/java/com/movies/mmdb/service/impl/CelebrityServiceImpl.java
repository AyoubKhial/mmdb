package com.movies.mmdb.service.impl;

import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.exception.ResourceNotFoundException;
import com.movies.mmdb.model.Celebrity;
import com.movies.mmdb.repository.CelebrityRepository;
import com.movies.mmdb.service.CelebrityService;
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
public class CelebrityServiceImpl implements CelebrityService {

    private CelebrityRepository celebrityRepository;

    @Autowired
    public CelebrityServiceImpl(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }

    @Override
    public PagedResponse<CelebrityResponse> getAllCelebrities(String page, String size, String sort, String direction) {
        // check if the page arguments are valid
        ValidatingRequestParameters.parameterShouldBeInteger("page", page);
        ValidatingRequestParameters.parameterShouldBeInteger("size", size);
        ValidatingRequestParameters.validatePageNumberParameter(page);
        ValidatingRequestParameters.validatePageSizeParameter(size);
        ValidatingRequestParameters.validateSortAndDirection(sort, direction, Celebrity.class);

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

        // get the celebrities as a page from the repository
        Page<Celebrity> celebrityPage = this.celebrityRepository.findAll(pageable);

        // if the page is empty, return pagedResponse with empty list
        if(celebrityPage.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), celebrityPage.getNumber(), celebrityPage.getSize(),
                    celebrityPage.getTotalElements(), celebrityPage.getTotalPages(), celebrityPage.isLast());
        }

        // map the page into a list of celebrity DTO
        List<CelebrityResponse> celebrityResponseList = celebrityPage.map(DTOModelMapper::mapCelebrityToCelebrityResponse).getContent();

        // return the paged response
        return new PagedResponse<>(celebrityResponseList, celebrityPage.getNumber(), celebrityPage.getSize(),
                celebrityPage.getTotalElements(), celebrityPage.getTotalPages(), celebrityPage.isLast());
    }

    @Override
    public CelebrityResponse getCelebrityById(String id) {
        // get the celebrity with the given id if its exist, otherwise an exception will be thrown
        ValidatingRequestParameters.parameterShouldBeNumber("id", id);
        Long celebrityId = Long.valueOf(id);
        Celebrity celebrity = this.celebrityRepository.findById(celebrityId).orElseThrow(
                () -> new ResourceNotFoundException("celebrity", "id", celebrityId)
        );

        // map the celebrity to a celebrity response and return it
        return DTOModelMapper.mapCelebrityToCelebrityResponse(celebrity);
    }
}
