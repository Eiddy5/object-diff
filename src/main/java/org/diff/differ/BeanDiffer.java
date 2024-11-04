package org.diff.differ;


import org.diff.DiffNode;
import org.diff.Instances;
import org.diff.TypeInfo;
import org.diff.access.PropertyAccess;
import org.diff.resolver.IsReturnableResolver;
import org.diff.resolver.TypeInfoResolver;

public class BeanDiffer implements Differ {

    private final DifferDispatcher differDispatcher;
    private final TypeInfoResolver typeInfoResolver;
    private final IsReturnableResolver isReturnableResolver;

    public BeanDiffer(DifferDispatcher differDispatcher, TypeInfoResolver typeInfoResolver) {
        this.differDispatcher = differDispatcher;
        this.typeInfoResolver = typeInfoResolver;
        this.isReturnableResolver = differDispatcher.getIsReturnableResolver();
    }

    @Override
    public boolean accept(Class<?> type) {
        return !type.isPrimitive() && !type.isArray();
    }

    @Override
    public DiffNode compare(DiffNode parentNode, final Instances instances) {
        DiffNode beanNode = new DiffNode(instances.getSourceAccess(), instances.getType());
        if (instances.isNull() || instances.isSame()) {
            beanNode.setState(DiffNode.State.UNCHANGED);
        } else if (instances.hasBeenAdded()) {
            beanNode.setState(DiffNode.State.ADDED);
        } else if (instances.hasBeenRemoved()) {
            beanNode.setState(DiffNode.State.REMOVED);
        } else {
            compareWithNested(beanNode, instances);
        }
        return beanNode;
    }

    private void compareWithNested(final DiffNode parentNode, final Instances instances) {
        TypeInfo typeInfo = typeInfoResolver.typeInfoForNode(parentNode);
        parentNode.setTypeInfo(typeInfo);
        for (PropertyAccess propertyAccess : typeInfo.accesses()) {
            DiffNode propertyNode = differDispatcher.dispatch(parentNode, instances, propertyAccess);
            if (isReturnableResolver.isReturnable(propertyNode))
                parentNode.addChildren(propertyNode);
        }
    }
}
