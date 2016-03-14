package com.qtt.jinrong;

import com.qtt.framework.util.LogUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        Float f = 1.0f;

        int amount = (int)(f/100*100000*12);

        System.out.print("TESTJAVA="+amount);
    }
}