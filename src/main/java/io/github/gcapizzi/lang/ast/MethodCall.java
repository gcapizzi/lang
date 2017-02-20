package io.github.gcapizzi.lang.ast;

import java.util.List;

public class MethodCall {
    private final String methodName;
    private final List<Node> arguments;

    public MethodCall(String methodName, List<Node> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Node> getArguments() {
        return arguments;
    }
}
