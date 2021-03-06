package com.movies.mmdb.dto;

import com.movies.mmdb.model.CelebrityRole;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;


public class MovieResponseTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    private MovieResponse movieResponse;

    @Before
    public void setUp() {
        this.movieResponse = new MovieResponse();
    }

    @Test
    public void testSetCelebrities_MovieCelebrityResponseList_ShouldDivideTheList() {
        MovieCelebrityResponse movieCelebrityResponse = new MovieCelebrityResponse();
        movieCelebrityResponse.setRole(CelebrityRole.ACTOR);
        MovieCelebrityResponse movieCelebrityResponse1 = new MovieCelebrityResponse();
        movieCelebrityResponse1.setRole(CelebrityRole.DIRECTOR);
        MovieCelebrityResponse movieCelebrityResponse2 = new MovieCelebrityResponse();
        movieCelebrityResponse2.setRole(CelebrityRole.WRITER);

        List<MovieCelebrityResponse> movieCelebrityResponses = new ArrayList<>(
                Arrays.asList(
                        movieCelebrityResponse,
                        movieCelebrityResponse1,
                        movieCelebrityResponse2
                )
        );

        this.movieResponse.setCelebrities(movieCelebrityResponses);
        this.errorCollector.checkThat("The size of cast list should be 1.", this.movieResponse.getCast(), hasSize(1));
        this.errorCollector.checkThat("The size of directors list should be 1.", this.movieResponse.getDirectors(), hasSize(1));
        this.errorCollector.checkThat("The size of writers list should be 1.", this.movieResponse.getWriters(), hasSize(1));
    }

    @After
    public void tearDown() {
        this.movieResponse = null;
    }
}
