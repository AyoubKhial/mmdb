package com.movies.mmdb.util;

/**
 * This class is used to set default values were using in the <code>Http</code> requests.
 * <p>
 *  We have three constant : <br>
 * {@link #DEFAULT_PAGE_NUMBER} = {@value #DEFAULT_PAGE_NUMBER} : The default page number when making a request to get a Page of object.<br>
 * {@link #DEFAULT_PAGE_SIZE} = {@value #DEFAULT_PAGE_SIZE} : The default page size when making a request to get a Page of object.<br>
 * {@link #MAX_PAGE_SIZE} = {@value #MAX_PAGE_SIZE} : The maximum size of a page when making a request to get a Page of object.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public final class ApplicationConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
    public static final int MAX_PAGE_SIZE = 50;

    // restrict instantiation
    private ApplicationConstants() {}
}
