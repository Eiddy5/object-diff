package org.utils;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import java.util.Optional;

public class DiffUtil {
    private static final Javers javers = JaversBuilder.javers().build();

    public static <T> T compare(T base, T working) {
        Diff diff = javers.compare(base, working);
        diff.getChanges().forEach(d -> {
            Optional<Object> object = d.getAffectedObject();
        });
        return base;
    }

}
