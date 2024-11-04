package org.diff;


import org.diff.access.Access;
import org.diff.access.RootAccess;
import org.diff.access.TypeAwareAccess;
import org.diff.utils.Classes;
import org.diff.utils.Collections;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Instances {
    private final Access sourceAccess;
    private final Object working;
    private final Object base;


    public Instances(Access sourceAccess, Object base, Object working) {
        this.sourceAccess = sourceAccess;
        this.working = working;
        this.base = base;
    }

    public static <T> Instances of(
            final T base,
            final T working) {
        return new Instances(RootAccess.getInstance(), base, working);
    }

    public boolean hasBeenAdded() {
        return base == null && working != null;
    }

    public boolean hasBeenRemoved() {
        return base != null && working == null;
    }

    public Object getWorking() {
        return working;
    }

    public Object getBase() {
        return base;
    }

    public Access getSourceAccess() {
        return sourceAccess;
    }

    public boolean isSame() {
        return Objects.equals(base, working);
    }

    public boolean isNull() {
        return base == null && working == null;
    }

    public Class<?> getType() {
        final Set<Class<?>> types = Classes.typesOf(working, base);
        final Class<?> sourceAccessorType = tryToGetTypeFromSourceAccessor();
        if (Classes.isPrimitiveType(sourceAccessorType)) {
            return sourceAccessorType;
        }
        if (types.isEmpty()) {
            return null;
        }
        if (types.size() == 1) {
            return Collections.firstElementOf(types);
        }
        if (Classes.allAssignableFrom(Collection.class, types)) {
            return Collection.class;
        } else if (Classes.allAssignableFrom(Map.class, types)) {
            return Map.class;
        }
        return Object.class;
    }

    private Class<?> tryToGetTypeFromSourceAccessor() {
        if (sourceAccess instanceof TypeAwareAccess) {
            return ((TypeAwareAccess) sourceAccess).getType();
        }
        return null;
    }


    public Instances access(Access access) {
        return new Instances(access, access.get(working), access.get(base));
    }
}
