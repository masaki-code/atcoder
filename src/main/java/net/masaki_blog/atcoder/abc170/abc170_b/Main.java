package net.masaki_blog.atcoder.abc170.abc170_b;

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

    final int x, y;

    Main(BufferedReader br) throws Exception {
        String[] xy = br.readLine().split(" ");
        this.x = Integer.parseInt(xy[0]);
        this.y = Integer.parseInt(xy[1]);
    }

    String execute() {
        for (int i = 0; i <= x; i++) {
            int j = x - i;
            if (i * 2 + j * 4 == y) {
                return "Yes";
            }
        }
        return "No";
    }
}