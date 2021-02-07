package net.masaki_blog.atcoder.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class DataMaker {

    private static final String TEST_DIR = "src/main/java/net/masaki_blog/atcoder/test";

    private static final String FILE_NAME = "tmp.txt";

    public static void main(String... args) {
        try {
            System.out.println("=== START ===");
            new DataMaker().execute();
            System.out.println("=== END   ===");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execute() throws IOException {
        Path path = Paths.get(".", TEST_DIR, FILE_NAME);
        byte[] bytes = this.data().toString().getBytes();
        Files.write(path, bytes);
    }

    private Object data() {
        int n = 2 * (int) Math.pow(10, 5);
        int min = 1;
        int max = (int) Math.pow(10, 6);

        return new Random(System.currentTimeMillis())
                .ints(n, min, max + 1)
                .boxed()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}