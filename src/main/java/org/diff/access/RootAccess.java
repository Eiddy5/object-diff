package org.diff.access;

public class RootAccess implements Access {
    private static final Access INSTANCE = new RootAccess();

    public static Access getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(Object target) {
        return target;
    }
}
