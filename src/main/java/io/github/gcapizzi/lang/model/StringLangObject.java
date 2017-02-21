package io.github.gcapizzi.lang.model;

import java.util.List;

public class StringLangObject implements LangObject {
    private String value;

    public StringLangObject(String value) {
        this.value = value;
    }

    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        StringLangObject arg = (StringLangObject) args.get(0);
        return new StringLangObject(value + arg.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
