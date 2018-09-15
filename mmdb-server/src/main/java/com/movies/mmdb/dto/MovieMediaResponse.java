package com.movies.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movies.mmdb.model.MediaType;

import java.util.Objects;

/**
 * <code>MovieMediaResponse</code> is a DTO class which have the all the
 * <code>{@link com.movies.mmdb.model.MovieMedia}</code> fields we want to expose in the view.
 * <p>
 * The main reason for using this class(DTO) is to batch up what would be multiple remote calls into a single one.<br>
 * Also it can help hiding implementation details of <code>{@link com.movies.mmdb.model.MovieMedia}</code>.
 * Exposing entities through endpoints can become a security issue if we do not carefully handle what properties can
 * be changed through what operations.
 * @author Ayoub Khial
 * @version 1.0
 * @see com.movies.mmdb.model.MovieMedia
 * @since 1.0
 */
public class MovieMediaResponse {

    private String url;
    private MediaType type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieMediaResponse that = (MovieMediaResponse) o;
        return Objects.equals(url, that.url) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, type);
    }
}
