package io.github.gcapizzi.lang;

import com.google.common.collect.ImmutableMap;
import io.github.gcapizzi.lang.ast.Node;
import io.github.gcapizzi.lang.model.LangObject;
import io.github.gcapizzi.lang.model.SingletonLangObject;

import java.util.Map;

class LangInterpreter {
    private LangParser parser;

    LangInterpreter(LangParser parser) {
        this.parser = parser;
    }

    void run(String source) throws Exception {
        Node node = parser.parse(source);

        SingletonLangObject ioObject = new SingletonLangObject(ImmutableMap.of("println", (self, args) -> {
            System.out.println(args.get(0).toString());
            return null;
        }));
        Map<String, LangObject> context = ImmutableMap.of("IO", ioObject);

        node.evaluate(context);
    }
}
