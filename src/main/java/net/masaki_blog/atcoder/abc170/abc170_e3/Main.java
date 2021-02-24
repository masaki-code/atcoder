package net.masaki_blog.atcoder.abc170.abc170_e3;

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

    final int n, q;

    Main(BufferedReader br) throws Exception {

        this.n = readInt(br);
        this.q = readInt(br);

        int[] infantRates = new int[n];
        int[] infantYoens = new int[n];

        Yochien[] yochiens = new Yochien[2_00_000 + 1];
        for (int i = 0; i < n; i++) {
            infantRates[i] = readInt(br);
            infantYoens[i] = readInt(br);

            int yochienNo = infantYoens[i];
            if (yochiens[yochienNo] == null) {
                yochiens[yochienNo] = new Yochien();
            }
            yochiens[yochienNo].maxInfantNumber++;
        }

        int[] tenenEnjis = new int[q];
        int[] tenenYoens = new int[q];
        for (int i = 0; i < q; i++) {
            tenenEnjis[i] = readInt(br);
            tenenYoens[i] = readInt(br);

            int yochienNo = tenenYoens[i];
            if (yochiens[yochienNo] == null) {
                yochiens[yochienNo] = new Yochien();
            }
            yochiens[yochienNo].maxInfantNumber++;
        }

        for (Yochien yochien : yochiens) {
            if (yochien != null) {
                yochien.infants = new Infant[yochien.maxInfantNumber];
            }
        }

        Infant[] infants = new Infant[n];
        for (int i = 0; i < n; i++) {
            infants[i] = new Infant();
            infants[i].rate = infantRates[i];

            int yochienNo = infantYoens[i];
            Yochien yochien = yochiens[yochienNo];
            Infant infant = infants[i];
            yochien.index++;
            yochien.infants[yochien.index] = infant;
            infant.yochien = yochien;
            infant.noInYochien = yochien.index;

            if (infant.rate > yochien.maxRate) {
                yochien.maxRate = infant.rate;
            }
        }

        Yochiens objYochiens = new Yochiens();
        objYochiens.yochiens = yochiens;
        objYochiens.updateEquality();

        this.yochiens = objYochiens;

        Infant[] tenenInfants = new Infant[q];
        Yochien[] tenenYochiens = new Yochien[q];

        for (int i = 0; i < q; i++) {
            int tenenEnjiNo = tenenEnjis[i];
            int tenenYoenNo = tenenYoens[i];
            tenenInfants[i] = infants[tenenEnjiNo - 1];
            tenenYochiens[i] = yochiens[tenenYoenNo];
        }

        this.tenenInfants = tenenInfants;
        this.tenenYochiens = tenenYochiens;
    }

    final Yochiens yochiens;
    final Infant[] tenenInfants;
    final Yochien[] tenenYochiens;

    void execute(PrintWriter pw) {

        for (int i = 0; i < q; i++) {
            yochiens.tenen(tenenInfants[i], tenenYochiens[i]);
            pw.println(yochiens.equality);
        }
    }

    private static class Yochiens {
        int equality = 1_000_000_000;
        Yochien[] yochiens;

        void tenen(Infant infant, Yochien tenenYochien) {
            Yochien corrYoen = infant.yochien;
            Yochien nextYoen = tenenYochien;

            int corrYoenBeforeRate = corrYoen.maxRate;
            int nextYoenBeforeRate = nextYoen.maxRate;

            corrYoen.remove(infant);
            nextYoen.add(infant);

            int corrYoenAfterRate = corrYoen.maxRate;
            int nextYoenAfterRate = nextYoen.maxRate;

            int afterMinRate = getMin(corrYoenAfterRate, nextYoenAfterRate);
            if (afterMinRate < equality) {
                equality = afterMinRate;
                return;
            }

            if (corrYoenBeforeRate == equality
                    && corrYoenAfterRate == Yochien.NO_INFANTS_RATE) {
                updateEquality();
                return;
            }

            if (nextYoenBeforeRate == equality
                    && nextYoenAfterRate != equality) {
                updateEquality();
                return;
            }

        }

        private int getMin(int cRate, int nRate) {
            if (cRate == Yochien.NO_INFANTS_RATE) {
                return nRate;
            }

            return cRate < nRate ? cRate : nRate;
        }

        void updateEquality() {
            int equality = 1_000_000_000;
            for (Yochien yochien : yochiens) {
                if (yochien == null || yochien.maxRate == Yochien.NO_INFANTS_RATE) {
                    continue;
                }

                if (yochien.maxRate < equality) {
                    equality = yochien.maxRate;
                }
            }
            this.equality = equality;
        }

    }

    private static class Yochien {

        static final int NO_INFANTS_RATE = -1;

        int index = -1;
        int maxRate = NO_INFANTS_RATE;
        int maxInfantNumber = 0;
        Infant[] infants;

        void add(Infant infant) {
            if (infant.rate > maxRate) {
                maxRate = infant.rate;
            }

            index++;
            infants[index] = infant;
            infant.noInYochien = index;
            infant.yochien = this;
        }

        void remove(Infant target) {
            infants[target.noInYochien] = null;
            if (target.rate != maxRate) {
                return;
            }

            int newMaxRate = NO_INFANTS_RATE;
            for (int i = 0; i <= index; i++) {
                if (infants[i] != null && infants[i].rate > newMaxRate) {
                    newMaxRate = infants[i].rate;
                }
            }
            this.maxRate = newMaxRate;
        }
    }

    private static class Infant {
        int rate;
        int noInYochien;
        Yochien yochien;
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