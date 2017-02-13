package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.LangObject;

import java.util.Map;

public interface Node {
    LangObject evaluate(Map<String, LangObject> context);
}
