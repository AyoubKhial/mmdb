package com.movies.mmdb.controller;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.service.MovieService;
import com.movies.mmdb.util.PagedResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private PagedResponse<MovieResponse> pagedMovieResponse;
    private MovieResponse movieResponse;

    @Before
    public void setUp() {
        this.pagedMovieResponse = DummyData.dummyPagedMovieResponse();
        this.movieResponse = DummyData.dummyMovieResponse();
    }

    @Test
    public void getAllMovies_PageableGiven_ShouldReturnPagedMovieResponse() throws Exception {
        given(this.movieService.getAllMovies(anyString(), anyString(), anyString(), anyString())).willReturn(this.pagedMovieResponse);

        mockMvc.perform(get("/api/movies").accept(MediaType.APPLICATION_JSON))
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

        verify(this.movieService, times(1)).getAllMovies(anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieService);
    }

    @Test
    public void getMovieById_IdGiven_ShouldReturnMovieResponse() throws Exception {
        given(this.movieService.getMovieById(anyString())).willReturn(this.movieResponse);

        mockMvc.perform(get("/api/movies/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Scarface")))
                .andExpect(jsonPath("$.releaseDate", is("09-12-1983")))
                .andExpect(jsonPath("$.runtime", is("02:50:00")))
                .andExpect(jsonPath("$.rating", is(8.3)))
                .andExpect(jsonPath("$.storyline", is("This is the storyline.")))
                .andExpect(jsonPath("$.poster", is("picture.png")));

        verify(this.movieService, times(1)).getMovieById(anyString());
        verifyNoMoreInteractions(this.movieService);
    }

    @Test
    public void getMoviesByCriteria_CriteriaGiven_ShouldReturnPagedMovieResponse() throws Exception {
        given(this.movieService.getMoviesByCriteria(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).willReturn(this.pagedMovieResponse);

        mockMvc.perform(get("/api/movies/search?name=Scarface&min_rating=5").accept(MediaType.APPLICATION_JSON))
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

        verify(this.movieService, times(1)).getMoviesByCriteria(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieService);
    }

    @Test
    public void getRelatedMovies_IdGiven_ShouldReturnPagedMovieResponse() throws Exception {
        given(this.movieService.getRelatedMovies(anyString(), anyString(), anyString())).willReturn(this.pagedMovieResponse);

        mockMvc.perform(get("/api/movies/2/related").accept(MediaType.APPLICATION_JSON))
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

        verify(this.movieService, times(1)).getRelatedMovies(anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieService);
    }

    @Test
    public void getMoviesByCelebrity_CriteriaGiven_ShouldReturnPagedMovieResponse() throws Exception {
        given(this.movieService.getMoviesByCelebrity(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(this.pagedMovieResponse);

        mockMvc.perform(get("/api/celebrities/1/acted").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Scarface")))
                .andExpect(jsonPath("$.content[0].releaseDate", is("09-12-1983")));

        verify(this.movieService, times(1)).getMoviesByCelebrity(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieService);
    }

    @After
    public void tearDown() {
        this.pagedMovieResponse = null;
        this.movieResponse = null;
    }
}
