package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.model.StringLangObject;

import java.util.Map;

public class StringLiteralNode implements Node {
    private String value;

    public StringLiteralNode(String value) {
        this.value = value;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        return new StringLangObject(value);
    }
}
