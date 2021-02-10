package net.masaki_blog.atcoder.abc170.abc170_e2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        new Writer().write(new Main(new Reader()).execute());
    }

    final int n, q;
    final int max_yochien;
    final Yochien[] yochiens;
    final int[][] infants, tenens;

    private static final int RATE = 0, ENJI = 0;
    private static final int YOEN = 1;

    Main(Reader reader) throws Exception {
        this.n = reader.readInt();
        this.q = reader.readInt();
        int max_yochien = 0;
        int[][] infants = new int[n][];
        for (int i = 0; i < n; i++) {
            infants[i] = new int[2];
            infants[i][RATE] = reader.readInt();
            infants[i][YOEN] = reader.readInt();
            max_yochien = max_yochien > infants[i][YOEN] ? max_yochien : infants[i][YOEN];
        }
        this.infants = infants;

        int[][] tenens = new int[q][];
        for (int i = 0; i < q; i++) {
            tenens[i] = new int[2];
            tenens[i][ENJI] = reader.readInt();
            tenens[i][YOEN] = reader.readInt();
            max_yochien = max_yochien > tenens[i][YOEN] ? max_yochien : tenens[i][YOEN];
        }
        this.max_yochien = max_yochien;
        this.tenens = tenens;
        this.yochiens = yochiens();
    }

    Object[] execute() {
        Object[] output = new Object[q];
        for (int i = 0; i < tenens.length; i++) {
            tenen(tenens[i]);
            output[i] = equality();
        }
        return output;
    }

    private void tenen(int[] tenen) {
        int infant = tenen[ENJI] - 1;
        int next_yochien_id = tenen[YOEN];
        int curr_yochien_id = infants[infant][YOEN];

        yochiens[curr_yochien_id].remove(infant);
        if (yochiens[curr_yochien_id].children == null) {
            yochiens[curr_yochien_id] = null;
        }

        if (yochiens[next_yochien_id] == null) {
            yochiens[next_yochien_id] = Yochien.get(infant);
        } else {
            yochiens[next_yochien_id].children.add(infant);
        }

    }

    private int equality() {
        int equality = 1_000_000_000;
        for (Yochien yochien : yochiens) {
            if (yochien != null) {
                yochien.setMaxRate(infants);
                equality = equality > yochien.maxRate ? yochien.maxRate : equality;
            }
        }
        return equality;
    }

    private Yochien[] yochiens() {
        Yochien[] yochiens = new Yochien[max_yochien + 1];
        for (int i = 0; i < n; i++) {
            int infant_shozoku = infants[i][YOEN];
            if (yochiens[infant_shozoku] == null) {
                yochiens[infant_shozoku] = Yochien.get(i);
            } else {
                yochiens[infant_shozoku].children.add(i);
            }
        }
        return yochiens;
    }

    static class Yochien {
        int maxRate;
        InfantChain children;

        static Yochien get(int infant) {
            Yochien yochien = new Yochien();
            InfantChain children = new InfantChain();
            children.infant = infant;
            yochien.children = children;
            return yochien;

        }

        void setMaxRate(int[][] infants) {
            int yochien_max = infants[children.infant][RATE];
            InfantChain current = children;
            while (current.next != null) {
                int infant_rate = infants[current.next.infant][RATE];
                yochien_max = yochien_max > infant_rate ? yochien_max : infant_rate;
                current = current.next;
            }
            this.maxRate = yochien_max;
        }

        void remove(int infant) {

            if (this.children.infant == infant) {
                this.children = this.children.next;
                return;
            }

            InfantChain newChild = new InfantChain();
            newChild.infant = this.children.infant;

            InfantChain child = this.children;
            while (child.next != null) {
                if (child.next.infant != infant) {
                    newChild.add(child.next.infant);
                }
                child = child.next;
            }

            this.children = newChild;
        }

    }

    static class InfantChain {
        int infant;
        InfantChain next;

        void add(int infant) {
            InfantChain children = new InfantChain();
            children.infant = infant;
            children.next = this.next;
            this.next = children;
        }

    }

    private static class Reader {

        private final BufferedReader br;

        Reader() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        private int readInt() throws IOException {
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

    private static class Writer {

        private final PrintWriter pw;

        Writer() {
            this.pw = new PrintWriter(System.out);
        }

        void write(Object[] outputs) {
            for (Object output : outputs) {
                pw.println(output);
            }
            pw.flush();
        }

    }

}