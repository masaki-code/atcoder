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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	/**
	 * dataProviderはサブクラスで実装（static）
	 */
	@ParameterizedTest
	@MethodSource("dataProvider")
	void test(List<String> input, String expected) throws Exception {
		executeTest(input, expected);
	}

	private void executeTest(List<String> input, String expected) throws Exception {
		in.inputLines(input);
		this.executeMain();
		assertThat(out.readLine(), is(expected));
	}

	abstract protected void executeMain() throws Exception;

	protected List<Arguments> getDataProvider(String inputFileName, String outputFIleName) {
		try {
			String inputFile = this.getClass().getResource(inputFileName).getPath();
			String outputFile = this.getClass().getResource(outputFIleName).getPath();
			List<String> inputList = Files.readAllLines(Paths.get(inputFile));
			List<String> outputList = Files.readAllLines(Paths.get(outputFile));

			List<Arguments> pattern = new ArrayList<>();
			for (int i = 0; i < inputList.size(); i++) {
				String input = inputList.get(i);
				Object[] inputArray = input.split(",");
				String output = outputList.get(i);
				pattern.add(arguments(input(inputArray), output(output)));
			}

			return pattern;
		} catch (IOException e) {
			fail(e);
			throw new RuntimeException(e);
		}
	}
}