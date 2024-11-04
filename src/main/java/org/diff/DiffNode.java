package org.diff;


import org.diff.access.Access;
import org.diff.access.RootAccess;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DiffNode {

    public static final DiffNode ROOT = null;
    private Access access;
    private Class<?> valueType;
    private TypeInfo typeInfo;
    private Change change;
    private State state = State.UNCHANGED;
    private final List<DiffNode> children = new LinkedList<>();


    public DiffNode(Access access, Class<?> valueType) {
        this.access = access;
        this.valueType = valueType;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean isUntouched() {
        return state.equals(State.UNCHANGED);
    }


    public State getState() {
        return state;
    }


    public void addChildren(DiffNode node) {
        children.add(node);
        if (node.state == State.UNCHANGED && node.hasChanges()) {
            node.state = State.CHANGED;
        }
    }

    public boolean hasAdded() {
        return State.ADDED.equals(state);
    }

    public boolean hasRemoved() {
        return State.REMOVED.equals(state);
    }

    public boolean hasChanges() {
        return hasAdded() || hasRemoved();
    }


    public boolean canReturn() {
        // todo 节点返回条件
        return false;
    }

    public boolean isRootNode() {
        return access instanceof RootAccess;
    }


    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public enum State {
        ADDED,
        CHANGED,
        REMOVED,
        IGNORED,
        UNCHANGED;
    }

}
