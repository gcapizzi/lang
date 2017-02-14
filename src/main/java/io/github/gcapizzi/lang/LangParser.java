package io.github.gcapizzi.lang;

import io.github.gcapizzi.lang.ast.*;
import io.github.gcapizzi.lang.model.MethodCallsNode;
import org.javafp.data.IList;
import org.javafp.parsecj.Parser;
import org.javafp.parsecj.State;

import static org.javafp.data.IList.toList;
import static org.javafp.parsecj.Combinators.*;
import static org.javafp.parsecj.Text.*;

class LangParser {
    private Parser<Character, Node> rootParser;

    private LangParser(Parser<Character, Node> rootParser) {
        this.rootParser = rootParser;
    }

    public static LangParser build() {
        final Parser<Character, Character> dot = chr('.');
        final Parser<Character, Character> openParen = chr('(');
        final Parser<Character, Character> closedParen = chr(')');
        final Parser<Character, Character> quote = chr('"');
        final Parser<Character, Character> comma = chr(',');

        final Parser.Ref<Character, Node> expression = Parser.ref();

        final Parser<Character, Node> string = between(quote, quote, regex("[^\"]*")).bind(s -> retn(new StringLiteralNode(s)));
        final Parser<Character, Node> integer = intr.bind(i -> retn(new IntegerLiteralNode(i)));
        final Parser<Character, String> identifier = regex("[a-zA-Z_][a-zA-Z0-9_]+");
        final Parser<Character, Node> variable = identifier.bind(i -> retn(new VariableNode(i)));
        final Parser<Character, Node> atom = integer.or(string).or(variable);

        final Parser<Character, IList<Node>> argsList = sepBy(expression, comma).bind(args -> retn(args));
        final Parser<Character, IList<Node>> args = between(openParen, closedParen, argsList);
        final Parser<Character, MethodCall> methodCall = dot.then(
                identifier.bind(methodName ->
                        args.bind(arguments ->
                                retn(new MethodCall(methodName, toList(arguments))))));

        expression.set(atom.bind(target ->
                many(methodCall).bind(methodCalls ->
                        retn(new MethodCallsNode(target, toList(methodCalls))))));

        final Parser<Character, Node> root = sepBy(expression, chr('\n')).bind(statements -> retn(new ProgramNode(statements)));

        return new LangParser(root);
    }

    Node parse(String source) throws Exception {
        return rootParser.parse(State.of(source)).getResult();
    }
}
