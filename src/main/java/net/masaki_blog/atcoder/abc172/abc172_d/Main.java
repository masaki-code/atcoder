package net.masaki_blog.atcoder.abc172.abc172_d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        Measure measure = new Measure(input.N);

        long result = 0;
        for (int i = 1; i <= input.N; i++) {
            result = result + (long) i * measure.get(i);
        }

        pw.println(result);
        pw.flush();
    }

    private static class Measure {
        final int[] measure;

        Measure(int N) {
            int[] measure = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                int index = i;
                while (index <= N) {
                    measure[index]++;
                    index = index + i;
                }
            }
            this.measure = measure;
        }

        long get(int x) {
            return measure[x];
        }
    }

    private static class Input {

        final BufferedReader br;

        final int N;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.N = readInt();
        }

        int readInt() throws IOException {
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