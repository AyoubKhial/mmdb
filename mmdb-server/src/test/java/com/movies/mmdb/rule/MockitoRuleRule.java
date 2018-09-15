package com.movies.mmdb.rule;

import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

/**
 * This class is extended by test classes which need to use mockito as a test runner.
 * @author Ayoub Khial
 * @version 1.0
 * @since 1.0
 */
public abstract class MockitoRuleRule {

    /**
     * This rule allows you to verify that your code throws a specific exception.
     * <p>
     * the <code>@Rule</code> annotation offers a generic way to add extended features on a test method.<br>
     * The strict stubs rule helps to keep the test code clean and checks for common oversights
     * @see MockitoRule
     * @see Rule
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
}
