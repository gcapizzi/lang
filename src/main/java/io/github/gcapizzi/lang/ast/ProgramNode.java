package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.model.LangObject;
import org.javafp.data.IList;

import java.util.Map;

public class ProgramNode implements Node {
    private IList<Node> statements;

    public ProgramNode(IList<Node> statements) {
        this.statements = statements;
    }

    @Override
    public LangObject evaluate(Map<String, LangObject> context) {
        statements.forEach(s -> s.evaluate(context));
        return null;
    }
}
