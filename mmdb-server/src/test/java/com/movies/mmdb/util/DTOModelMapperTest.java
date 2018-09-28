package com.movies.mmdb.util;

import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.model.MediaType;
import com.movies.mmdb.model.Movie;
import com.movies.mmdb.model.MovieMedia;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.sql.Time;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class DTOModelMapperTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void testMapMovieToMovieResponse() {
        Movie movie = new Movie(1L, "The Shawshank Redemption", new Date(1994), new Time(2), 9.3F,
                "This is The storyline", "poster.png", "R");

        MovieResponse movieResponse = DTOModelMapper.mapMovieToMovieResponse(movie);

        this.errorCollector.checkThat("The movie id should be 1", movieResponse.getId(), is(1L));
        this.errorCollector.checkThat("Mismatch movie name", movieResponse.getName(), is(equalTo("The Shawshank Redemption")));
        this.errorCollector.checkThat("The movie rating should be 9.3", movieResponse.getRating(), is(9.3F));
        this.errorCollector.checkThat("Mismatch movie storyline", movieResponse.getStoryline(), is(equalTo("This is The storyline")));
        this.errorCollector.checkThat("Mismatch movie poster", movieResponse.getPoster(), is(equalTo("poster.png")));
        this.errorCollector.checkThat("The movie Rated should be 'R'", movieResponse.getRated(), is(equalTo("R")));
    }

    @Test
    public void mapMovieMediaToMovieMediaResponse() {
        MovieMedia movieMedia = new MovieMedia(1L, "photo.png", MediaType.PHOTO);
        MovieMediaResponse movieMediaResponse = DTOModelMapper.mapMovieMediaToMovieMediaResponse(movieMedia);
        this.errorCollector.checkThat("Mismatch media url", movieMediaResponse.getUrl(), is(equalTo("photo.png")));
        this.errorCollector.checkThat("The movie rating should be 9.3", movieMediaResponse.getType(), is(MediaType.PHOTO));
    }
}
