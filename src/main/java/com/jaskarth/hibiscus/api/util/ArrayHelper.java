package com.jaskarth.hibiscus.api.util;

public final class ArrayHelper {
    public static <T> boolean contains(T[] array, T value) {
        for (T t : array) {
            if (t.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
