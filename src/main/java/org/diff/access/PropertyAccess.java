package org.diff.access;


import java.lang.reflect.Field;

public class PropertyAccess implements PropertyAwareAccess {
    private String propertyName;
    private Class<?> type;

    public PropertyAccess(String propertyName, Class<?> type) {
        this.propertyName = propertyName;
        this.type = type;
    }

    @Override
    public Object get(Object target) {
        try {
            Field field = target.getClass().getField(propertyName);
            return field.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }
}
