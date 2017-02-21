package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

public interface Node {
    <T> T evaluate(LangVisitor<T> visitor);
}
