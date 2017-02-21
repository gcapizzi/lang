package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.visitor.LangVisitor;

import java.util.List;

public class MethodCallsNode implements Node {
    private final Node target;
    private final List<MethodCall> methodCalls;

    public MethodCallsNode(Node target, List<MethodCall> methodCalls) {
        this.target = target;
        this.methodCalls = methodCalls;
    }

    public Node getTarget() {
        return target;
    }

    public List<MethodCall> getMethodCalls() {
        return methodCalls;
    }

    @Override
    public void evaluate(LangVisitor visitor) {
        visitor.visit(this);
    }
}
