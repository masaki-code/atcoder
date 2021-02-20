package net.masaki_blog.atcoder.abc170.abc170_e6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeMap;

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
        Input input = new Input(br);
        input.readInfant();
        input.readTenen(pw);
        pw.flush();
    }

    private static class YochienRateSegTree extends SegTree {
        private static int MAX_COUNT = (int) 2e5;

        YochienRateSegTree() {
            super(MAX_COUNT);
            for (Yochien yochien : Yochien.yochiens) {
                if (yochien != null) {
                    this.update(yochien.yochienNo, yochien.getRate());
                }
            }
        }

        void update(Yochien yochien) {
            int x = yochien.getRate();
            x = x == 0 ? SegTree.INF : x;
            super.update(yochien.yochienNo, x);
        }

        int getMinRate() {
            return super.getTopValue();
        }
    }

    private static class Infant {
        private int rate;
        private int yochienNo;

        static Infant[] infants;

        Infant(int rate, int yochienNo) {
            this.rate = rate;
            this.yochienNo = yochienNo;
        }

        static Infant get(int infantNo) {
            return infants[infantNo - 1];
        }

    }

    private static class Input {

        final int n, q;
        final BufferedReader br;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.n = readInt(br);
            this.q = readInt(br);
        }

        void readInfant() throws IOException {
            Infant.infants = new Infant[n];
            for (int i = 0; i < n; i++) {
                Infant infant = new Infant(readInt(br), readInt(br));
                Infant.infants[i] = infant;
            }
            Yochien.init();
        }

        void readTenen(PrintWriter pw) throws IOException {
            YochienRateSegTree yochienRates = new YochienRateSegTree();

            for (int i = 0; i < q; i++) {
                Infant infant = Infant.get(readInt(br));
                int beforeYochienNo = infant.yochienNo;
                int afterYochienNo = readInt(br);
                Yochien before = Yochien.get(beforeYochienNo);
                Yochien after = Yochien.get(afterYochienNo);
                before.remove(infant.rate);
                yochienRates.update(before);
                after.add(infant.rate);
                yochienRates.update(after);
                infant.yochienNo = afterYochienNo;
                pw.println(yochienRates.getMinRate());
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

    private static class Yochien {

        private static Yochien[] yochiens;

        static void init() {
            yochiens = new Yochien[(int) 2e5 + 1];
            for (Infant infant : Infant.infants) {
                Yochien.get(infant.yochienNo).add(infant.rate);
            }
        }

        private int yochienNo;
        private TreeMap<Integer, Count> infantRates = new TreeMap<>();

        Integer getRate() {
            if (infantRates == null || infantRates.isEmpty()) {
                return 0;
            }
            return this.infantRates.lastKey();
        }

        void add(Integer infantRate) {
            Count infantCount = infantRates.get(infantRate);
            if (infantCount == null) {
                infantCount = new Count();
                infantRates.put(infantRate, infantCount);
            }
            infantCount.increment();
        }

        void remove(Integer infantRate) {
            Count infantCount = infantRates.get(infantRate);
            if (infantCount == null) {
                return;
            }
            if (infantCount.count == 1) {
                infantRates.remove(infantRate);
            } else {
                infantCount.decrement();
            }
        }

        static Yochien get(int yochienNo) {
            if (yochiens[yochienNo] == null) {
                yochiens[yochienNo] = new Yochien();
                yochiens[yochienNo].yochienNo = yochienNo;
            }
            return yochiens[yochienNo];
        }
    }

    private static class Count {
        int count = 0;

        void increment() {
            count++;
        }

        void decrement() {
            count--;
        }
    }

    private static class SegTree {

        private static final int INF = (int) 1e9 + 1;

        private final int[] node;

        private final int n;

        SegTree(int min) {
            int n = 1;
            while (n < min) {
                n *= 2;
            }
            this.n = n;
            this.node = new int[2 * n + 1];
            Arrays.fill(this.node, INF);
        }

        int getTopValue() {
            return node[0];
        }

        void update(int a, int x) {
            a += n - 1;

            if (node[a] == x) {
                return;
            }

            node[a] = x;
            while (a > 0) {
                a = (a - 1) / 2;
                node[a] = Math.min(node[2 * a + 1], node[2 * a + 2]);
            }
        }
    }
}