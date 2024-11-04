package org.diff.resolver;

import org.diff.DiffNode;

import java.util.EnumMap;
import java.util.Map;

public class IsReturnableResolver {

    private static final Map<DiffNode.State, Boolean> stateFilterSettings;

    static {
        stateFilterSettings = new EnumMap<>(DiffNode.State.class);
        stateFilterSettings.put(DiffNode.State.IGNORED, false);
        stateFilterSettings.put(DiffNode.State.ADDED, true);
        stateFilterSettings.put(DiffNode.State.REMOVED, true);
        stateFilterSettings.put(DiffNode.State.CHANGED, true);
        stateFilterSettings.put(DiffNode.State.UNCHANGED, false);
    }

    public boolean isReturnable(DiffNode node) {
        if (node.isRootNode()) return true;
        if (node.isUntouched() && node.hasChildren()) return true;
        return stateFilterSettings.get(node.getState());
    }
}
