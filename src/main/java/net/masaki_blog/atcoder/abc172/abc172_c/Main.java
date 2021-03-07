package net.masaki_blog.atcoder.abc172.abc172_c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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
        Counts counts = new Counts(input.an, input.bm, input.K);
        pw.println(counts.max());
        return this;
    }

    private static class Counts {
        final int[] counts;

        int max() {
            int max = 0;
            for (int count : counts) {
                max = max > count ? max : count;
            }
            return max;
        }

        Counts(An an, An bm, int k) {
            int[] counts = new int[an.sum.length];
            for (int i = 0; i < an.sum.length; i++) {
                int count = 0;
                for (int j = bm.sum.length - 1; j >= 0; j--) {
                    if (an.sum[i] + bm.sum[j] <= k) {
                        count = i + j;
                        break;
                    }
                }
                counts[i] = count;
            }
            this.counts = counts;
        }
    }

    private static class An {

        final long[] sum;

        An(int n, int k, Input input) throws IOException {
            long[] sum = new long[n + 1]; // sum[0] = 0
            int size = 0;
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + input.readInt();
                if (sum[i] <= k) {
                    size = i;
                }
            }
            if (size != n) {
                sum = Arrays.copyOfRange(sum, 0, size + 1);
            }
            this.sum = sum;
        }
    }

    private static class Input {

        final BufferedReader br;

        final int N, M, K;

        final An an, bm;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.N = readInt();
            this.M = readInt();
            this.K = readInt();
            this.an = new An(N, K, this);
            this.bm = new An(M, K, this);
        }

        int readInt() throws IOException {
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