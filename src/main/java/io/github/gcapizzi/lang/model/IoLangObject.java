package io.github.gcapizzi.lang.model;

import java.util.List;
import java.util.stream.Collectors;

public class IoLangObject implements LangObject {
    @Override
    public LangObject invokeMethod(String methodName, List<LangObject> args) {
        List<String> strings = args.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        System.out.println(String.join(" ", strings));
        return null;
    }
}
