package com.movies.mmdb.controller;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.service.MovieMediaService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieMediaController.class)
public class MovieMediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieMediaService movieMediaService;

    private PagedResponse<MovieMediaResponse> pagedMovieMediaResponse;

    @Before
    public void setUp() {
        this.pagedMovieMediaResponse = DummyData.dummyPagedMovieMediaResponse();
    }

    @Test
    public void getAllMediaOfMovie_CriteriaGiven_ShouldReturnPagedMovieMediaResponse() throws Exception {
        given(this.movieMediaService.getAllMediaOfMovie(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).willReturn(this.pagedMovieMediaResponse);

        mockMvc.perform(get("/api/movies/1/photo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].url", is("photo.png")));

        verify(this.movieMediaService, times(1)).getAllMediaOfMovie(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieMediaService);
    }

    @After
    public void tearDown() {
        this.pagedMovieMediaResponse = null;
    }
}
