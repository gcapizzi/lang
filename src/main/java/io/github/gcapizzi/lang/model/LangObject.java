package io.github.gcapizzi.lang.model;

import java.util.List;

public interface LangObject {
    LangObject invokeMethod(String methodName, List<LangObject> args);
}
