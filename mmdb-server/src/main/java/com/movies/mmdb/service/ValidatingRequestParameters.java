package com.movies.mmdb.service;

import com.movies.mmdb.exception.BadRequestException;

import java.lang.reflect.Field;
import java.util.List;

import static com.movies.mmdb.util.ApplicationConstants.MAX_PAGE_SIZE;

/**
 * This class check the validation of the parameters in an endpoint url.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public interface ValidatingRequestParameters {

    /**
     * Check if the size and the page number parameters are valid.
     * <p>
     * The page number should accept integer only, and should be greater than 0.<br>
     * The size should be an integer, and should be between 0 and
     * {@link com.movies.mmdb.util.ApplicationConstants#MAX_PAGE_SIZE}
     * @param page the page number
     * @param size the size of each page
     */
    static void validatePageNumberAndSize(String page, String size) {
        try {
            int PageInt = Integer.parseInt(page);
            if(PageInt < 0) {
                throw new BadRequestException("Page number cannot be less than zero.");
            }
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Page parameter accept integer only, '" + page + "' is not an integer.");
        }

        try {
            int sizeInt = Integer.parseInt(size);
            if(sizeInt > MAX_PAGE_SIZE ) {
                throw new BadRequestException("Page size must not be greater than " + MAX_PAGE_SIZE + ".");
            }
            if(sizeInt < 0) {
                throw new BadRequestException("Page size must not be lower than 0.");
            }
        } catch (NumberFormatException ex) {
            throw new BadRequestException("size parameter accept integer only, '" + size + "' is not an integer.");
        }
    }

    /**
     * Check if the sort and the direction parameters are valid.
     * <p>
     * Check if the sort parameter value is equal one of the object fields.<br>
     * Check if the direction is either <b>"asc"</b> or <b>"desc"</b>
     * @param sort the sort of the page
     * @param direction the direction of the sort
     * @param clazz the object
     */
    static void validateSortAndDirection(String sort, String direction, Class<?> clazz) {
        boolean fieldFound = false;
        for(Field field: clazz.getDeclaredFields()) {
            if(field.getName().equals(sort) && field.getType() != List.class) {
                fieldFound = true;
                break;
            }
        }
        if(!fieldFound) {
            for (Field field : clazz.getSuperclass().getDeclaredFields()) {
                if (field.getName().equals(sort) && field.getType() != List.class) {
                    fieldFound = true;
                    break;
                }
            }
        }

        if(!fieldFound) {
            throw new BadRequestException("You can't sort by '" + sort + "', please choose a valid field to sort with.");
        }

        if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new BadRequestException("The direction parameter can be 'asc' or 'desc'.");
        }
    }

    /**
     * Check if the id parameter is valid.
     * <p>
     * The id should be an integer, otherwise <code>BadRequestException</code> will be thrown
     * @param requestId the id field
     */
    static void validateId(String requestId) {
        try {
            Integer.parseInt(requestId);
        } catch (NumberFormatException ex) {
            throw new BadRequestException("size parameter accept integer only, '" + requestId + "' is not an integer.");
        }
    }
}
