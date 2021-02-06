package net.masaki_blog.atcoder.test;

import java.io.IOException;
import java.io.InputStream;

/**
 * テスト用の標準入力クラス
 */
public class StandardInputStream extends InputStream {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private StringBuilder stream = new StringBuilder();

    public static StandardInputStream get() {
        return new StandardInputStream();
    }

    private StandardInputStream() {

    };

    public <T> void inputLine(T line) {
        stream.append(line).append(LINE_SEPARATOR);
    }

    public <T> void inputLines(Iterable<T> lines) {
        for (T line : lines) {
            this.inputLine(line);
        }
    }

    public <T> void inputLines(T[] lines) {
        for (T line : lines) {
            this.inputLine(line);
        }
    }

    public void inputLines(int[] lines) {
        for (int line : lines) {
            this.inputLine(line);
        }
    }

    @Override
    public int read() throws IOException {
        if (stream.length() == 0) {
            return -1;
        }
        int result = stream.charAt(0);
        stream.deleteCharAt(0);
        return result;
    }

}