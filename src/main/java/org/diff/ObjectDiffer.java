package org.diff;

import org.diff.access.RootAccess;
import org.diff.differ.BeanDiffer;
import org.diff.differ.DifferDispatcher;
import org.diff.differ.DifferProvider;
import org.diff.resolver.IsReturnableResolver;
import org.diff.resolver.TypeInfoResolver;

public class ObjectDiffer {

    private final TypeInfoResolver typeInfoResolver = new TypeInfoResolver();
    private final IsReturnableResolver isReturnableResolver = new IsReturnableResolver();
    private final DifferDispatcher differDispatcher;

    public ObjectDiffer() {
        differDispatcher = newDifferDispatcher();
    }

    public static ObjectDiffer use() {
        return new ObjectDiffer();

    }

    public DiffNode compare(Object base, Object working) {
        return differDispatcher.dispatch(DiffNode.ROOT, Instances.of(base, working), RootAccess.getInstance());
    }

    private DifferDispatcher newDifferDispatcher() {
        DifferProvider differProvider = new DifferProvider();
        DifferDispatcher differDispatcher = newDifferDispatcher(differProvider);
        differProvider.push(newBeanDiffer(differDispatcher));
        return differDispatcher;
    }

    private DifferDispatcher newDifferDispatcher(DifferProvider differProvider) {
        return new DifferDispatcher(differProvider, isReturnableResolver);
    }

    private BeanDiffer newBeanDiffer(DifferDispatcher differDispatcher) {
        return new BeanDiffer(differDispatcher, typeInfoResolver);
    }
}
