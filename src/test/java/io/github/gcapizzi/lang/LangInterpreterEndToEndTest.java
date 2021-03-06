package io.github.gcapizzi.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LangInterpreterEndToEndTest {
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
        assertOutput("IO.println(1.plus(2).times(2).minus(2).divided_by(4))", "1\n");
    }

    @Test
    public void itSupportsMultipleArguments() throws Exception {
        assertOutput("IO.println(\"Hello,\", \"world!\")", "Hello, world!\n");
    }

    @Test
    public void itSupportsNestedMethodCalls() throws Exception {
        assertOutput("IO.println(\"foo\", \"ba\".concat(\"r\"))", "foo bar\n");
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
