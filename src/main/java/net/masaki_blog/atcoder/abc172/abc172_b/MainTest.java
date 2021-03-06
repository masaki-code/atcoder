package net.masaki_blog.atcoder.abc172.abc172_b;

import java.util.List;

import org.junit.jupiter.params.provider.Arguments;

import net.masaki_blog.atcoder.test.MainTestBaseV2;

class MainTest extends MainTestBaseV2 {

    private static final String TEST_DATA_FILE = "test_data.txt";

    static List<Arguments> dataProvider() {
        return new MainTest().getDataProvider(TEST_DATA_FILE);

    }

    @Override
    protected void executeMain() throws Exception {
        Main.main("");
    }
}