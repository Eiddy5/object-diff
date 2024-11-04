package org.diff;

public interface Change {
    String getPropertyName();
    Class<?> getPropertyClass();
    Object getOldValue();

    Object getNewValue();
}
