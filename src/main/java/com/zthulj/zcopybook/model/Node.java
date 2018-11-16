package com.zthulj.zcopybook.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zthulj.zcopybook.serializer.NodeSerializer;

import java.io.Serializable;
import java.util.*;

@JsonSerialize(using = NodeSerializer.class)
public abstract class Node<T> implements Serializable {
    private ParentNode<T> parent;

    protected Node(ParentNode<T> parent) {
        this.parent = parent;
    }

    public static <T> ParentNode<T> createRootNode() {
        return new ParentNode<T>(null, new LinkedHashMap(), 0);
    }

    public static <T> ParentNode<T> createParentNode(ParentNode<T> parent, int lvlNumber) {
        return new ParentNode<T>(parent, new LinkedHashMap<>(), lvlNumber);
    }

    public static <T>  ValueNode<T> createValueNode(ParentNode<T> parent, Coordinates coords) {
        return new ValueNode(parent, coords);
    }

    public static <T> ParentArrayNode<T> createParentNodeArray(ParentNode<T> parent, int lvlNumber, int occursNumber) {
        return new ParentArrayNode(parent, lvlNumber, occursNumber);
    }

    public ParentNode<T> getParent() {
        return parent;
    }

    public abstract boolean isParent();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(isParent(), node.isParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isParent());
    }
}
