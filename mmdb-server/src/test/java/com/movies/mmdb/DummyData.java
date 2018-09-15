package com.movies.mmdb;

import com.movies.mmdb.model.*;

import java.sql.Time;
import java.util.*;

/**
 * This class contain some dummy data which will be used in the test
 */
public final class DummyData {

    /**
     * @return a movie
     * @see Movie
     */
    public static Movie dummyMovie() {
        // create date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1983);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 9);
        Date movieDate = cal.getTime();

        // create time
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 2);
        cal2.set(Calendar.MINUTE, 50);
        cal2.set(Calendar.SECOND, 0);
        int hour = cal2.get(Calendar.HOUR_OF_DAY);
        int minute = cal2.get(Calendar.MINUTE);
        int second = cal2.get(Calendar.SECOND);
        Time movieTime = Time.valueOf(hour + ":" + minute + ":" + second);

        // create the movie
        Movie movie = new Movie(1L, "Scarface", movieDate, movieTime, 8.3F,
                "This is the storyline.", "picture.png", "R");
        movie.setGenres(new ArrayList<>(Collections.singletonList(dummyGenre())));
        movie.setMovieMedia(new ArrayList<>(Collections.singletonList(dummyMovieMedia())));
        movie.setMovieReviews(new ArrayList<>(Collections.singletonList(dummyMovieReview())));
        movie.setMovieCelebrities(new ArrayList<>(Collections.singletonList(dummyMovieCelebrity())));
        return movie;
    }

    /**
     * @return a genre
     * @see Genre
     */
    public static Genre dummyGenre() {
        return new Genre(1L, "Drama");
    }

    /**
     * @return a movie media
     * @see MovieMedia
     */
    public static MovieMedia dummyMovieMedia() {
        return new MovieMedia(1L, "photo1.jpg", MediaType.PHOTO);
    }

    /**
     * @return a movie review
     * @see MovieReview
     */
    public static MovieReview dummyMovieReview() {
        return new MovieReview(1L, 10, "Amazing movie", "Best movie ever.");
    }

    /**
     * @return a movie celebrity
     * @see MovieCelebrity
     */
    public static MovieCelebrity dummyMovieCelebrity() {
        return new MovieCelebrity(1L, true, CelebrityRole.ACTOR, "Tony Montana", dummyCelebrity());
    }

    /**
     * @return a celebrity
     * @see Celebrity
     */
    public static Celebrity dummyCelebrity() {
        return new Celebrity(1L, "Al Pacino", "photo1.png", new Date(1940), "");
    }
}
