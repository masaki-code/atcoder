package net.masaki_blog.atcoder.abc171.abc171_f2;

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

        long sum = an.get();
        for (int i = 1; i <= k; i++) {
            sum += an.next().get();
            if (sum >= LIMIT) {
                sum = sum % LIMIT;
            }
        }

        pw.println(sum);
        pw.flush();
    }

    private static class MyInteger {

        BigInteger value;

        MyInteger(BigInteger value) {
            this.value = value;
        }

        MyInteger multiply(long l) {
            return multiply(BigInteger.valueOf(l));
        }

        MyInteger multiply(BigInteger bi) {
            value = value.multiply(bi);
            return this;
        }

        MyInteger divide(long l) {
            return divide(BigInteger.valueOf(l));
        }

        MyInteger divide(BigInteger bi) {
            value = value.divide(bi);
            return this;
        }

        BigInteger remainderValue(BigInteger bi) {
            return value.remainder(bi);
        }
    }

    private static class An {
        final int s;
        final MyInteger ai;

        long i;

        /**
         *  (s-1)+i,C,(s-1) * 25^i * 26^k-i
         */
        An(int s, int k) {
            this.s = s;
            this.i = 0;
            this.ai = new MyInteger(BI_26.pow(k));
        }

        static final BigInteger BI_RE = BigInteger.valueOf(LIMIT);
        static final BigInteger BI_25 = BigInteger.valueOf(25);
        static final BigInteger BI_26 = BigInteger.valueOf(26);

        /**
         *  (i+s-1)/i * 25/26 * ai-1
         */
        An next() {
            i++;
            ai.multiply(i + s - 1).multiply(BI_25).divide(i).divide(BI_26);
            return this;
        }

        long get() {
            return this.ai.remainderValue(BI_RE).longValue();
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
