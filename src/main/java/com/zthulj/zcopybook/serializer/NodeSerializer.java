package com.zthulj.zcopybook.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zthulj.zcopybook.model.Node;
import com.zthulj.zcopybook.model.ParentNode;
import com.zthulj.zcopybook.model.ValueNode;

import java.io.IOException;

public class NodeSerializer<T> extends JsonSerializer<Node<T>> {

    @Override
    public void serialize(Node<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value.isParent())
            serializeParent(value, gen, serializers);
        else
            serializeChild(value, gen, serializers);
    }

    public void serializeParent(Node<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeObject(((ParentNode)value).getChilds());
    }

    public void serializeChild(Node<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(((ValueNode)value).getValue().toString());
    }
}
