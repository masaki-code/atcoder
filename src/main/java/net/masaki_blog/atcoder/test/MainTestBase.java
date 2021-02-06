package net.masaki_blog.atcoder.test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MainTestBase {

    private static final InputStream SYSTEM_IN = System.in;
    private static final PrintStream SYSTEM_OUT = System.out;

    protected StandardPrintStream out = StandardPrintStream.has(SYSTEM_OUT);
    protected StandardInputStream in = StandardInputStream.get();

    @BeforeEach
    public void before() {
        System.setIn(in);
        System.setOut(out);
    }

    @AfterEach
    public void after() {
        System.setIn(SYSTEM_IN);
        System.setOut(SYSTEM_OUT);
    }

    protected static List<String> input(Object... obj) {
        return Stream.of(obj).map(Object::toString).collect(Collectors.toList());
    }

    protected static String output(Object obj) {
        return obj.toString();
    }

}