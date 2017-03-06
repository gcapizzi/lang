package io.github.gcapizzi.lang;

public class LangMain {
    public static void main(String[] args) throws Exception {
        LangParser parser = LangParser.build();
        LangCompiler compiler = new LangCompiler(parser);

        String source = "IO.println(\"Hello, world!\")";

        compiler.run(source);
    }
}
