package net.masaki_blog.atcoder.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Objects;

/**
 * テスト用の標準出力クラス
 */
public class StandardPrintStream extends PrintStream {

    private final PrintStream other;

    private BufferedReader stream = newBufferedReader("");

    public static StandardPrintStream get() {
        return new StandardPrintStream(null);
    }

    public static StandardPrintStream has(PrintStream other) {
        return new StandardPrintStream(other);
    }

    private StandardPrintStream(PrintStream other) {
        super(new ByteArrayOutputStream());
        this.other = other;
    }

    public String readLine() {
        String line = readLineInternal();

        if (other != null) {
            other.println(line);
        }

        return line;
    }

    private String readLineInternal() {
        try {
            String line = stream.readLine();
            if (line != null) {
                return line;
            } else {
                stream = newBufferedReader(super.out);
                ((ByteArrayOutputStream) super.out).reset();
                return stream.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private BufferedReader newBufferedReader(Object str) {
        return new BufferedReader(new StringReader(Objects.toString(str)));
    }

}