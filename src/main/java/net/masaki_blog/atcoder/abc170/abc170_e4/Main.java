package net.masaki_blog.atcoder.abc170.abc170_e4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        YochienChaine.init();
        Tenen.init(input);
    }

    void execute(PrintWriter pw) {
        YochienChaine yochienChaine = YochienChaine.yochienChaine;
        for (Tenen tenen : Tenen.list) {
            Infant infant = tenen.infant;
            Yochien before = infant.yochien;
            Yochien after = tenen.yochien;

            before.removeInfant(infant);
            after.addInfant(infant);

            pw.println(yochienChaine.yochien.getMaxRate());

        }

    }

    private static class Global {

        static void init(Input input) {
            int n = input.n;

            Infant.init(n);
            Yochien.init();
            int[] rates = input.infantRates;
            int[] yoens = input.infantYoens;
            for (int i = 0; i < n; i++) {
                int no = i + 1;
                int yoen = yoens[i];
                int rate = rates[i];
                Infant infant = Infant.get(no);
                infant.rate = rate;
                Yochien yochien = Yochien.get(yoen);
                yochien.initInfant(infant);
            }
        }
    }

    private static class YochienChaine {
        static YochienChaine yochienChaine;

        static void init() {
            YochienChaine yochienChaine = new YochienChaine();
            for (Yochien yochien : Yochien.yochiens) {
                if (yochien != null && yochien.getMaxRate() != Yochien.NO_INFANT) {
                    yochienChaine.add(yochien);
                }
            }
            YochienChaine.yochienChaine = yochienChaine;
        }

        private Yochien yochien = null;
        private YochienChaine prev = null;
        private YochienChaine next = null;

        private static int getRate(YochienChaine chaine) {
            if (chaine == null || chaine.yochien == null) {
                return Yochien.NO_INFANT;
            }
            return chaine.yochien.getMaxRate();
        }

        static void reset(Yochien target) {
            if (target.yochienChaine != null) {
                shiftCenter(target);
            } else {
                YochienChaine yochienChaine = YochienChaine.yochienChaine;
                int targetMaxRate = target.getMaxRate();
                int currentMaxRate = getRate(yochienChaine);

                if (targetMaxRate > currentMaxRate) {
                    yochienChaine.shiftRight(targetMaxRate, target);
                } else {
                    YochienChaine newNext = new YochienChaine();
                    newNext.yochien = yochienChaine.yochien;
                    newNext.next = yochienChaine.next;
                    yochienChaine.yochien = target;
                    yochienChaine.next = newNext;
                    yochienChaine.next.prev = yochienChaine;
                }
            }
        }

        private static void shiftCenter(Yochien target) {
            YochienChaine current = target.yochienChaine;
            YochienChaine prev = current.prev;
            YochienChaine next = current.next;
            int prevRate = getRate(prev);
            int nextRate = getRate(next);
            int targetRate = target.getMaxRate();

            if (prevRate <= targetRate && targetRate <= nextRate) {
                return;
            }

            if (targetRate < prevRate) {
                current.shiftLeft(targetRate, target);
            } else {
                current.shiftRight(targetRate, target);
            }

            pack(target);

        }

        private void shiftLeft(int targetRate, Yochien target) {
            if (targetRate > yochien.getMaxRate() && this.prev != null) {
                this.prev.shiftLeft(targetRate, target);
            } else {
                YochienChaine newNext = new YochienChaine();
                newNext.yochien = yochien;
                newNext.next = next;
                this.yochien = target;
                this.next = newNext;
                this.next.prev = this;
            }
        }

        private void shiftRight(int targetRate, Yochien target) {
            if (targetRate < yochien.getMaxRate() && this.next != null) {
                this.prev.shiftRight(targetRate, target);
            } else {
                YochienChaine newNext = new YochienChaine();
                newNext.yochien = yochien;
                newNext.next = next;
                this.yochien = target;
                this.next = newNext;
                this.next.prev = this;
            }
        }

        private static void pack(Yochien target) {
            YochienChaine currentChaine = target.yochienChaine;
            if (currentChaine.next != null) {
                currentChaine.yochien = currentChaine.next.yochien;
                currentChaine.next = currentChaine.next.next;
                if (currentChaine.next != null) {
                    currentChaine.next.prev = currentChaine;
                }
            } else {
                currentChaine.yochien = null;
                currentChaine.next = null;
            }
        }

        void add(Yochien target) {
            if (target.getMaxRate() == Yochien.NO_INFANT) {
                return;
            }

            if (yochien == null) {
                this.yochien = target;
                this.yochien.yochienChaine = this;
                return;
            }

            if (next == null) {
                next = new YochienChaine();
                next.yochien = target;
                next.yochien.yochienChaine = next;
                this.next.prev = this;
                return;
            }

            if (this.yochien.getMaxRate() < target.getMaxRate()) {
                this.next.add(target);
            } else {
                YochienChaine newNext = new YochienChaine();
                newNext.yochien = yochien;
                newNext.next = next;
                newNext.yochien.yochienChaine = newNext;
                yochien = target;
                next = newNext;
                this.next.prev = this;
                this.yochien.yochienChaine = this;
            }

        }

        void remove(Yochien target) {
            if (target.getMaxRate() == Yochien.NO_INFANT) {
                return;
            }

            YochienChaine chaine = this;
            while (chaine.yochien != target) {
                chaine = chaine.next;
            }

            if (chaine.next != null) {
                chaine.yochien = chaine.next.yochien;
                chaine.next = chaine.next.next;
            } else {
                chaine.yochien = null;
                chaine.next = null;
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

    private static class Yochien {

        static final int NO_INFANT = -1;

        static Yochien[] yochiens;

        static void init() {
            Yochien.yochiens = new Yochien[2_000_00];
        }

        int no; // 1-2*10^5

        YochienChaine yochienChaine;

        InfantChaine infantChaine = new InfantChaine();

        int getMaxRate() {
            if (infantChaine.infant != null) {
                return infantChaine.infant.rate;
            } else {
                return NO_INFANT;
            }
        }

        void initInfant(Infant infant) {
            infant.yochien = this;
            this.infantChaine.add(infant);
        }

        void addInfant(Infant infant) {
            infant.yochien = this;
            this.infantChaine.add(infant);

            //
            YochienChaine current = this.yochienChaine;
            YochienChaine prev = current.prev;
            YochienChaine next = current.next;

            int afterRate = this.getMaxRate();
            int prevRate = YochienChaine.getRate(prev);
            int nextRate = YochienChaine.getRate(next);

            if (prevRate <= afterRate && afterRate <= nextRate) {
                return;
            }

            if (prevRate > afterRate) {
                current.shiftLeft(afterRate, this);
            } else {
                current.shiftRight(afterRate, this);
            }
        }

        void removeInfant(Infant infant) {
            infant.yochien = null;

            YochienChaine current = this.yochienChaine;
            YochienChaine prev = current.prev;
            YochienChaine next = current.next;

            this.infantChaine.remove(infant);
            int afterRate = this.getMaxRate();
            if (afterRate == Yochien.NO_INFANT) {
                prev.next = next;
                return;
            }

            int prevRate = YochienChaine.getRate(prev);
            int nextRate = YochienChaine.getRate(next);

            if (prevRate <= afterRate && afterRate <= nextRate) {
                return;
            }

            if (prevRate > afterRate) {
                current.shiftLeft(afterRate, this);
            } else {
                current.shiftRight(afterRate, this);
            }

        }

        static Yochien get(int no) {
            int index = no - 1;
            if (Yochien.yochiens[index] == null) {
                Yochien.yochiens[index] = new Yochien();
                Yochien.yochiens[index].no = no;
            }
            return Yochien.yochiens[index];
        }

    }

    private static class Infant {

        int no; // 1-N
        int rate; // 1-10^9
        Yochien yochien;
        InfantChaine chain;

        static Infant[] infants;

        static void init(int count) {
            Infant.infants = new Infant[count];
        }

        static Infant get(int no) {
            int index = no - 1;
            if (Infant.infants[index] == null) {
                Infant.infants[index] = new Infant();
                Infant.infants[index].no = no;
            }
            return Infant.infants[index];
        }
    }

    private static class InfantChaine {
        Infant infant;
        InfantChaine next;

        void add(Infant target) {
            if (this.infant == null) {
                this.infant = target;
                this.infant.chain = this;
                return;
            }

            if (next == null) {
                next = new InfantChaine();
                next.infant = target;
                next.infant.chain = next;
                return;
            }

            if (this.infant.rate > target.rate) {
                this.next.add(target);
            } else {
                InfantChaine newNext = new InfantChaine();
                newNext.infant = infant;
                newNext.next = next;
                newNext.infant.chain = newNext;

                this.infant = target;
                this.next = newNext;
                this.infant.chain = this;
            }
        }

        void remove(Infant target) {
            InfantChaine chaine = this;
            while (chaine.infant != target) {
                chaine = chaine.next;
            }

            if (chaine.next != null) {
                chaine.infant = chaine.next.infant;
                chaine.next = chaine.next.next;
            } else {
                chaine.infant = null;
                chaine.next = null;
            }
        }

        //      static InfantChaine get(Infant[] infants) {
        //      InfantChaine chaine = new InfantChaine();
        //      for (Infant infant : infants) {
        //          chaine.add(infant);
        //      }
        //      return chaine;
        //  }

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