package com.movies.mmdb.rule;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import com.movies.mmdb.dto.UserResponse;
/**
 * This class is extended by test classes which have at least one method with multiple assertions.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public abstract class ErrorCollectorRule {

    /**
     * Enable a test not to stop on an error by doing all assertions and listing the failed ones at the end.
     * the <code>@Rule</code> annotation offers a generic way to add extended features on a test method
     */
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();
}
