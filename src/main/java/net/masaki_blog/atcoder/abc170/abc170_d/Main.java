package net.masaki_blog.atcoder.abc170.abc170_d;

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

    final int n;
    final int max;
    final int[] an;

    Main(BufferedReader br) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int max = 0;
        int[] an = new int[n];
        for (int i = 0; i < n; i++) {
            int ai = readInt(br);
            an[i] = ai;
            max = max > ai ? max : ai;
        }
        this.n = n;
        this.an = an;
        this.max = max;
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
        int[] bucket = new int[max + 1];
        for (int i = 0; i < n; i++) {
            int ai = an[i];
            for (int j = 1; j * ai <= max; j++) {
                bucket[j * ai]++;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (bucket[an[i]] == 1) {
                count++;
            }
        }

        return count;
    }
}