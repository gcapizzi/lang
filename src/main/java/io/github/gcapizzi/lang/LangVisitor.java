package io.github.gcapizzi.lang;

import io.github.gcapizzi.lang.ast.*;
import io.github.gcapizzi.lang.model.IntegerLangObject;
import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.model.StringLangObject;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LangVisitor {
    private Map<String, LangObject> context;

    public LangVisitor(Map<String, LangObject> context) {
        this.context = context;
    }

    public IntegerLangObject visit(IntegerLiteralNode integerLiteralNode) {
        return new IntegerLangObject(integerLiteralNode.getValue());
    }

    public StringLangObject visit(StringLiteralNode stringLiteralNode) {
        return new StringLangObject(stringLiteralNode.getValue());
    }

    public LangObject visit(VariableNode variableNode) {
        return context.get(variableNode.getName());
    }

    public LangObject visit(MethodCallsNode methodCallsNode) {
        LangObject result = methodCallsNode.getTarget().evaluate(this);

        for (MethodCall methodCall : methodCallsNode.getMethodCalls()) {
            List<LangObject> evaluatedArgs = methodCall.getArguments().stream()
                    .map(arg -> arg.evaluate(this))
                    .collect(toList());

            result = result.invokeMethod(methodCall.getMethodName(), evaluatedArgs);
        }

        return result;
    }

    public LangObject visit(ProgramNode programNode) {
        programNode.getStatements().forEach(s -> s.evaluate(this));
        return null;
    }
}
