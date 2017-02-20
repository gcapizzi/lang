package io.github.gcapizzi.lang.ast;

import io.github.gcapizzi.lang.LangVisitor;
import io.github.gcapizzi.lang.model.LangObject;
import org.javafp.data.IList;

public class ProgramNode implements Node {
    private IList<Node> statements;

    public ProgramNode(IList<Node> statements) {
        this.statements = statements;
    }

    public IList<Node> getStatements() {
        return statements;
    }

    @Override
    public LangObject evaluate(LangVisitor visitor) {
        return visitor.visit(this);
    }
}
