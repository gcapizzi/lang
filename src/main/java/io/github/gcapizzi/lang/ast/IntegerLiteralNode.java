package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.LangVisitor;
import io.github.gcapizzi.lang.model.LangObject;

public class IntegerLiteralNode implements Node {
    private Integer value;

    public IntegerLiteralNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public LangObject evaluate(LangVisitor visitor) {
        return visitor.visit(this);
    }
}
