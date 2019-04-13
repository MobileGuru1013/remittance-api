package com.voteva.remittance.utils;

import java.util.Collection;

/**
 * Utils for collections
 */
public class CollectionUtils {

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
}
