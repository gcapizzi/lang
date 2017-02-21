package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

public class IntegerLiteralNode implements Node {
    private Integer value;

    public IntegerLiteralNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void evaluate(LangVisitor visitor) {
        visitor.visit(this);
    }
}
