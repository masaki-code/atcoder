package net.masaki_blog.atcoder.abc170.abc170_e4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.function.BiPredicate;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        new Main(br).execute(pw);
        pw.flush();

    }

    Main(BufferedReader br) throws Exception {
        Input input = new Input(br);
        Global.init(input);
        Tenen.init(input);
    }

    void execute(PrintWriter pw) {
        for (Tenen tenen : Tenen.list) {
            Infant infant = tenen.infant;
            Yochien before = infant.yochien;
            Yochien after = tenen.yochien;
            before.removeInfant(infant);
            after.addInfant(infant);
            pw.println(Global.equality());
        }
    }

    private static class Global {
        static YochienChaine yochienChaine;

        static int equality() {
            yochienChaine = yochienChaine.getFirst();
            return yochienChaine.ins.getMaxRate();
        }

        static void init(Input input) {
            yochienChaine = new YochienChaine();
            int n = input.n;
            Infant.infants = new Infant[n];
            Yochien.yochiens = new Yochien[2_000_00];
            int[] rates = input.infantRates;
            int[] yoens = input.infantYoens;
            for (int i = 0; i < n; i++) {
                int no = i + 1;
                int yoen = yoens[i];
                int rate = rates[i];
                Infant infant = Infant.get(no);
                infant.rate = rate;
                Yochien yochien = Yochien.get(yoen);
                yochien.addInfant(infant);
            }
            for (Yochien yochien : Yochien.yochiens) {
                if (yochien != null) {
                    yochienChaine.addInstance(yochien);
                }
            }
        }
    }

    private static class Yochien {
        static final int NO_INFANT = -1;

        private static Yochien[] yochiens;

        YochienChaine chaine;

        InfantChaine infantChaine = new InfantChaine();

        int getMaxRate() {
            if (infantChaine.ins != null) {
                return infantChaine.ins.rate;
            } else {
                return NO_INFANT;
            }
        }

        static Yochien get(int no) {
            int index = no - 1;
            if (Yochien.yochiens[index] == null) {
                Yochien.yochiens[index] = new Yochien();
            }
            return Yochien.yochiens[index];
        }

        void addInfant(Infant infant) {
            infant.yochien = this;
            this.infantChaine.addInstance(infant);
            if (this.chaine != null) {
                this.chaine.reset();
            } else {
                Global.yochienChaine.addInstance(this);
            }
        }

        void removeInfant(Infant infant) {
            this.infantChaine.removeInstance(infant);
            infant.yochien = null;

            if (this.getMaxRate() == NO_INFANT) {
                this.chaine.removeSelf();
            } else {
                this.chaine.reset();
            }

        }
    }

    private static class YochienChaine {
        Yochien ins;
        YochienChaine prev;
        YochienChaine next;

        static BiPredicate<Yochien, Yochien> order = new BiPredicate<Yochien, Yochien>() {
            @Override
            public boolean test(Yochien t, Yochien u) {
                return t.getMaxRate() <= u.getMaxRate();
            }
        };

        public YochienChaine getFirst() {
            YochienChaine result = this;
            while (result.prev != null) {
                result = result.prev;
            }
            return result;
        }

        void addInstance(Yochien newInstance) {
            if (this.ins == null) {
                this.ins = newInstance;
                this.ins.chaine = this;
                return;
            }

            if (order.test(this.ins, newInstance)) {
                if (this.next == null) {
                    YochienChaine newNext = new YochienChaine();
                    newNext.ins = newInstance;
                    newNext.ins.chaine = newNext;
                    this.next = newNext;
                } else {
                    this.next.addInstance(newInstance);
                }
                this.next.prev = this;
            } else {
                YochienChaine newNext = new YochienChaine();
                newNext.ins = this.ins;
                newNext.ins.chaine = newNext;
                newNext.next = this.next;
                this.ins = newInstance;
                this.ins.chaine = this;
                this.next = newNext;
                this.next.prev = this;
            }
        }

        void addInstancePrev(Yochien newInstance) {
            if (this.ins == null) {
                this.ins = newInstance;
                this.ins.chaine = this;
                return;
            }

            if (order.test(newInstance, this.ins)) {
                if (this.prev == null) {
                    YochienChaine newNext = new YochienChaine();
                    newNext.ins = this.ins;
                    newNext.ins.chaine = newNext;
                    newNext.next = this.next;

                    this.ins = newInstance;
                    this.ins.chaine = this;
                    this.next = newNext;
                    this.next.prev = this;
                } else {
                    this.prev.addInstancePrev(newInstance);
                }
            } else {
                YochienChaine newNext = new YochienChaine();
                newNext.ins = newInstance;
                newNext.ins.chaine = newNext;
                newNext.next = this.next;
                this.next = newNext;
                this.next.prev = this;
            }
        }

        void reset() {
            Yochien beforeIns = this.ins;
            if (this.prev != null && order.test(this.ins, this.prev.ins)) {
                this.removeSelf();
                this.addInstancePrev(beforeIns);
            }

            if (this.next != null && order.test(this.next.ins, this.ins)) {
                this.addInstance(beforeIns);
                this.removeSelf();
            }
        }

        private void removeSelf() {
            if (this.next == null) {
                this.ins = null;
                if (this.prev != null) {
                    this.prev.next = null;
                }
            } else {
                this.ins = this.next.ins;
                this.ins.chaine = this;
                this.next = this.next.next;
                if (this.next != null) {
                    this.next.prev = this;
                }
            }
        }
    }

    private static class Infant {

        int rate;
        Yochien yochien;

        private static Infant[] infants;

        static Infant get(int no) {
            int index = no - 1;
            if (Infant.infants[index] == null) {
                Infant.infants[index] = new Infant();
            }
            return Infant.infants[index];
        }

    }

    private static class InfantChaine {
        Infant ins;
        InfantChaine next;

        static final BiPredicate<Infant, Infant> order = new BiPredicate<Infant, Infant>() {
            @Override
            public boolean test(Infant first, Infant second) {
                return first.rate >= second.rate;
            }
        };

        void addInstance(Infant newInstance) {
            if (this.ins == null) {
                this.ins = newInstance;
                return;
            }

            if (order.test(this.ins, newInstance)) {
                if (this.next == null) {
                    InfantChaine newNext = new InfantChaine();
                    newNext.ins = newInstance;
                    this.next = newNext;
                } else {
                    this.next.addInstance(newInstance);
                }
            } else {
                InfantChaine newNext = new InfantChaine();
                newNext.ins = this.ins;
                newNext.next = this.next;
                this.next = newNext;
                this.ins = newInstance;
            }
        }

        void removeInstance(Infant target) {
            if (this.ins == target) {
                if (this.next == null) {
                    this.ins = null;
                } else {
                    this.ins = this.next.ins;
                    this.next = this.next.next;
                }
            } else {
                if (this.next != null) {
                    this.next.removeInstance(target);
                }
            }
        }
    }

    private static class Tenen {
        Infant infant;
        Yochien yochien;

        static Tenen[] list;

        static void init(Input input) {
            int q = input.q;
            int[] enjis = input.tenenEnjis;
            int[] yoens = input.tenenYoens;

            Tenen[] tenens = new Tenen[q];
            for (int i = 0; i < q; i++) {
                int infantsNo = enjis[i];
                int yochienNo = yoens[i];

                Tenen tenen = new Tenen();
                tenen.infant = Infant.get(infantsNo);
                tenen.yochien = Yochien.get(yochienNo);
                tenens[i] = tenen;
            }
            Tenen.list = tenens;
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