package net.masaki_blog.atcoder.abc170.abc170_c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(new Main(br).execute());
        pw.flush();

    }

    final int x, n;
    final int[] pn;

    Main(BufferedReader br) throws Exception {
        this.x = readInt(br);
        this.n = readInt(br);

        pn = new int[n];

        for (int i = 0; i < n; i++) {
            pn[i] = readInt(br);
        }
    }

    private int readInt(BufferedReader br) throws IOException {
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

    int execute() {
        int[] bucket = new int[101];
        for (int p : pn) {
            bucket[p] = 1;
        }

        if (bucket[x] == 0) {
            return x;

        }

        int dis = 1;
        while (true) {
            if (x - dis == -1) {
                return -1;
            }

            if (bucket[x - dis] == 0) {
                return x - dis;
            }

            if (100 < x + dis) {
                return x + dis;
            }

            if (bucket[x + dis] == 0) {
                return x + dis;
            }
            dis++;
        }
    }
}