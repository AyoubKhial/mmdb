package com.movies.mmdb.dto;

import com.movies.mmdb.rule.ErrorCollectorRule;
import com.movies.mmdb.model.CelebrityRole;
import com.movies.mmdb.model.MediaType;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;

/**
 * A test class to test <code>MovieResponse</code> class
 * <p>
 * Extending <code>ErrorCollectorRule</code> to make sure we can use multiple assertions in single method and get
 * all the failure assertions
 * @author Ayoub Khial
 * @version 1.0
 * @see ErrorCollectorRule
 * @see MovieResponse
 * @since 1.0
 */
public class MovieResponseTest extends ErrorCollectorRule {

    private MovieResponse movieResponse;

    @Before
    public void setUp() {
        // Initialize the movieResponse Object
        this.movieResponse = new MovieResponse();
    }

    /**
     * A test case for <code>setCelebrity</code> method.
     * <p>
     * The given <code>MovieCelebrityResponse</code> list should be divided into three list based on the
     * <code>CelebrityRole</code>.<br>
     * If the <code>CelebrityRole</code> is <b>ACTOR</b> the <code>MovieCelebrityResponse</code> should
     * be stored in <code>cast</code> list.<br>
     * If the <code>CelebrityRole</code> is <b>DIRECTOR</b> the <code>MovieCelebrityResponse</code> should
     * be stored in <code>directors</code> list.<br>
     * If the <code>CelebrityRole</code> is <b>WRITER</b> the <code>MovieCelebrityResponse</code> should
     * be stored in <code>writers</code> list.
     * @see MovieResponse#setCelebrities(List)
     */
    @Test
    public void testSetCelebrities_MovieCelebrityResponseList_ShouldDivideTheList() {
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
        this.errorCollector.checkThat("The size of cast list should be 1.", this.movieResponse.getCast(), hasSize(1));
        this.errorCollector.checkThat("The size of directors list should be 1.", this.movieResponse.getDirectors(), hasSize(1));
        this.errorCollector.checkThat("The size of writers list should be 1.", this.movieResponse.getWriters(), hasSize(1));
    }

    /**
     * A test case for <code>setMovieMedia</code> method.
     * <p>
     * The given <code>MovieMediaResponse</code> list should be divided into two list based on the
     * <code>MediaType</code>.<br>
     * If the <code>MediaType</code> is <b>PHOTO</b> the <code>MovieMediaResponse</code> should
     * be stored in <code>photos</code> list.<br>
     * If the <code>MediaType</code> is <b>VIDEO</b> the <code>MovieMediaResponse</code> should
     * be stored in <code>videos</code> list.
     * @see MovieResponse#setMovieMedia(List)
     */
    @Test
    public void testSetMovieMedia_MovieMediaResponseList_ShouldDivideTheList() {
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
        this.errorCollector.checkThat("The size of photo list should be 1.", this.movieResponse.getPhotos(), hasSize(1));
        this.errorCollector.checkThat("The size of video list should be 1.", this.movieResponse.getVideos(), hasSize(1));
    }

    @After
    public void tearDown() {
        this.movieResponse = null;
    }
}
