package io.github.gcapizzi.lang.visitor;

import com.google.common.collect.Lists;
import io.github.gcapizzi.lang.ast.*;
import io.github.gcapizzi.lang.model.IntegerLangObject;
import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.model.StringLangObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterpreterLangVisitor implements LangVisitor {
    private Map<String, LangObject> context;
    private List<LangObject> stack = new ArrayList<>();

    public InterpreterLangVisitor(Map<String, LangObject> context) {
        this.context = context;
    }

    @Override
    public void visit(IntegerLiteralNode integerLiteralNode) {
        push(new IntegerLangObject(integerLiteralNode.getValue()));
    }

    @Override
    public void visit(StringLiteralNode stringLiteralNode) {
        push(new StringLangObject(stringLiteralNode.getValue()));
    }

    @Override
    public void visit(VariableNode variableNode) {
        push(context.get(variableNode.getName()));
    }

    @Override
    public void visit(MethodCallsNode methodCallsNode) {
        methodCallsNode.getTarget().evaluate(this);

        for (MethodCall methodCall : methodCallsNode.getMethodCalls()) {
            List<Node> arguments = methodCall.getArguments();
            arguments.forEach(arg -> arg.evaluate(this));
            List<LangObject> evaluatedArgs = popManyInOrder(arguments.size());
            push(pop().invokeMethod(methodCall.getMethodName(), evaluatedArgs));
        }
    }

    private List<LangObject> popManyInOrder(int size) {
        List<LangObject> poppedObjects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            poppedObjects.add(pop());
        }
        return Lists.reverse(poppedObjects);
    }

    @Override
    public void visit(ProgramNode programNode) {
        programNode.getStatements().forEach(s -> s.evaluate(this));
    }

    private void push(LangObject object) {
        stack.add(object);
    }

    private LangObject pop() {
        return stack.remove(stack.size() - 1);
    }
}
