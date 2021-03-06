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

abstract public class MainTestBaseV2 {

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

    protected List<Arguments> getDataProvider(String testDataFileName) {
        try {
            String testDataFile = this.getClass().getResource(testDataFileName).getPath();
            List<String> readAllData = Files.readAllLines(Paths.get(testDataFile));
            List<List<String>> testData = lines2data(readAllData);

            if (testData.size() % 2 != 0) {
                throw new RuntimeException("testData.size() % 2 != 0");
            }

            int len = testData.size() / 2;
            List<Arguments> pattern = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                List<String> input = testData.get(2 * i);
                List<String> output = testData.get(2 * i + 1);
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