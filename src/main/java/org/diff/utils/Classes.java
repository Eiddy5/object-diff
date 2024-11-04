package org.diff.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Classes {

    public static Set<Class<?>> typesOf(Object... values) {
        HashSet<Class<?>> types = new HashSet<>();
        for (Object value : values) {
            if (value != null) {
                types.add(value.getClass());
            }
        }
        return types;
    }

    public static boolean isPrimitiveType(Class<?> sourceAccessorType) {
        return sourceAccessorType != null && sourceAccessorType.isPrimitive();
    }

    public static boolean allAssignableFrom(Class<?> clazz, Set<Class<?>> types) {
        if (types == null || types.isEmpty()) return false;
        for (Class<?> type : types) {
            if (!type.isAssignableFrom(clazz)) return false;
        }
        return true;
    }
}
