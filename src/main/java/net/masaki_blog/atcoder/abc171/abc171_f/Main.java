package net.masaki_blog.atcoder.abc171.abc171_f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SuppressWarnings("unused")
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

    private static class An {
        final long s;
        final long k;

        long i;
        long ai;

        /**
         *  (s-1)+i,C,(s-1) * 25^i * 26^k-i
         */
        An(long s, long k) {
            this.s = s;
            this.k = k;
            this.i = 0;
            this.ai = initAi();
        }

        long initAi() {
            long ai = 1;
            for (int i = 0; i < k; i++) {
                ai = ai * (long) 26;
                if (ai >= LIMIT) {
                    ai = ai % LIMIT;
                }
            }
            return ai;
        }

        long get() {
            return this.ai;
        }

        /**
         *  (i+s-1)/i * 25/26 * ai-1
         */
        An next() {
            i++;
            long ai = this.ai;
            ai = ai * (long) (i + s - 1);
            ai = ai * (long) 25;
            ai = ai / (long) i;
            ai = ai / (long) 26;
            if (ai >= LIMIT) {
                ai = ai % LIMIT;
            }
            this.ai = ai;
            return this;
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
