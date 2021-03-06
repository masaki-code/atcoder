package net.masaki_blog.atcoder.abc171.abc171_b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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

        int pn[] = input.pn;
        Arrays.sort(pn);

        int result = 0;
        for (int i = 0; i < input.k; i++) {
            result += pn[i];
        }

        pw.println(result);
        pw.flush();
    }

    private static class Input {

        final int n, k;
        final BufferedReader br;

        final int[] pn;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.n = readInt(br);
            this.k = readInt(br);
            this.pn = this.readP();
        }

        int[] readP() throws IOException {
            int[] pn = new int[n];
            for (int i = 0; i < n; i++) {
                pn[i] = readInt(br);
            }
            return pn;
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