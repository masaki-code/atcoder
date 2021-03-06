package net.masaki_blog.atcoder.abc172.abc172_b;

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
        char[] s = input.S.toCharArray();
        char[] t = input.T.toCharArray();

        int count = 0;
        for (int i = 0; i < t.length; i++) {
            if (s[i] != t[i]) {
                count++;
            }
        }
        pw.println(count);
        pw.flush();
    }

    private static class Input {

        final String S, T;

        Input(BufferedReader br) throws IOException {
            this.S = br.readLine();
            this.T = br.readLine();
        }
    }
}