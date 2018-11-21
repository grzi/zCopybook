package com.zthulj.zcopybook.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ZCopyBook<T> {

    private final RootNode<T> rootNode;
    private List<ValueNode<T>> valueNodes;
    private int waitedLength;

    public static <T> ZCopyBook<T> from(RootNode<T> rootNodeCopybook){
        List<ValueNode<T>> allValueNodes = new ArrayList<>();
        rootNodeCopybook.getChilds().forEach(
                (k,v) -> allValueNodes.addAll(v.getAllValueNodes())
        );
        final int waited = allValueNodes.stream().mapToInt(e->e.getCoordinates().getSize()).sum();
        return new ZCopyBook<>(rootNodeCopybook,allValueNodes, waited);
    }
}
