package com.movies.mmdb.service.impl;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.exception.ResourceNotFoundException;
import com.movies.mmdb.model.*;
import com.movies.mmdb.repository.MovieRepository;
import com.movies.mmdb.util.PagedResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class MovieServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;
    private Page<Movie> moviePage;
    private PagedResponse<MovieResponse> pagedMovieResponse;
    private MovieResponse movieResponse;

    @Before
    public void setUp() {
        this.movie = DummyData.dummyMovie();
        this.moviePage = DummyData.dummyMoviePage();
        this.pagedMovieResponse = DummyData.dummyPagedMovieResponse();
        this.movieResponse = DummyData.dummyMovieResponse();
    }

    @Test
    public void getAllMovies_PageableWithDescSortDirectionGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findAll(any(PageRequest.class))).thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getAllMovies("0", "1", "id","desc");

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getAllMovies_PageableWithAscSortDirectionGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findAll(any(PageRequest.class))).thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualMovieResponsePage = this.movieService.getAllMovies("0", "20", "id","asc");

        assertThat("The actual response is different than the expected.", actualMovieResponsePage, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getAllMovies_PageableGiven_ShouldReturnEmptyPagedMovieResponse() {
        when(this.movieRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<MovieResponse> actualMovieResponsePage = this.movieService.getAllMovies("0", "20", "createdAt","desc");

        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualMovieResponsePage, is(equalTo(expectedMovieResponsePage)));
    }

    @Test
    public void getMovieById_IdGiven_ShouldReturnMovieResponse() {
        when(this.movieRepository.findById(anyLong())).thenReturn(Optional.of(this.movie));

        MovieResponse actualMovieResponse = this.movieService.getMovieById("1");

        assertThat("The actual response is different than the expected.", actualMovieResponse, is(equalTo(this.movieResponse)));
    }

    @Test
    public void getMovieById_NonExistIdGiven_ShouldThrowResourceNotFoundException() {
        this.expectedException.expect(ResourceNotFoundException.class);
        this.expectedException.expectMessage("movie not found with {id : '2'}");

        when(this.movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        this.movieService.getMovieById("2");
    }

    @Test
    public void getMoviesByCriteria_CriteriaWithDescSortDirectionGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findByNameContainingAndRatingBetweenAndReleaseDateBetween(anyString(), anyFloat(), anyFloat(), any(Date.class), any(Date.class), any(PageRequest.class)))
                .thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getMoviesByCriteria("Scarface", "5", "10", "1950", "1970","0", "1", "id","desc");

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getMoviesByCriteria_CriteriaWithAscSortDirectionGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findByNameContainingAndRatingBetweenAndReleaseDateBetween(anyString(), anyFloat(), anyFloat(), any(Date.class), any(Date.class), any(PageRequest.class)))
                .thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getMoviesByCriteria("Scarface", "5", "10", "1950", "1970","0", "1", "id","asc");

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getMoviesByCriteria_EmptyToDateGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findByNameContainingAndRatingBetweenAndReleaseDateBetween(anyString(), anyFloat(), anyFloat(), any(Date.class), any(Date.class), any(PageRequest.class)))
                .thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getMoviesByCriteria("Scarface", "5", "10", "1950", "","0", "1", "id","asc");

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getMoviesByCriteria_CriteriaGiven_ShouldReturnEmptyPagedMovieResponse() {
        when(this.movieRepository.findByNameContainingAndRatingBetweenAndReleaseDateBetween(anyString(), anyFloat(), anyFloat(), any(Date.class), any(Date.class), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getMoviesByCriteria("Scarface", "5", "10", "1950", "1970","0", "1", "id","asc");

        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(expectedMovieResponsePage)));
    }

    @Test
    public void getRelatedMovies_IdGiven_ShouldReturnPagedMovieResponse() {
        when(this.movieRepository.findRelatedMoviesToAMovieById(anyInt(), any(PageRequest.class))).thenReturn(this.moviePage);

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getRelatedMovies("1", "0", "10");

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(this.pagedMovieResponse)));
    }

    @Test
    public void getRelatedMovies_IdGiven_ShouldReturnEmptyPagedMovieResponse() {
        when(this.movieRepository.findRelatedMoviesToAMovieById(anyInt(), any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        PagedResponse<MovieResponse> actualPagedMovieResponse = this.movieService.getRelatedMovies("1", "0", "10");

        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 1, true);

        assertThat("The actual response is different than the expected.", actualPagedMovieResponse, is(equalTo(expectedMovieResponsePage)));
    }

    @Test
    public void removeUndesirableFields_PageGiven_ShouldRemoveUndesirableFields() {
        List<Movie> movieList = new ArrayList<>(Collections.singletonList(this.movie));

        Page<Movie> moviePage = new PageImpl<>(movieList);

        Page<Movie> actualPageMovie = MovieServiceImpl.removeUndesirableFields(moviePage);

        assertThat("The actual response is different than the expected.", actualPageMovie, is(equalTo(this.moviePage)));
    }

    @After
    public void tearDown() {
        this.movie = null;
        this.moviePage = null;
        this.pagedMovieResponse = null;
        this.movieResponse = null;
    }
}
