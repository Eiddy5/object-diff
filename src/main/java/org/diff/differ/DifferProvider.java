package org.diff.differ;


import java.util.ArrayList;
import java.util.List;

public class DifferProvider {
    private final List<Differ> differs = new ArrayList<>();


    public DifferProvider() {
    }

    public void push(Differ differ) {
        differs.add(differ);
    }

    public Differ retrieveDiffer(Class<?> type) {
        if (type == null) throw new RuntimeException("type is null");
        for (Differ differ : differs) {
            if (differ.accept(type)) {
                return differ;
            }
        }
        throw new RuntimeException("no differ found: %s".formatted(type.getName()));
    }

}
