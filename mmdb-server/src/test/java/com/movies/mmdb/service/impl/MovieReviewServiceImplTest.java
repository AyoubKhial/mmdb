package com.movies.mmdb.service.impl;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.model.MovieReview;
import com.movies.mmdb.repository.MovieReviewRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class MovieReviewServiceImplTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private MovieReviewRepository movieReviewRepository;

    @InjectMocks
    private MovieReviewServiceImpl movieReviewService;

    private PagedResponse<MovieReviewResponse> pagedMovieReviewResponse;
    private Page<MovieReview> movieReviewPage;

    @Before
    public void setUp() {
        this.pagedMovieReviewResponse = DummyData.dummyPagedMovieReviewResponse();
        this.movieReviewPage = DummyData.dummyMovieReviewPage();
    }

    @Test
    public void getAllReviewsOfMovie_CriteriaAndPageableGiven_ShouldReturnPagedMovieReviewResponse() {
        when(this.movieReviewRepository.findByMovieId(anyLong(), any(PageRequest.class))).thenReturn(this.movieReviewPage);

        PagedResponse<MovieReviewResponse> actualPagedMovieReviewResponse = this.movieReviewService.getAllReviewsOfMovie("1", "0", "5", "createdAt", "desc");

        assertThat("The actual response is different than the expected.", actualPagedMovieReviewResponse, is(equalTo(this.pagedMovieReviewResponse)));
    }

    @Test
    public void getAllReviewsOfMovie_CriteriaAndPageableGiven_ShouldReturnEmptyPagedMovieReviewResponse() {
        when(this.movieReviewRepository.findByMovieId(anyLong(), any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<MovieReviewResponse> actualPagedMovieReviewResponse = this.movieReviewService.getAllReviewsOfMovie("1", "0", "5", "createdAt", "asc");

        PagedResponse<MovieReviewResponse> expectedMovieReviewResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualPagedMovieReviewResponse, is(equalTo(expectedMovieReviewResponsePage)));
    }
}
