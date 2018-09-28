package com.movies.mmdb.util;

import com.movies.mmdb.DummyData;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.model.MediaType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class DTOModelMapperTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void testMapMovieToMovieResponse() {
        MovieResponse movieResponse = DTOModelMapper.mapMovieToMovieResponse(DummyData.dummyMovie());

        this.errorCollector.checkThat("The movie id should be 1", movieResponse.getId(), is(1L));
        this.errorCollector.checkThat("Mismatch movie name", movieResponse.getName(), is(equalTo("Scarface")));
        this.errorCollector.checkThat("The movie rating should be 8.3", movieResponse.getRating(), is(8.3F));
        this.errorCollector.checkThat("Mismatch movie storyline", movieResponse.getStoryline(), is(equalTo("This is the storyline.")));
        this.errorCollector.checkThat("Mismatch movie poster", movieResponse.getPoster(), is(equalTo("picture.png")));
        this.errorCollector.checkThat("The movie Rated should be 'R'", movieResponse.getRated(), is(equalTo("R")));
    }

    @Test
    public void mapMovieMediaToMovieMediaResponse() {
        MovieMediaResponse movieMediaResponse = DTOModelMapper.mapMovieMediaToMovieMediaResponse(DummyData.dummyMovieMedia());

        this.errorCollector.checkThat("Mismatch media url", movieMediaResponse.getUrl(), is(equalTo("photo.png")));
        this.errorCollector.checkThat("The movie rating should be 9.3", movieMediaResponse.getType(), is(MediaType.PHOTO));
    }

    @Test
    public void mapMovieReviewToMovieReviewResponse() {
        MovieReviewResponse movieReviewResponse = DTOModelMapper.mapMovieReviewToMovieReviewResponse(DummyData.dummyMovieReview());

        this.errorCollector.checkThat("The review rating should be 10", movieReviewResponse.getRating(), is(equalTo(10)));
        this.errorCollector.checkThat("Mismatch review title", movieReviewResponse.getTitle(), is(equalTo("Amazing movie.")));
        this.errorCollector.checkThat("Mismatch review text", movieReviewResponse.getText(), is(equalTo("The best performance by AlPacino")));
        this.errorCollector.checkThat("Mismatch review user's username", movieReviewResponse.getUser().getUsername(), is(equalTo("Ayoub")));

    }
}
