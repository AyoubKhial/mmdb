package com.movies.mmdb;

import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.model.*;
import com.movies.mmdb.service.impl.MovieServiceImpl;
import com.movies.mmdb.util.DTOModelMapper;
import com.movies.mmdb.util.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.sql.Time;
import java.util.*;

/**
 * This class contain some dummy data which will be used in the test
 */
public final class DummyData {

    public static Movie dummyMovie() {
        // create date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1983);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 9);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
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
        movie.setMovieCelebrities(new ArrayList<>(Collections.singletonList(dummyMovieCelebrity())));
        movie.setCreatedAt(movieDate);
        return movie;
    }

    public static MovieResponse dummyMovieResponse() {
        return DTOModelMapper.mapMovieToMovieResponse(dummyMovie());
    }

    public static Page<Movie> dummyMoviePage() {
        List<Movie> movieList = new ArrayList<>(Collections.singletonList(dummyMovie()));
        return MovieServiceImpl.removeUndesirableFields(new PageImpl<>(movieList));
    }

    public static PagedResponse<MovieResponse> dummyPagedMovieResponse() {
        List<MovieResponse> movieResponseList = dummyMoviePage().map(DTOModelMapper::mapMovieToMovieResponse).getContent();
        return new PagedResponse<>(movieResponseList, dummyMoviePage().getNumber(), dummyMoviePage().getSize(),
                dummyMoviePage().getTotalElements(), dummyMoviePage().getTotalPages(), dummyMoviePage().isLast());
    }

    public static Genre dummyGenre() {
        return new Genre(1L, "Drama");
    }

    public static MovieCelebrity dummyMovieCelebrity() {
        return new MovieCelebrity(1L, true, CelebrityRole.ACTOR, "Tony Montana", dummyCelebrity());
    }

    public static MovieMedia dummyMovieMedia() {
        return new MovieMedia(1L, "photo.png", MediaType.PHOTO);
    }

    public static Page<MovieMedia> dummyMovieMediaPage() {
        List<MovieMedia> movieMediaList = new ArrayList<>(Collections.singletonList(dummyMovieMedia()));
        return new PageImpl<>(movieMediaList);
    }

    public static PagedResponse<MovieMediaResponse> dummyPagedMovieMediaResponse() {
        List<MovieMediaResponse> movieMediaResponseList = dummyMovieMediaPage().map(DTOModelMapper::mapMovieMediaToMovieMediaResponse).getContent();
        return new PagedResponse<>(movieMediaResponseList, dummyMovieMediaPage().getNumber(), dummyMovieMediaPage().getSize(),
                dummyMovieMediaPage().getTotalElements(), dummyMovieMediaPage().getTotalPages(), dummyMovieMediaPage().isLast());
    }

    public static MovieReview dummyMovieReview() {
        // create & updated date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1983);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 9);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date reviewDate = cal.getTime();
        return new MovieReview(10, "Amazing movie.", "The best performance by AlPacino", dummyUser(), reviewDate, reviewDate);
    }

    public static Page<MovieReview> dummyMovieReviewPage() {
        List<MovieReview> movieReviewList = new ArrayList<>(Collections.singletonList(dummyMovieReview()));
        return new PageImpl<>(movieReviewList);
    }

    public static PagedResponse<MovieReviewResponse> dummyPagedMovieReviewResponse() {
        List<MovieReviewResponse> movieReviewResponseList = dummyMovieReviewPage().map(DTOModelMapper::mapMovieReviewToMovieReviewResponse).getContent();
        return new PagedResponse<>(movieReviewResponseList, dummyMovieReviewPage().getNumber(), dummyMovieReviewPage().getSize(),
                dummyMovieReviewPage().getTotalElements(), dummyMovieReviewPage().getTotalPages(), dummyMovieReviewPage().isLast());
    }

    public static User dummyUser() {
        return new User(1L, "Ayoub", "Ayoub@gmail.com", "Ayoub", "Khial", "123456", "picture.png");
    }

    public static Celebrity dummyCelebrity() {
        return new Celebrity(1L, "Al Pacino", "photo1.png", null, null);
    }

    public static Page<Celebrity> dummyCelebrityPage() {
        List<Celebrity> celebrityList = new ArrayList<>(Collections.singletonList(dummyCelebrity()));
        return new PageImpl<>(celebrityList);
    }

    public static PagedResponse<CelebrityResponse> dummyPagedCelebrityResponse() {
        List<CelebrityResponse> celebrityResponseList = dummyCelebrityPage().map(DTOModelMapper::mapCelebrityToCelebrityResponse).getContent();
        return new PagedResponse<>(celebrityResponseList, dummyCelebrityPage().getNumber(), dummyCelebrityPage().getSize(),
                dummyCelebrityPage().getTotalElements(), dummyCelebrityPage().getTotalPages(), dummyCelebrityPage().isLast());
    }

    public static CelebrityResponse dummyCelebrityResponse() {
        return DTOModelMapper.mapCelebrityToCelebrityResponse(dummyCelebrity());
    }
}
