package io.github.gcapizzi.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LangCompilerEndToEndTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final LangCompiler compiler = new LangCompiler(LangParser.build());

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void itExecutesAMethodCall() throws Exception {
        assertOutput("IO.println(\"Hello, world!\")", "Hello, world!\n");
    }

    private void assertOutput(String source, String expectedOutput) throws Exception {
        String output = run(source);
        assertThat(output, is(expectedOutput));
    }

    private String run(String source) throws Exception {
        compiler.run(source);
        return outputStream.toString();
    }
}
