package com.greencrane.utils;

import java.util.List;

public interface ListUtils {
    static boolean isEmpty(List<?> list) {
        return list == null ? true : list.isEmpty();
    }
    static boolean containsIgnoreCase(List<?> list, String s) {
        return list.stream().filter(l -> String.valueOf(l).equalsIgnoreCase(s)).findFirst().isPresent();
    }
}
