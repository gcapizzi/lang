package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.LangObject;

import java.util.Map;

public class VariableNode implements Node {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        return context.get(name);
    }
}
