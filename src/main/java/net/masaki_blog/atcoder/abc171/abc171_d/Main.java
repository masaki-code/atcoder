package net.masaki_blog.atcoder.abc171.abc171_d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute();
    }

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);
    }

    void execute() throws Exception {
        Global.init();
        Input input = new Input(br);
        input.readA();
        input.readBC(pw);
        pw.flush();
    }

    private static class Global {

        static final int MAX = 1_000_00;

        static Sum sum;
        static Backet backet;

        static void init() {
            sum = new Sum();
            backet = new Backet();
        }
    }

    private static class Sum {
        long sum;

        Sum() {
            sum = 0;
        }

        long get() {
            return sum;
        }

        void plus(long val) {
            sum += val;
        }

        void minus(long val) {
            sum -= val;
        }
    }

    private static class Backet {
        final int[] backet;

        Backet() {
            backet = new int[Global.MAX];
        }

        void increment(int val) {
            backet[val - 1]++;
        }

        void move(int from, int to) {
            backet[to - 1] += backet[from - 1];
            backet[from - 1] = 0;
        }

        long get(int val) {
            return (long) backet[val - 1] * (long) val;
        }
    }

    private static class Input {

        final BufferedReader br;

        Input(BufferedReader br) throws IOException {
            this.br = br;
        }

        void readA() throws IOException {
            int n = readInt(br);
            for (int i = 0; i < n; i++) {
                int a = readInt(br);
                Global.backet.increment(a);
                Global.sum.plus(a);
            }
        }

        void readBC(PrintWriter pw) throws IOException {
            int q = readInt(br);
            for (int i = 0; i < q; i++) {
                int b = readInt(br);
                int c = readInt(br);
                Global.sum.minus(Global.backet.get(b));
                Global.sum.minus(Global.backet.get(c));
                Global.backet.move(b, c);
                Global.sum.plus(Global.backet.get(c));
                pw.println(Global.sum.get());
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