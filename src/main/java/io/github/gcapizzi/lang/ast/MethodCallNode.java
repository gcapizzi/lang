package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.LangObject;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MethodCallNode implements Node {
    private final Node target;
    private final String methodName;
    private final List<Node> arguments;

    public MethodCallNode(Node target, String methodName, List<Node> arguments) {
        this.target = target;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        LangObject evaluatedTarget = target.evaluate(context);
        List<LangObject> evaluatedArgs = arguments.stream()
                .map(arg -> arg.evaluate(context))
                .collect(toList());

        return evaluatedTarget.invokeMethod(methodName, evaluatedArgs);
    }
}
