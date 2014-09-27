package com.omaha.hibernate;

import org.junit.Test;

import java.util.List;

import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;
import static org.junit.Assert.assertEquals;

public class ValidationExampleTest {
    private final int attempts = 100;
    private final ValidationExample example = new ValidationExample();

    @Test
    public void testValidateMethodWithListOfEntities() {
        final List<Boolean> results = generate(example::listOfEntities).limit(attempts).collect(toList());
        assertResultsUniformity(results);
    }

    @Test
    public void testValidateMethodWithListOfPrimitives() {
        final List<Boolean> results = generate(example::listOfPrimitives).limit(attempts).collect(toList());
        assertResultsUniformity(results);
    }

    private void assertResultsUniformity(final List<Boolean> results) {
        assertEquals("size mismatch", attempts, results.size());
        assertEquals("validated mismatch", 0, results.stream().filter(isEqual(true)).count());
    }
}
