package com.movies.mmdb.dto;

/**
 * <code>GenreResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.Genre}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.Genre}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.Genre
 * @since 1.0
 */
public class GenreResponse {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
