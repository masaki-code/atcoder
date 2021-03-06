package net.masaki_blog.atcoder.abc171.abc171_e;

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
        int n = input.n;
        int[] an = input.an;

        int xor = 0;
        for (int a : an) {
            xor = xor ^ a;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            sb.append(xor ^ an[i]);
            i++;
            if (i == n) {
                break;
            }
            sb.append(" ");

        }
        pw.println(sb);
        pw.flush();
    }

    private static class Input {

        final int n;
        final int[] an;

        Input(BufferedReader br) throws IOException {
            this.n = readInt(br);
            an = new int[n];
            for (int i = 0; i < n; i++) {
                an[i] = readInt(br);
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