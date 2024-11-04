package org.diff;


import org.diff.access.PropertyAccess;

import java.util.LinkedList;
import java.util.List;

public class TypeInfo {
    private Class<?> type;
    private List<PropertyAccess> accesses = new LinkedList<>();


    public TypeInfo(Class<?> type) {
        if (type == null) {
            throw new RuntimeException("type is not null");
        }
        this.type = type;
    }

    public List<PropertyAccess> accesses() {
        return accesses;
    }

    public void setAccess(PropertyAccess access) {
        this.accesses.add(access);
    }
}
