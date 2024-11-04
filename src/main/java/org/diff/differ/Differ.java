package org.diff.differ;


import org.diff.DiffNode;
import org.diff.Instances;

public interface Differ {
    boolean accept(Class<?> type);

    DiffNode compare(DiffNode diffNode, Instances instances);
}
