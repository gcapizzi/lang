package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

public class VariableNode implements Node {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void evaluate(LangVisitor visitor) {
        visitor.visit(this);
    }
}
