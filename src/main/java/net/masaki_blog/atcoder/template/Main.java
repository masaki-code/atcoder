package net.masaki_blog.atcoder.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SuppressWarnings("unused")
public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute();
    }

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);
    }

    void execute() throws Exception {
        Input input = new Input(br);
        input.readXXXX();
        input.readYYYY();
        pw.flush();
    }

    private static class Input {

        final int n, q;
        final BufferedReader br;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.n = readInt(br);
            this.q = readInt(br);
        }

        void readXXXX() throws IOException {
            for (int i = 0; i < n; i++) {
                int a = readInt(br);
                int b = readInt(br);
            }
        }

        void readYYYY() throws IOException {
            for (int i = 0; i < q; i++) {
                int a = readInt(br);
                int b = readInt(br);
            }
        }

        int readInt(BufferedReader br) throws IOException {
            int a = 0;
            while (true) {
                int read = br.read();
                if (48 <= read && read <= 57) {
                    a = a * 10 + read - 48;
                } else {
                    return a;
                }
            }
        }
    }

}