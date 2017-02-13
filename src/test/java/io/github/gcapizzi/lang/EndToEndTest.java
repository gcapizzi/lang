package io.github.gcapizzi.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EndToEndTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final LangInterpreter interpreter = new LangInterpreter(new LangParser());

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void itExecutesAMethodCall() throws Exception {
        String source = "IO.println(\"Hello, world!\")";
        interpreter.run(source);
        assertThat(outputStream.toString(), is("Hello, world!\n"));
    }

    @Test
    public void itExecutesMultipleStatements() throws Exception {
        String source = "IO.println(\"Foo\")\nIO.println(\"Bar\")";
        interpreter.run(source);
        assertThat(outputStream.toString(), is("Foo\nBar\n"));
    }
}
