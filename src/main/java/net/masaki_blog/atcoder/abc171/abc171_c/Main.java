package net.masaki_blog.atcoder.abc171.abc171_c;

import java.io.BufferedReader;
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

    static final int INF = 27;
    static final int LEN = 12;

    int[] chars() {
        int[] chars = new int[LEN];
        Arrays.fill(chars, INF);
        return chars;
    }

    void execute() throws Exception {

        long i = Long.parseLong(br.readLine());
        int[] chars = chars();
        for (int j = 0; j < LEN; j++) {
            long q = i / 26;
            long r = i % 26;
            chars[j] = (int) r;
            if (q == 0) {
                break;
            }
            i = q;
        }

        for (int j = 0; j < LEN - 1; j++) {
            if (chars[j] == INF) {
                break;
            }
            if (chars[j] == 0 && chars[j + 1] == INF) {
                break;
            }
            if (chars[j] <= 0) {
                chars[j] += 26;
                chars[j + 1] -= 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < LEN; j++) {
            int c = chars[j];
            if (c == INF || c == 0) {
                break;
            }
            sb.insert(0, alphabet(c));
        }
        pw.println(sb);
        pw.flush();
    }

    char alphabet(int val) {
        return (char) ('a' + val - 1);
    }
}
