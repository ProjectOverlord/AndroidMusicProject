package com.progettofondamenti.audioplayer.tests;

import android.test.InstrumentationTestCase;

/**
 * Simple example class for JUnit test
 * @author team
 * @see android.test.InstrumentationTestCase
 */
public class ExampleTest extends InstrumentationTestCase {

    /* test di prova */
    public void test() throws Exception {
        final int expected = 1;
        //final int reality = 5; if you use this the test will fail
        final int reality = 1; // if you use this the test will naturally be positive
        assertEquals(expected, reality);
    }
}
