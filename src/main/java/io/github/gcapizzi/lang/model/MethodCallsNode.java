package io.github.gcapizzi.lang.model;

import io.github.gcapizzi.lang.ast.MethodCall;
import io.github.gcapizzi.lang.ast.Node;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MethodCallsNode implements Node {
    private final Node target;
    private final List<MethodCall> methodCalls;

    public MethodCallsNode(Node target, List<MethodCall> methodCalls) {
        this.target = target;
        this.methodCalls = methodCalls;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        LangObject result = target.evaluate(context);

        for (MethodCall methodCall : methodCalls) {
            List<LangObject> evaluatedArgs = methodCall.getArguments().stream()
                    .map(arg -> arg.evaluate(context))
                    .collect(toList());

            result = result.invokeMethod(methodCall.getMethodName(), evaluatedArgs);
        }

        return result;
    }
}
