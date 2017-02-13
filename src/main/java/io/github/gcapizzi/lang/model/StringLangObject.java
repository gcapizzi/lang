package io.github.gcapizzi.lang.model;

import java.util.List;

public class StringLangObject implements LangObject {
    private String value;

    public StringLangObject(String value) {
        this.value = value;
    }

    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
