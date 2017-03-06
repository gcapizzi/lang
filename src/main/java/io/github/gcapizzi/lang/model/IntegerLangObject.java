package io.github.gcapizzi.lang.model;

import java.util.List;

public class IntegerLangObject implements LangObject {
    private int value;

    public IntegerLangObject(int value) {
        this.value = value;
    }

    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        IntegerLangObject arg = (IntegerLangObject) args.get(0);
        switch (methodName) {
            case "plus":
                return new IntegerLangObject(value + arg.value);
            case "minus":
                return new IntegerLangObject(value - arg.value);
            case "times":
                return new IntegerLangObject(value * arg.value);
            case "divided_by":
                return new IntegerLangObject(value / arg.value);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
