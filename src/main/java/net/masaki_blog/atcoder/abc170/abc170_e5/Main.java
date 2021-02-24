package net.masaki_blog.atcoder.abc170.abc170_e5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        new Main(br).execute(pw);
        pw.flush();
    }

    Main(BufferedReader br) throws Exception {
        Input input = new Input(br);
        Infant.init(input);
        Tenen.init(input);
        Yochien.init(Infant.infants);
        Global.init(Yochien.yochiens);
    }

    void execute(PrintWriter pw) {
        for (Tenen tenen : Tenen.tenens) {
            tenen.exec();
            pw.println(Global.getRate());
        }
    }

    private static class Tenen {

        int enjiNo;
        int yoenNo;

        void exec() {
            Infant infant = Infant.get(enjiNo);
            Yochien beforeYochien = Yochien.getYochien(infant.yochienNo);
            Yochien afterYochien = Yochien.getYochien(yoenNo);

            Global.removeYochienRate(beforeYochien.getRate());
            beforeYochien.removeInfantRate(infant.rate);
            if (beforeYochien.isNotEmpty()) {
                Global.addYochienRate(beforeYochien.getRate());
            }

            if (afterYochien.isNotEmpty()) {
                Global.removeYochienRate(afterYochien.getRate());
            }
            afterYochien.addInfantRate(infant.rate);
            Global.addYochienRate(afterYochien.getRate());

            infant.yochienNo = yoenNo;
        }

        static Tenen[] tenens;

        static void init(Input input) {
            tenens = new Tenen[input.q];
            for (int i = 0; i < input.q; i++) {
                Tenen tenen = new Tenen();
                tenen.enjiNo = input.tenenEnjis[i];
                tenen.yoenNo = input.tenenYoens[i];
                tenens[i] = tenen;
            }
        }
    }

    private static class Global {
        private static MySet yochienRates;

        static void init(Yochien[] yochiens) {
            yochienRates = new MySet();

            for (int i = 0; i < yochiens.length; i++) {
                if (yochiens[i] != null) {
                    addYochienRate(yochiens[i].getRate());
                }
            }
        }

        static Integer getRate() {
            return yochienRates.getFirst();
        }

        static void addYochienRate(int rate) {
            yochienRates.increment(rate);
        }

        static void removeYochienRate(int rate) {
            yochienRates.decrement(rate);
        }

    }

    private static class Yochien {
        private MySet infantRates;

        static Yochien[] yochiens;

        static Yochien getYochien(int yochienNo) {
            if (yochiens[yochienNo] == null) {
                yochiens[yochienNo] = new Yochien();
                yochiens[yochienNo].infantRates = new MySet();
            }
            return yochiens[yochienNo];
        }

        boolean isNotEmpty() {
            return !infantRates.isEmpty();
        }

        Integer getRate() {
            return this.infantRates.getEnd();
        }

        static void init(Infant[] infants) {
            yochiens = new Yochien[2_000_001];
            for (int i = 0; i < infants.length; i++) {
                Yochien.getYochien(infants[i].yochienNo).addInfantRate(infants[i].rate);
            }
        }

        void addInfantRate(int rate) {
            infantRates.increment(rate);
        }

        void removeInfantRate(int rate) {
            infantRates.decrement(rate);
        }

    }

    private static class Infant {
        int rate;
        int yochienNo;

        private static Infant[] infants;

        static Infant get(int no) {
            return infants[no - 1];
        }

        static void init(Input input) {
            infants = new Infant[input.n];
            for (int i = 0; i < input.n; i++) {
                Infant infant = new Infant();
                infant.rate = input.infantRates[i];
                infant.yochienNo = input.infantYoens[i];
                infants[i] = infant;
            }
        }
    }

    private static class MyInt {
        int count = 0;

        void increment() {
            count++;
        }

        void decrement() {
            count--;
        }
    }

    private static class MySet {
        private TreeMap<Integer, MyInt> map = new TreeMap<>();

        void increment(Integer key) {
            MyInt val = map.get(key);
            if (val == null) {
                val = new MyInt();
                map.put(key, val);
            }
            val.increment();
        }

        void decrement(Integer key) {
            MyInt val = map.get(key);
            if (val == null) {
                return;
            }

            if (val.count == 1 || val.count == 0) {
                map.remove(key);
                return;
            }

            val.decrement();
        }

        boolean isEmpty() {
            return this.map.isEmpty();
        }

        Integer getFirst() {
            return map.firstEntry().getKey();
        }

        Integer getEnd() {
            return map.lastEntry().getKey();
        }
    }

    private static class Input {

        final int n, q;

        int[] infantRates, infantYoens;
        int[] tenenEnjis, tenenYoens;

        Input(BufferedReader br) throws IOException {
            this.n = readInt(br);
            this.q = readInt(br);

            infantRates = new int[n];
            infantYoens = new int[n];

            for (int i = 0; i < n; i++) {
                infantRates[i] = readInt(br);
                infantYoens[i] = readInt(br);
            }

            tenenEnjis = new int[q];
            tenenYoens = new int[q];
            for (int i = 0; i < q; i++) {
                tenenEnjis[i] = readInt(br);
                tenenYoens[i] = readInt(br);
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