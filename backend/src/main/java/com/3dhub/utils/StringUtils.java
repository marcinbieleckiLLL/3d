package com.greencrane.utils;

/**
 * Created by Marcin on 26.09.2018.
 */
public interface StringUtils {
    static boolean isEmpty(String s) {
        return s == null ? true : s.isEmpty();
    }
}
