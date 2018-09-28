package com.movies.mmdb.controller;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.service.CelebrityService;
import com.movies.mmdb.util.PagedResponse;
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
@WebMvcTest(CelebrityController.class)
public class CelebrityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CelebrityService celebrityService;

    private PagedResponse<CelebrityResponse> pagedCelebrityResponse;

    @Before
    public void setUp() {
        this.pagedCelebrityResponse = DummyData.dummyPagedCelebrityResponse();
    }

    @Test
    public void getAllCelebrities_PageableGiven_ShouldReturnPagedMovieResponse() throws Exception {
        given(this.celebrityService.getAllCelebrities(anyString(), anyString(), anyString(), anyString()))
                .willReturn(this.pagedCelebrityResponse);

        mockMvc.perform(get("/api/celebrities").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Al Pacino")))
                .andExpect(jsonPath("$.content[0].picture", is("photo1.png")));

        verify(this.celebrityService, times(1)).getAllCelebrities(anyString(), anyString(), anyString(), anyString());
        verifyNoMoreInteractions(this.celebrityService);
    }
}
