package net.masaki_blog.atcoder.abs.abc085_c;

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

    final int n, y;

    Main(BufferedReader br) throws Exception {
        String[] ny = br.readLine().split(" ");
        this.n = Integer.parseInt(ny[0]);
        this.y = Integer.parseInt(ny[1]);

    }

    String execute() {
        for (int ix = 0; ix <= n; ix++) {
            for (int iy = 0; ix + iy <= n; iy++) {
                int iz = n - ix - iy;
                if (10000 * ix + 5000 * iy + 1000 * iz == y) {
                    return ix + " " + iy + " " + iz;
                }

            }
        }
        return "-1 -1 -1";
    }
}