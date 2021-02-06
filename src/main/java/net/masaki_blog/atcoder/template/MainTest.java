package net.masaki_blog.atcoder.template;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import net.masaki_blog.atcoder.test.MainTestBase;

class MainTest extends MainTestBase {

	private static final String FILE_NAME = "pattern.txt";

	@ParameterizedTest
	@MethodSource("dataProvider")
	void testWithDataProvider(List<String> input, String expected) throws Exception {
		executeTest(input, expected);
	}

	static List<Arguments> dataProvider() {
		List<Arguments> pattern = new ArrayList<>();
		pattern.add(arguments(input("1"), output("0")));
		return pattern;
	}

	@ParameterizedTest
	@CsvFileSource(resources = FILE_NAME)
	void testTxt(String csv_p1, String csv_p2) throws Exception {
		List<String> input = Arrays.asList(csv_p1);
		String expected = csv_p2;
		executeTest(input, expected);
	}

	private void executeTest(List<String> input, String expected) throws Exception {
		in.inputLines(input);
		Main.main("");
		assertThat(out.readLine(), is(expected));

	}
}