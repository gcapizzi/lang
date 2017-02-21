package io.github.gcapizzi.lang.visitor;

import io.github.gcapizzi.lang.ast.*;

public interface LangVisitor<T> {
    T visit(IntegerLiteralNode integerLiteralNode);

    T visit(StringLiteralNode stringLiteralNode);

    T visit(VariableNode variableNode);

    T visit(MethodCallsNode methodCallsNode);

    T visit(ProgramNode programNode);
}
