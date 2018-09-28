package com.movies.mmdb.service.impl;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.model.MediaType;
import com.movies.mmdb.model.MovieMedia;
import com.movies.mmdb.repository.MovieMediaRepository;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class MovieMediaServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private MovieMediaRepository movieMediaRepository;

    @InjectMocks
    private MovieMediaServiceImpl movieMediaService;

    private PagedResponse<MovieMediaResponse> pagedMovieMediaResponse;
    private Page<MovieMedia> movieMediaPage;

    @Before
    public void setUp() {
        this.pagedMovieMediaResponse = DummyData.dummyPagedMovieMediaResponse();
        this.movieMediaPage = DummyData.dummyMovieMediaPage();
    }

    @Test
    public void getAllMediaOfMovie_CriteriaAndPageableGiven_ShouldReturnPagedMovieMediaResponse() {
        when(this.movieMediaRepository.findByTypeAndMovieId(any(MediaType.class), anyLong(), any(PageRequest.class)))
                .thenReturn(this.movieMediaPage);

        PagedResponse<MovieMediaResponse> actualPagedMovieMediaResponse = this.movieMediaService.getAllMediaOfMovie("photo", "1", "0", "5", "createdAt", "desc");

        assertThat("The actual response is different than the expected.", actualPagedMovieMediaResponse, is(equalTo(this.pagedMovieMediaResponse)));
    }

    @Test
    public void getAllMediaOfMovie_CriteriaAndPageableGiven_ShouldReturnEmptyPagedMovieMediaResponse() {
        when(this.movieMediaRepository.findByTypeAndMovieId(any(MediaType.class), anyLong(), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<MovieMediaResponse> actualPagedMovieMediaResponse = this.movieMediaService.getAllMediaOfMovie("video", "1", "0", "5", "createdAt", "asc");

        PagedResponse<MovieMediaResponse> expectedMovieMediaResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualPagedMovieMediaResponse, is(equalTo(expectedMovieMediaResponsePage)));
    }
}
