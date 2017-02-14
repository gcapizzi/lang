package io.github.gcapizzi.lang.model;

import java.util.List;

public class IoLangObject implements LangObject {
    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        System.out.println(args.get(0));
        return null;
    }
}
