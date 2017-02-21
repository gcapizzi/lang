package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

public class StringLiteralNode implements Node {
    private String value;

    public StringLiteralNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T evaluate(LangVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
