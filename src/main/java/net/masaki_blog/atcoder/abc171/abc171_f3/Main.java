package net.masaki_blog.atcoder.abc171.abc171_f3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute();
    }

    static final long LIMIT = 1_000_000_000 + 7;

    static final Mod m = new Mod(LIMIT);

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);
    }

    void execute() throws Exception {

        Input input = new Input(br);

        int k = input.K;
        int s = input.S.length();

        An an = new An(s, k);

        long sum = 0;
        for (int i = 0; i <= k; i++) {
            sum = m.plus(sum, an.get(i));
        }

        pw.println(sum);
        pw.flush();
    }

    private static class An {

        private final int s;
        private final int k;

        private final Comb comb;
        private final Exp exp25;
        private final Exp exp26;

        /**
         *  (s-1)+i,C,(s-1) * 25^i * 26^k-i
         *
         *  i in [0 ... k]
         */
        An(int s, int k) {
            this.s = s;
            this.k = k;
            this.comb = new Comb(s - 1 + k);
            this.exp25 = new Exp(25, k);
            this.exp26 = new Exp(26, k);
        }

        long get(int i) {
            return m.times(c(i), e25(i), e26(i));
        }

        private long c(int i) {
            return comb.get(s - 1 + i, s - 1);
        }

        private long e25(int i) {
            return exp25.get(i);
        }

        private long e26(int i) {
            return exp26.get(k - i);
        }
    }

    private static class Mod {

        private final long law;

        Mod(long law) {
            this.law = law;
        }

        long plus(long a, long b) {
            return mod(a + b);
        }

        long times(long a, long b) {
            return mod(a * b);
        }

        long times(long a, long b, long c) {
            return mod(mod(a * b) * c);
        }

        private long mod(long val) {
            if (val >= law) {
                val = val % law;
            }
            return val;
        }
    }

    private static class Comb {
        private final int max;
        private final long[] kai;
        private final long[] inv;

        Comb(int max) {
            this.max = max;
            this.kai = new long[max + 1];
            this.inv = new long[max + 1];
            init();
        }

        private void init() {
            kai[0] = 1;
            for (int i = 1; i <= max; i++) {
                kai[i] = m.times(kai[i - 1], i);
            }
            inv[0] = 1;
            inv[1] = 1;
            inv[max] = modInverse(kai[max]);
            for (int i = max - 1; i > 1; i--) {
                inv[i] = m.times(inv[i + 1], i + 1);
            }
        }

        private long modInverse(long val) {
            return BigInteger.valueOf(val)
                    .modInverse(BigInteger.valueOf(LIMIT))
                    .longValue();
        }

        long get(int n, int k) {
            return m.times(kai[n], inv[k], inv[n - k]);
        }
    }

    private static class Exp {

        private final int tei;
        private final int max;
        private final long[] val;

        Exp(int tei, int max) {
            this.tei = tei;
            this.max = max;
            this.val = new long[max + 1];
            init();
        }

        private void init() {
            val[0] = 1;
            for (int i = 1; i <= max; i++) {
                val[i] = m.times(val[i - 1], tei);
            }
        }

        long get(int i) {
            return val[i];
        }
    }

    private static class Input {

        final int K;
        final String S;

        Input(BufferedReader br) throws IOException {
            this.K = Integer.parseInt(br.readLine());
            this.S = br.readLine();
        }
    }

}
