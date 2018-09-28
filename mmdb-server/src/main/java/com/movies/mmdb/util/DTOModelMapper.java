package com.movies.mmdb.util;

import com.movies.mmdb.dto.CelebrityResponse;
import com.movies.mmdb.dto.MovieMediaResponse;
import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.dto.MovieReviewResponse;
import com.movies.mmdb.model.Celebrity;
import com.movies.mmdb.model.Movie;
import com.movies.mmdb.model.MovieMedia;
import com.movies.mmdb.model.MovieReview;
import org.modelmapper.ModelMapper;

/**
 * This class is use to map an Model(Entity) to a DTO class.
 * <p>
 * We will be using <code>ModelMapper</code> library To avoid having to write cumbersome/boilerplate code to map
 * entities into DTOs.
 * <p>
 * The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to
 * another.
 * @author Ayoub Khial
 * @version 1.0
 * @see ModelMapper
 * @since 1.0
 *
 */
public interface DTOModelMapper {

    /**
     * Map a <code>Movie</code> to a <code>MovieResponse</code> using <code>ModelMapper</code> library.
     * @param movie a Movie Object we want to map DTO from.
     * @return the generated <code>MovieResponse</code> from <code>Movie</code>.
     * @see Movie
     * @see MovieResponse
     */
    static MovieResponse mapMovieToMovieResponse(Movie movie) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieResponse.class);
    }

    /**
     * Map a <code>MovieMedia</code> to a <code>MovieMediaResponse</code> using <code>ModelMapper</code> library.
     * @param movieMedia a MovieMedia Object we want to map DTO from.
     * @return the generated <code>MovieMediaResponse</code> from <code>MovieMedia</code>.
     * @see MovieMedia
     * @see MovieMediaResponse
     */
    static MovieMediaResponse mapMovieMediaToMovieMediaResponse(MovieMedia movieMedia) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieMedia, MovieMediaResponse.class);
    }

    /**
     * Map a <code>MovieReview</code> to a <code>MovieReviewResponse</code> using <code>ModelMapper</code> library.
     * @param movieReview a MovieReview Object we want to map DTO from.
     * @return the generated <code>MovieReviewResponse</code> from <code>MovieReview</code>.
     * @see MovieReview
     * @see MovieReviewResponse
     */
    static MovieReviewResponse mapMovieReviewToMovieReviewResponse(MovieReview movieReview) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieReview, MovieReviewResponse.class);
    }

    /**
     * Map a <code>Celebrity</code> to a <code>CelebrityResponse</code> using <code>ModelMapper</code> library.
     * @param celebrity a Celebrity Object we want to map DTO from.
     * @return the generated <code>CelebrityResponse</code> from <code>Celebrity</code>.
     * @see Celebrity
     * @see CelebrityResponse
     */
    static CelebrityResponse mapCelebrityToCelebrityResponse(Celebrity celebrity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(celebrity, CelebrityResponse.class);
    }
}
