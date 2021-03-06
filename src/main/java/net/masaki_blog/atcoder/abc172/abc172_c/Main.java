package net.masaki_blog.atcoder.abc172.abc172_c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute().flush();
        ;
    }

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);

    }

    void flush() {
        pw.flush();
    }

    Main execute() throws Exception {
        Input input = new Input(br);
        int k = input.K;
        An an = new An(input.AN, k);
        An bm = new An(input.BM, k);
        if (an.get(0) > k && bm.get(0) > k) {
            pw.println(0);
            return this;
        }
        if (an.maxValue() + bm.maxValue() <= k) {
            pw.println(an.max + bm.max);
            return this;
        }
        if (an.max == 0 || bm.max == 0) {
            pw.println(an.max + bm.max);
            return this;
        }

        int max = 0;
        int bStart = 1;
        for (int i = an.max; i >= 0; i--) {
            long ai = an.get(i);
            for (int j = bStart; j <= bm.max; j++) {
                long bj = bm.get(j);
                if (ai + bj > k) {
                    bStart = j - 1;
                    int count = i + j - 1;
                    max = max > count ? max : count;
                    break;
                }
            }
        }

        pw.println(max);
        return this;
    }

    private static class An {
        final long[] sum;
        final int max;

        long get(int i) {
            return sum[i];
        }

        long maxValue() {
            return this.sum[max];
        }

        An(int[] an, int k) {
            int n = an.length;
            long[] sum = new long[n + 1];
            int max = n;
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + an[i - 1];
                if (sum[i] > k) {
                    max = i - 1;
                    break;
                }
            }
            this.sum = sum;
            this.max = max;
        }
    }

    private static class Input {

        final int N, M, K;

        final int[] AN, BM;

        Input(BufferedReader br) throws IOException {
            this.N = readInt(br);
            this.M = readInt(br);
            this.K = readInt(br);
            this.AN = new int[N];
            this.BM = new int[M];
            for (int i = 0; i < N; i++) {
                AN[i] = readInt(br);
            }
            for (int i = 0; i < M; i++) {
                BM[i] = readInt(br);
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