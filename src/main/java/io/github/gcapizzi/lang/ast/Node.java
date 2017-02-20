package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.LangVisitor;
import io.github.gcapizzi.lang.model.LangObject;

public interface Node {
    LangObject evaluate(LangVisitor visitor);
}
