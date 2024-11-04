package org.diff.resolver;


import org.diff.DiffNode;
import org.diff.TypeInfo;
import org.diff.access.PropertyAccess;

import java.lang.reflect.Field;

public class TypeInfoResolver {
    public TypeInfo typeInfoForNode(DiffNode node) {
        Class<?> type = node.getValueType();
        TypeInfo typeInfo = new TypeInfo(type);
        Field[] fields = type.getFields();
        for (Field field : fields) {
            PropertyAccess access = new PropertyAccess(field.getName(), field.getClass());
            typeInfo.setAccess(access);
        }
        return typeInfo;
    }
}
