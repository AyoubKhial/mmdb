package com.movies.mmdb.controller;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.service.MovieReviewService;
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
@WebMvcTest(MovieReviewController.class)
public class MovieReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieReviewService movieReviewService;

    private PagedResponse<MovieReviewResponse> pagedMovieReviewResponse;

    @Before
    public void setUp() {
        this.pagedMovieReviewResponse = DummyData.dummyPagedMovieReviewResponse();
    }

    @Test
    public void getAllReviewOfMovie_CriteriaGiven_ShouldReturnPagedMovieReviewResponse() throws Exception {
        given(this.movieReviewService.getAllReviewsOfMovie(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(this.pagedMovieReviewResponse);

        mockMvc.perform(get("/api/movies/1/reviews").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].rating", is(10)))
                .andExpect(jsonPath("$.content[0].title", is("Amazing movie.")))
                .andExpect(jsonPath("$.content[0].text", is("The best performance by AlPacino")))
                .andExpect(jsonPath("$.content[0].createdAt", is("09-12-1983")))
                .andExpect(jsonPath("$.content[0].updatedAt", is("09-12-1983")));

        verify(this.movieReviewService, times(1)).getAllReviewsOfMovie(anyString(), anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.movieReviewService);
    }

    @After
    public void tearDown() {
        this.pagedMovieReviewResponse = null;
    }
}
