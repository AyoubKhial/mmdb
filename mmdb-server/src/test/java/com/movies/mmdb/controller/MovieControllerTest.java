package com.movies.mmdb.controller;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.model.Movie;
import com.movies.mmdb.service.MovieService;
import com.movies.mmdb.util.DTOModelMapper;
import com.movies.mmdb.util.PagedResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * A test class to test <code>MovieController</code> class.
 * @author Ayoub Khial
 * @version 1.0
 * @see MovieController
 * @see SpringRunner
 * @see WebMvcTest
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private Movie movie;

    @Before
    public void setUp() {
        // initialise the movie
        this.movie = DummyData.dummyMovie();
    }

    /**
     * A test case for <code>getAllMovies</code> Method.
     * @see MovieController#getAllMovies(String, String, String, String)
     */
    @Test
    public void getAllMovies_PageableGiven_ShouldReturnPagedMovieResponse() throws Exception{
        // add movie to a list
        List<Movie> movieList = new ArrayList<>();
        movieList.add(this.movie);

        // convert list to page
        Page<Movie> moviePage = new PageImpl<>(movieList);

        // map movie page to movie response list
        List<MovieResponse> movieResponseList = moviePage.map(DTOModelMapper::mapMovieToMovieResponse).getContent();

        // create PagedResponse from movieResponseList
        PagedResponse<MovieResponse> expectedMovieResponsePage = new PagedResponse<>(movieResponseList, moviePage.getNumber(),
                moviePage.getSize(), moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());

        // mock MovieService getAllMovies method result to return MovieResponse page
        given(this.movieService.getAllMovies(anyString(), anyString(), anyString(), anyString())).willReturn(expectedMovieResponsePage);

        // perform the request and test the expected response
         mockMvc.perform(get("/api/movies").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Scarface")))
                .andExpect(jsonPath("$.content[0].releaseDate", is("09-12-1983")))
                .andExpect(jsonPath("$.content[0].runtime", is("02:50:00")))
                .andExpect(jsonPath("$.content[0].rating", is(8.3)))
                .andExpect(jsonPath("$.content[0].storyline", is("This is the storyline.")))
                .andExpect(jsonPath("$.content[0].poster", is("picture.png")));

         // verify that getAllMovies get called only one time
        verify(this.movieService, times(1)).getAllMovies(anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieService);
    }
}
