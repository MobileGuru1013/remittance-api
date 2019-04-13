package com.voteva.remittance.utils;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class CollectionUtilsTest {

    private List<?> nullList = null;
    private List<?> emptyList = new ArrayList<>();
    private List<?> fullList = Arrays.asList(new Object(), new Object());

    @Test
    public void testIsNotEmpty() {
        assertTrue(CollectionUtils.isNotEmpty(fullList));
    }

    @Test
    public void testIsNotEmpty_NullList() {
        assertFalse(CollectionUtils.isNotEmpty(nullList));
    }

    @Test
    public void testIsNotEmpty_EmptyList() {
        assertFalse(CollectionUtils.isNotEmpty(emptyList));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(CollectionUtils.isEmpty(fullList));
    }

    @Test
    public void testIsEmpty_NullList() {
        assertTrue(CollectionUtils.isEmpty(nullList));
    }

    @Test
    public void testIsEmpty_EmptyList() {
        assertTrue(CollectionUtils.isEmpty(emptyList));
    }
}