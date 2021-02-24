package net.masaki_blog.atcoder.abc170.abc170_a;

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

    final int result;

    Main(BufferedReader br) throws Exception {
        String[] xn = br.readLine().split(" ");

        for (int i = 0; i < 5; i++) {
            if (Integer.parseInt(xn[i]) == 0) {
                result = i + 1;
                return;
            }
        }
        result = 0;
    }

    int execute() {
        return result;
    }

}