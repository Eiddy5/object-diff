package org.utils;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

import java.util.Optional;

public class ObjectDiffer<T> {
    private final Javers javers;
    private final Class<T> clazz;

    public static <T> ObjectDiffer<T> use(Class<T> clazz) {
        if (clazz == null) throw new RuntimeException("clazz is null");
        return new ObjectDiffer<>(clazz);
    }

    public ObjectDiffer(Class<T> clazz) {
        this.clazz = clazz;
        this.javers = JaversBuilder.javers().build();
    }

    public T compare(T base, T working) {
        if (base == null && working != null) {
            return working;
        }
        Diff diff = javers.compare(base, working);
        diff.getChanges().forEach(change -> {
            if (change instanceof ValueChange valueChange) {
                Optional<Object> affectedObject = valueChange.getAffectedObject();
                affectedObject.ifPresent(o->{

                });
                String propertyName = valueChange.getPropertyName();
                Object left = valueChange.getLeft();
                Object right = valueChange.getRight();
            }
        });
        return working;
    }

}
