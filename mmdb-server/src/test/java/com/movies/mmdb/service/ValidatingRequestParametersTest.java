package com.movies.mmdb.service;

import com.movies.mmdb.rule.ExpectedExceptionRule;
import com.movies.mmdb.exception.BadRequestException;
import com.movies.mmdb.model.Movie;
import org.junit.Test;

/**
 * A test class to test <code>ValidatingRequestParameters</code> class.
 * <p>
 * Extending <code>ExpectedExceptionRule</code> so we can test that an exception is thrown, also to test
 * the message returned by the exception
 * @author Ayoub Khial
 * @version 1.0
 * @see ValidatingRequestParametersTest
 * @since 1.0
 */
public class ValidatingRequestParametersTest extends ExpectedExceptionRule {

    /**
     * A test case for <code>validatePageNumberAndSize</code> Method.
     * @see ValidatingRequestParameters#validatePageNumberAndSize(String, String)
     */
    @Test
    public void ValidatePageNumberAndSize_NegativePageNumber_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("Page number cannot be less than zero.");
        ValidatingRequestParameters.validatePageNumberAndSize("-1", "20");
    }

    /**
     * A test case for <code>validatePageNumberAndSize</code> Method.
     * @see ValidatingRequestParameters#validatePageNumberAndSize(String, String)
     */
    @Test
    public void ValidatePageNumberAndSize_NonIntegerPageNumber_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("Page parameter accept integer only, 'a' is not an integer.");
        ValidatingRequestParameters.validatePageNumberAndSize("a", "20");
    }

    /**
     * A test case for <code>validatePageNumberAndSize</code> Method.
     * @see ValidatingRequestParameters#validatePageNumberAndSize(String, String)
     */
    @Test
    public void ValidatePageNumberAndSize_PageSizeGreaterThanMax_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("Page size must not be greater than 50.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "80");
    }

    /**
     * A test case for <code>validatePageNumberAndSize</code> Method.
     * @see ValidatingRequestParameters#validatePageNumberAndSize(String, String)
     */
    @Test
    public void ValidatePageNumberAndSize_NegativePageSize_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("Page size must not be lower than 0.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "-1");
    }

    /**
     * A test case for <code>validatePageNumberAndSize</code> Method.
     * @see ValidatingRequestParameters#validatePageNumberAndSize(String, String)
     */
    @Test
    public void ValidatePageNumberAndSize_NonIntegerPageSize_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("size parameter accept integer only, '2.3' is not an integer.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "2.3");
    }

    /**
     * A test case for <code>validateSortAndDirection</code> Method.
     * @see ValidatingRequestParameters#validateSortAndDirection(String, String, Class)
     */
    @Test
    public void ValidateSortAndDirection_SortFieldNotExist_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("You can't sort by 'size', please choose a valid field to sort with.");
        ValidatingRequestParameters.validateSortAndDirection("size","desc", Movie.class);
    }

    /**
     * A test case for <code>validateSortAndDirection</code> Method.
     * @see ValidatingRequestParameters#validateSortAndDirection(String, String, Class)
     */
    @Test
    public void ValidateSortAndDirection_InvalidSortDirection_ShouldThrowBadRequestException() {
        this.expectedException.expect(BadRequestException.class);
        this.expectedException.expectMessage("The direction parameter can be 'asc' or 'desc'.");
        ValidatingRequestParameters.validateSortAndDirection("rating","fromTop", Movie.class);
    }
}
