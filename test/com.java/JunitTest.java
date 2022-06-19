package com.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitTest {

    @Test
    public void testAdd() {
        Assertions.assertEquals(2, Integer.sum(1,1));
    }
}
