package io.github.gcapizzi.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EndToEndTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void itExecutesAMethodCall() throws Exception {
        String source = "IO.println(\"Hello, world!\")";
        LangInterpreter interpreter = new LangInterpreter(new LangParser());
        interpreter.run(source);

        assertThat(outputStream.toString(), is("Hello, world!\n"));
    }
}
