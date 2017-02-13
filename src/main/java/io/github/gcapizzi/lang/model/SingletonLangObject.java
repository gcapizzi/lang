package io.github.gcapizzi.lang.model;

import java.util.List;
import java.util.Map;

public class SingletonLangObject implements LangObject {
    private final Map<String, LangMethod> methods;

    public SingletonLangObject(Map<String, LangMethod> methods) {
        this.methods = methods;
    }

    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        return methods.get(methodName).invoke(this, args);
    }
}
