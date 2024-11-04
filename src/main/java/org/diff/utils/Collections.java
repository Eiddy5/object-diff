package org.diff.utils;

import java.util.Collection;

public class Collections {
    public static <T> T firstElementOf(Collection<? extends T> items) {
        if (items == null || items.isEmpty()) return null;
        return items.iterator().next();
    }
}
