package net.masaki_blog.atcoder.abs.arc065_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(new Main(br).execute());
        pw.flush();

    }

    final char[] s;

    Main(BufferedReader br) throws Exception {
        this.s = (br.readLine()).toCharArray();

    }

    String execute() {
        return isFit() ? "YES" : "NO";

    }

    int i = 0;

    boolean isFit() {

        if (i + 5 <= s.length
                && s[i + 0] == 'd'
                && s[i + 1] == 'r'
                && s[i + 2] == 'e'
                && s[i + 3] == 'a'
                && s[i + 4] == 'm') {

            i = i + 5;

            if (i == s.length || isFit()) {
                return true;
            }

            i = i - 5;

        }

        if (i + 7 <= s.length
                && s[i + 0] == 'd'
                && s[i + 1] == 'r'
                && s[i + 2] == 'e'
                && s[i + 3] == 'a'
                && s[i + 4] == 'm'
                && s[i + 5] == 'e'
                && s[i + 6] == 'r') {

            i = i + 7;

            if (i == s.length || isFit()) {
                return true;
            }

            i = i - 7;

        }

        if (i + 5 <= s.length
                && s[i + 0] == 'e'
                && s[i + 1] == 'r'
                && s[i + 2] == 'a'
                && s[i + 3] == 's'
                && s[i + 4] == 'e') {

            i = i + 5;

            if (i == s.length || isFit()) {
                return true;
            }

            i = i - 5;
        }

        if (i + 6 <= s.length
                && s[i + 0] == 'e'
                && s[i + 1] == 'r'
                && s[i + 2] == 'a'
                && s[i + 3] == 's'
                && s[i + 4] == 'e'
                && s[i + 5] == 'r') {

            i = i + 6;

            if (i == s.length || isFit()) {
                return true;
            }

            i = i - 6;

        }

        return false;
    }

}