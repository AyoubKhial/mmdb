package com.movies.mmdb.util;

import com.movies.mmdb.dto.MovieResponse;
import com.movies.mmdb.model.Movie;
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
public class DTOModelMapper {

    /**
     *
     * @param movie a movie Object we want to map DTO from.
     * @return the generated DTOs - <code>MovieResponse</code>
     * @see Movie
     * @see MovieResponse
     */
    public static MovieResponse mapMovieToMovieResponse(Movie movie) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieResponse.class);
    }
}
