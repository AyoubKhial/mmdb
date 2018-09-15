package com.movies.mmdb.rule;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class is extended by test classes which have at least one method expect to throw exception.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public abstract class ExpectedExceptionRule {

    /**
     * This rule allows you to verify that your code throws a specific exception.
     * <p>
     * the <code>@Rule</code> annotation offers a generic way to add extended features on a test method
     * @see ExpectedException
     * @see Rule
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
}
