package io.github.gcapizzi.lang;

import io.github.gcapizzi.lang.ast.Node;
import io.github.gcapizzi.lang.visitor.CompilerLangVisitor;
import io.github.gcapizzi.lang.visitor.LangVisitor;

class LangCompiler {
    private LangParser parser;

    LangCompiler(LangParser parser) {
        this.parser = parser;
    }

    void run(String source) throws Exception {
        Node node = parser.parse(source);

        LangVisitor langVisitor = new CompilerLangVisitor();

        node.evaluate(langVisitor);
    }
}
