package io.github.gcapizzi.lang.model;

import java.util.List;

public class IntegerLangObject implements LangObject {
    private Integer value;

    public IntegerLangObject(Integer value) {
        this.value = value;
    }

    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        if (methodName.equals("plus")) {
            IntegerLangObject arg = (IntegerLangObject) args.get(0);
            return new IntegerLangObject(value + arg.value);
        }
        return null;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
