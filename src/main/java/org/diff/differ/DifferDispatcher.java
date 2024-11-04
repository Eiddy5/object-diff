package org.diff.differ;


import org.diff.DiffNode;
import org.diff.Instances;
import org.diff.access.Access;
import org.diff.access.PropertyAccess;
import org.diff.resolver.IsReturnableResolver;


public class DifferDispatcher {
    private final DifferProvider differProvider;
    private final IsReturnableResolver isReturnableResolver;

    public DifferDispatcher(DifferProvider provider, IsReturnableResolver isReturnableResolver) {
        this.differProvider = provider;
        this.isReturnableResolver = isReturnableResolver;
    }


    public DiffNode dispatch(DiffNode parentNode, Instances parentInstances, Access access) {
        DiffNode node = compare(parentNode, parentInstances, access);
        // todo 边界条件
        if (parentNode != null && isReturnableResolver.isReturnable(node)) {
            parentNode.addChildren(node);
        }
        return node;
    }

    private DiffNode compare(DiffNode parentNode, Instances parentInstances, Access access) {
        DiffNode diffNode = new DiffNode(access, null);
        Instances accessedInstances;
        if (access instanceof PropertyAccess) {
            PropertyAccess propertyAccess = (PropertyAccess) access;
            accessedInstances = parentInstances.access(propertyAccess);
        } else {
            accessedInstances = parentInstances.access(access);
        }
        Differ differ = differProvider.retrieveDiffer(accessedInstances.getType());
        DiffNode compared = differ.compare(diffNode, accessedInstances);
        if (parentNode != null){
            parentNode.addChildren(compared);
        }
        return compared;
    }


}
