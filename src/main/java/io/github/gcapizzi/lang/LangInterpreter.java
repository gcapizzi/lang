package io.github.gcapizzi.lang;

import com.google.common.collect.ImmutableMap;
import io.github.gcapizzi.lang.ast.Node;
import io.github.gcapizzi.lang.model.IoLangObject;
import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.visitor.InterpreterLangVisitor;
import io.github.gcapizzi.lang.visitor.LangVisitor;

import java.util.Map;

class LangInterpreter {
    private LangParser parser;

    LangInterpreter(LangParser parser) {
        this.parser = parser;
    }

    void run(String source) throws Exception {
        Node node = parser.parse(source);

        Map<String, LangObject> context = ImmutableMap.of("IO", new IoLangObject());
        LangVisitor<LangObject> langVisitor = new InterpreterLangVisitor(context);

        node.evaluate(langVisitor);
    }
}
