package net.masaki_blog.atcoder.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

abstract public class MainTestBase {

    private static final InputStream SYSTEM_IN = System.in;
    private static final PrintStream SYSTEM_OUT = System.out;

    protected StandardPrintStream out = StandardPrintStream.has(SYSTEM_OUT);
    protected StandardInputStream in = StandardInputStream.get();

    abstract protected void executeMain() throws Exception;

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

    /**
     * dataProviderはサブクラスで実装（static）
     */
    @ParameterizedTest
    @MethodSource("dataProvider")
    void test(List<String> input, List<String> output) throws Exception {
        executeTest(input, output);
    }

    private void executeTest(List<String> input, List<String> output) throws Exception {
        in.inputLines(input);
        this.executeMain();
        for (String expected : output) {
            assertThat(out.readLine(), is(expected));
        }
    }

    protected List<Arguments> getDataProvider(String inputFileName, String outputFIleName) {
        try {
            String inputFile = this.getClass().getResource(inputFileName).getPath();
            String outputFile = this.getClass().getResource(outputFIleName).getPath();
            List<String> readAllInput = Files.readAllLines(Paths.get(inputFile));
            List<String> readAllOutput = Files.readAllLines(Paths.get(outputFile));
            List<List<String>> dataInput = lines2data(readAllInput);
            List<List<String>> dataOutput = lines2data(readAllOutput);

            List<Arguments> pattern = new ArrayList<>();
            for (int i = 0; i < dataInput.size(); i++) {
                List<String> input = dataInput.get(i);
                List<String> output = dataOutput.get(i);
                pattern.add(arguments(input, output));
            }
            return pattern;
        } catch (IOException e) {
            fail(e);
            throw new RuntimeException(e);
        }
    }

    private List<List<String>> lines2data(List<String> readAll) {
        if (readAll.stream().allMatch(s -> !s.startsWith("---"))) {
            return readAll.stream().map(s -> Arrays.asList(s)).collect(Collectors.toList());
        }

        List<List<String>> data = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (String read : readAll) {
            if (read.startsWith("---")) {
                data.add(list);
                list = new ArrayList<>();
            } else {
                list.add(read);
            }
        }
        return data;
    }
}