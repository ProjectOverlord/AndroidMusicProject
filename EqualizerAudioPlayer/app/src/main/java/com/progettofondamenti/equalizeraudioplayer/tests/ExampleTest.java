package com.progettofondamenti.equalizeraudioplayer.tests;

import android.test.InstrumentationTestCase;

/**
 * Simple example class for JUnit test
 * Created by francesco on 13/05/15.
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
