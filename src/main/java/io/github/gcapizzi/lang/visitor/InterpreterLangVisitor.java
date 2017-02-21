package io.github.gcapizzi.lang.visitor;

import io.github.gcapizzi.lang.ast.*;
import io.github.gcapizzi.lang.model.IntegerLangObject;
import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.model.StringLangObject;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class InterpreterLangVisitor implements LangVisitor<LangObject> {
    private Map<String, LangObject> context;

    public InterpreterLangVisitor(Map<String, LangObject> context) {
        this.context = context;
    }

    @Override
    public IntegerLangObject visit(IntegerLiteralNode integerLiteralNode) {
        return new IntegerLangObject(integerLiteralNode.getValue());
    }

    @Override
    public StringLangObject visit(StringLiteralNode stringLiteralNode) {
        return new StringLangObject(stringLiteralNode.getValue());
    }

    @Override
    public LangObject visit(VariableNode variableNode) {
        return context.get(variableNode.getName());
    }

    @Override
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

    @Override
    public LangObject visit(ProgramNode programNode) {
        programNode.getStatements().forEach(s -> s.evaluate(this));
        return null;
    }
}
