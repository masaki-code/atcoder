package net.masaki_blog.atcoder.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import net.masaki_blog.atcoder.template.Main;

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
		Main.main("");
		assertThat(out.readLine(), is(expected));
	}

}