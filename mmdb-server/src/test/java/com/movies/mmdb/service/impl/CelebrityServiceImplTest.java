package com.movies.mmdb.service.impl;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.exception.ResourceNotFoundException;
import com.movies.mmdb.model.Celebrity;
import com.movies.mmdb.repository.CelebrityRepository;
import com.movies.mmdb.util.PagedResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CelebrityServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private CelebrityRepository celebrityRepository;

    @InjectMocks
    private CelebrityServiceImpl celebrityService;

    private Celebrity celebrity;
    private CelebrityResponse celebrityResponse;
    private Page<Celebrity> celebrityPage;
    private PagedResponse<CelebrityResponse> pagedCelebrityResponse;

    @Before
    public void setUp() {
        this.celebrity = DummyData.dummyCelebrity();
        this.celebrityPage = DummyData.dummyCelebrityPage();
        this.pagedCelebrityResponse = DummyData.dummyPagedCelebrityResponse();
        this.celebrityResponse = DummyData.dummyCelebrityResponse();
    }

    @Test
    public void getAllCelebrities_PageableGiven_ShouldReturnPagedCelebrityResponse() {
        when(this.celebrityRepository.findAll(any(PageRequest.class))).thenReturn(this.celebrityPage);

        PagedResponse<CelebrityResponse> actualPagedCelebrityResponse = this.celebrityService.getAllCelebrities("0", "1", "id","desc");

        assertThat("The actual response is different than the expected.", actualPagedCelebrityResponse, is(equalTo(this.pagedCelebrityResponse)));
    }

    @Test
    public void getAllCelebrities_PageableGiven_ShouldReturnEmptyPagedCelebrityResponse() {
        when(this.celebrityRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<CelebrityResponse> actualCelebrityResponsePage = this.celebrityService.getAllCelebrities("0", "20", "createdAt","asc");

        PagedResponse<CelebrityResponse> expectedCelebrityResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualCelebrityResponsePage, is(equalTo(expectedCelebrityResponsePage)));
    }

    @Test
    public void getCelebrityById_IdGiven_ShouldReturnCelebrityResponse() {
        when(this.celebrityRepository.findById(anyLong())).thenReturn(Optional.of(this.celebrity));

        CelebrityResponse actualCelebrityResponse = this.celebrityService.getCelebrityById("1");

        assertThat("The actual response is different than the expected.", actualCelebrityResponse, is(equalTo(this.celebrityResponse)));
    }

    @Test
    public void getCelebrityById_NonExistIdGiven_ShouldThrowResourceNotFoundException() {
        this.expectedException.expect(ResourceNotFoundException.class);
        this.expectedException.expectMessage("celebrity not found with {id : '2'}");

        when(this.celebrityRepository.findById(anyLong())).thenReturn(Optional.empty());
        this.celebrityService.getCelebrityById("2");
    }
}
