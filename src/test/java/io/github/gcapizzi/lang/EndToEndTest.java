package io.github.gcapizzi.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EndToEndTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final LangInterpreter interpreter = new LangInterpreter(LangParser.build());

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void itExecutesAMethodCall() throws Exception {
        assertOutput("IO.println(\"Hello, world!\")", "Hello, world!\n");
    }

    @Test
    public void itExecutesMultipleStatements() throws Exception {
        assertOutput("IO.println(\"Foo\")\nIO.println(\"Bar\")", "Foo\nBar\n");
    }

    @Test
    public void itHasIntegers() throws Exception {
        assertOutput("IO.println(2.plus(2))", "4\n");
    }

    private void assertOutput(String source, String expectedOutput) throws Exception {
        String output = run(source);
        assertThat(output, is(expectedOutput));
    }

    private String run(String source) throws Exception {
        interpreter.run(source);
        return outputStream.toString();
    }
}
