package com.movies.mmdb.dto;

import com.movies.mmdb.model.CelebrityRole;
import com.movies.mmdb.model.MediaType;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;

/**
 * A test class to test <code>MovieResponse</code> class
 * @author Ayoub Khial
 * @version 1.0
 * @see MovieResponse
 * @since 1.0
 */
public class MovieResponseTest {

    private MovieResponse movieResponse;

    @Before
    public void setUp() {
        // Initialize the movieResponse Object
        this.movieResponse = new MovieResponse();
    }
    /**
     * Enable a test not to stop on an error by doing all assertions and listing the failed ones at the end.
     * the <code>@Rule</code> annotation offers a generic way to add extended features on a test method
     */
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    /**
     * A test case for <code>setCelebrity</code> Method
     * @see MovieResponse#setCelebrities(List)
     */
    @Test
    public void testSetCelebrities() {
        // Creating three objects of MovieCelebrityResponse and setting for each object a different role
        MovieCelebrityResponse movieCelebrityResponse = new MovieCelebrityResponse();
        movieCelebrityResponse.setRole(CelebrityRole.ACTOR);
        MovieCelebrityResponse movieCelebrityResponse1 = new MovieCelebrityResponse();
        movieCelebrityResponse1.setRole(CelebrityRole.DIRECTOR);
        MovieCelebrityResponse movieCelebrityResponse2 = new MovieCelebrityResponse();
        movieCelebrityResponse2.setRole(CelebrityRole.WRITER);
        // Add the three previously generated objects to a list
        List<MovieCelebrityResponse> movieCelebrityResponses = new ArrayList<>(
                Arrays.asList(
                        movieCelebrityResponse,
                        movieCelebrityResponse1,
                        movieCelebrityResponse2
                )
        );
        // Add the list to the current movieResponse object
        this.movieResponse.setCelebrities(movieCelebrityResponses);
        this.collector.checkThat("The size of cast list should be 1.", this.movieResponse.getCast(), hasSize(1));
        this.collector.checkThat("The size of directors list should be 1.", this.movieResponse.getDirectors(), hasSize(1));
        this.collector.checkThat("The size of writers list should be 1.", this.movieResponse.getWriters(), hasSize(1));
    }

    /**
     * A test case for <code>setMovieMedia</code> Method
     * @see MovieResponse#setMovieMedia(List)
     */
    @Test
    public void testSetMovieMedia() {
        // Creating two objects of MovieMediaResponse and setting for each object a different type
        MovieMediaResponse movieMediaResponse = new MovieMediaResponse();
        movieMediaResponse.setType(MediaType.PHOTO);
        MovieMediaResponse movieMediaResponse1 = new MovieMediaResponse();
        movieMediaResponse1.setType(MediaType.VIDEO);
        // Add the two previously generated objects to a list
        List<MovieMediaResponse> movieMediaResponses = new ArrayList<>(
                Arrays.asList(
                        movieMediaResponse,
                        movieMediaResponse1
                )
        );
        // Add the list to the current movieResponse object
        this.movieResponse.setMovieMedia(movieMediaResponses);
        this.collector.checkThat("The size of photo list should be 1.", this.movieResponse.getPhotos(), hasSize(1));
        this.collector.checkThat("The size of video list should be 1.", this.movieResponse.getVideos(), hasSize(1));
    }

    @After
    public void tearDown() {
        this.movieResponse = null;
    }
}
