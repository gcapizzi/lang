package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

public interface Node {
    void evaluate(LangVisitor visitor);
}
