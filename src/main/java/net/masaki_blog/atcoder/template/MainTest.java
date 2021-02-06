package net.masaki_blog.atcoder.template;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.provider.Arguments;

import net.masaki_blog.atcoder.test.MainTestBase;

class MainTest extends MainTestBase {

	private static final String INPUT_FILE = "input.txt";
	private static final String OUTPUT_FILE = "output.txt";

	static List<Arguments> dataProvider() {
		try {

			String inputFile = MainTest.class.getResource(INPUT_FILE).getPath();
			String outputFile = MainTest.class.getResource(OUTPUT_FILE).getPath();

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