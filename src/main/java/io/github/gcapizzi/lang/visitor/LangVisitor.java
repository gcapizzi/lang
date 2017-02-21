package io.github.gcapizzi.lang.visitor;

import io.github.gcapizzi.lang.ast.*;

public interface LangVisitor {
    void visit(IntegerLiteralNode integerLiteralNode);

    void visit(StringLiteralNode stringLiteralNode);

    void visit(VariableNode variableNode);

    void visit(MethodCallsNode methodCallsNode);

    void visit(ProgramNode programNode);
}
