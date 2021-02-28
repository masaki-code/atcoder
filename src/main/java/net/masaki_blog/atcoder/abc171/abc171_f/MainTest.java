package net.masaki_blog.atcoder.abc171.abc171_f;

import java.util.List;

import org.junit.jupiter.params.provider.Arguments;

import net.masaki_blog.atcoder.test.MainTestBase;

class MainTest extends MainTestBase {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    static List<Arguments> dataProvider() {
        return new MainTest().getDataProvider(INPUT_FILE, OUTPUT_FILE);

    }

    @Override
    protected void executeMain() throws Exception {
        Main.main("");
    }
}