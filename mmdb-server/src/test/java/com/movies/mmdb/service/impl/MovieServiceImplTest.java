package com.movies.mmdb.service.impl;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.model.*;
import com.movies.mmdb.repository.MovieRepository;
import com.movies.mmdb.rule.MockitoRuleRule;
import com.movies.mmdb.util.DTOModelMapper;
import com.movies.mmdb.util.PagedResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * A test class to test <code>MovieServiceImpl</code> class.
 * <p>
 * Extending <code>MockitoRuleRule</code> make sur to run the test with mockito and inject the properties annotated
 * <code>@Mock</code> into the property annotated with <code>@InjectMocks</code>
 * @author Ayoub Khial
 * @version 1.0
 * @see MockitoRuleRule
 * @see MovieServiceImpl
 * @since 1.0
 */
public class MovieServiceImplTest extends MockitoRuleRule {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;

    private Page<Movie> moviePage;

    @Before
    public void setUp() {
        // initialise the movie
        this.movie = DummyData.dummyMovie();

        // initialise movie page
        List<Movie> movieList = new ArrayList<>(Collections.singletonList(this.movie));
        this.moviePage = new PageImpl<>(movieList);
    }

    /**
     * A test case for <code>getAllMovies</code> Method.
     * @see MovieServiceImpl#getAllMovies(String, String, String, String)
     */
    @Test
    public void getAllMovies_PageableGiven_ShouldReturnPagedMovieResponse() {
        // Clear movie page
        MovieServiceImpl.removeUndesirableFields(this.moviePage);

        // mock MovieRepository findAll method result to return movie page
        when(this.movieRepository.findAll(any(PageRequest.class))).thenReturn(this.moviePage);

        // map movie page content to movie response list
        List<MovieResponse> movieResponseList = this.moviePage.map(DTOModelMapper::mapMovieToMovieResponse).getContent();

        // expected value
        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(movieResponseList, this.moviePage.getNumber(),
                this.moviePage.getSize(), this.moviePage.getTotalElements(), this.moviePage.getTotalPages(), this.moviePage.isLast());

        // actual value
        PagedResponse<MovieResponse> actualMovieResponsePage = this.movieService.getAllMovies("0", "20", "id","desc");

        // assertion
        assertThat("The actual response is different than the expected", actualMovieResponsePage, is(equalTo(expectedMovieResponsePage)));
    }

    /**
     * A test case for <code>getAllMovies</code> Method.
     * @see MovieServiceImpl#getAllMovies(String, String, String, String)
     */
    @Test
    public void getAllMovies_PageableGiven_ShouldReturnEmptyPagedMovieResponse() {
        // create empty list
        List<Movie> movieList = new ArrayList<>();

        // convert list to page
        Page<Movie> moviePage = new PageImpl<>(movieList);
        when(this.movieRepository.findAll(any(PageRequest.class))).thenReturn(moviePage);

        // expected value
        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(Collections.emptyList(), moviePage.getNumber(),
                moviePage.getSize(), moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());

        // actual value
        PagedResponse<MovieResponse> actualMovieResponsePage = this.movieService.getAllMovies("0", "20", "id","desc");

        assertThat("", actualMovieResponsePage, is(equalTo(expectedMovieResponsePage)));
    }

    /**
     * A test case for <code>removeUndesirableFields</code> Method.
     * @see MovieServiceImpl#removeUndesirableFields(Page)
     */
    @Test
    public void removeUndesirableFields_PageGiven_ShouldRemoveUndesirableFields() {

        // actual value
        Page<Movie> actualMoviePage = MovieServiceImpl.removeUndesirableFields(this.moviePage);

        // instantiate new movie based on a class variable 'movie'
        Movie movie1 = new Movie(this.movie);
        movie1.getMovieReviews().clear();
        movie1.getMovieMedia().clear();

        // create list of movies and add movie1 to it
        List<Movie> movieList1 = new ArrayList<>(Collections.singletonList(movie1));

        // convert movieList1 to page
        Page<Movie> moviePage1 = new PageImpl<>(movieList1);

        assertThat("", actualMoviePage, is(equalTo(moviePage1)));
    }

    @After
    public void tearDown() {
        this.movie = null;
    }
}
