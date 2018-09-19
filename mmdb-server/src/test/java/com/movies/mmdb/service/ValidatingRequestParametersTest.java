package com.movies.mmdb.service;

import com.movies.mmdb.exception.BadRequestException;
import com.movies.mmdb.model.Movie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ValidatingRequestParametersTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this.expectedException.expect(BadRequestException.class);
    }

    @Test
    public void ValidatePageNumberAndSize_NegativePageNumber_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page number cannot be less than zero.");
        ValidatingRequestParameters.validatePageNumberAndSize("-1", "20");
    }

    @Test
    public void ValidatePageNumberAndSize_NonIntegerPageNumber_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page parameter accept integer only, 'a' is not an integer.");
        ValidatingRequestParameters.validatePageNumberAndSize("a", "20");
    }

    @Test
    public void ValidatePageNumberAndSize_PageSizeGreaterThanMax_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be greater than 50.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "80");
    }

    @Test
    public void ValidatePageNumberAndSize_NegativePageSize_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("Page size must not be lower than 0.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "-1");
    }

    @Test
    public void ValidatePageNumberAndSize_NonIntegerPageSize_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("size parameter accept integer only, '2.3' is not an integer.");
        ValidatingRequestParameters.validatePageNumberAndSize("0", "2.3");
    }

    @Test
    public void ValidateSortAndDirection_SortFieldNotExist_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("You can't sort by 'size', please choose a valid field to sort with.");
        ValidatingRequestParameters.validateSortAndDirection("size","desc", Movie.class);
    }

    @Test
    public void ValidateSortAndDirection_InvalidSortDirection_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("The direction parameter can be 'asc' or 'desc'.");
        ValidatingRequestParameters.validateSortAndDirection("rating","fromTop", Movie.class);
    }

    @Test
    public void ValidateId_InvalidId_ShouldThrowBadRequestException() {
        this.expectedException.expectMessage("size parameter accept integer only, 'a' is not an integer.");
        ValidatingRequestParameters.validateId("a");
    }
}
