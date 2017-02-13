package io.github.gcapizzi.lang;

import io.github.gcapizzi.lang.ast.*;
import org.javafp.data.IList;
import org.javafp.parsecj.Parser;
import org.javafp.parsecj.State;

import static org.javafp.data.IList.toList;
import static org.javafp.parsecj.Combinators.*;
import static org.javafp.parsecj.Text.chr;
import static org.javafp.parsecj.Text.regex;

class LangParser {
    private final Parser<Character, Character> dot = chr('.');
    private final Parser<Character, Character> openParen = chr('(');
    private final Parser<Character, Character> closedParen = chr(')');
    private final Parser<Character, Character> quote = chr('"');
    private final Parser<Character, Character> comma = chr(',');
    private final Parser<Character, String> identifier = regex("[a-zA-Z_][a-zA-Z0-9_]+");
    private final Parser<Character, Node> string = between(quote, quote, regex("[^\"]*")).bind(s -> retn(new StringLiteralNode(s)));
    private final Parser<Character, Node> variable = identifier.bind(i -> retn(new VariableNode(i)));
    private final Parser<Character, Node> expression = variable.or(string);
    private final Parser<Character, IList<Node>> argsList = sepBy(expression, comma).bind(args -> retn(args));
    private final Parser<Character, IList<Node>> args = between(openParen, closedParen, argsList);
    private final Parser<Character, Node> methodCall =
            expression.bind(target ->
                    dot.then(
                            identifier.bind(methodName ->
                                    args.bind(arguments ->
                                            retn(new MethodCallNode(target, methodName, toList(arguments)))))));
    private final Parser<Character, Node> program = sepBy(methodCall, chr('\n')).bind(statements -> retn(new ProgramNode(statements)));

    Node parse(String source) throws Exception {
        return program.parse(State.of(source)).getResult();
    }
}
