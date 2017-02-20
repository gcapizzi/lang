package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.LangVisitor;
import io.github.gcapizzi.lang.model.LangObject;

public class VariableNode implements Node {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public LangObject evaluate(LangVisitor visitor) {
        return visitor.visit(this);
    }
}
