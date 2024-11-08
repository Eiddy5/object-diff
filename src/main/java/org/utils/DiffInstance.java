package org.utils;

public class DiffInstance {
    private Object base;
    private Object working;

    public static DiffInstance of(Object base, Object working) {
        return new DiffInstance(base, working);
    }

    public DiffInstance(Object base, Object working) {
        this.base = base;
        this.working = working;
    }

    public void setBase(Object base) {
        this.base = base;
    }

    public void setWorking(Object working) {
        this.working = working;
    }

    public Object getBase() {
        return base;
    }

    public Object getWorking() {
        return working;
    }
}
