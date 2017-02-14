package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.IntegerLangObject;
import io.github.gcapizzi.lang.model.LangObject;

import java.util.Map;

public class IntegerLiteralNode implements Node {
    private Integer value;

    public IntegerLiteralNode(Integer value) {
        this.value = value;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        return new IntegerLangObject(value);
    }
}
