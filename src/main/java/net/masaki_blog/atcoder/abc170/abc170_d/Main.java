package net.masaki_blog.atcoder.abc170.abc170_d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(new Main(br).execute());
        pw.flush();

    }

    final int n, result;

    Main(BufferedReader br) throws Exception {
        this.n = Integer.parseInt(br.readLine());
        int[] an = this.getAnAndSort(br);
        this.result = this.getResult(an);
    }

    private int getResult(int[] an) {
        int result = 0;

        int index = -1;
        int[] checkList = new int[n];

        for (int i = 0; i < n; i++) {
            boolean isDivisible = isDivisible(index, an[i], checkList);
            if (!isDivisible) {
                result++;

                boolean sameLeft = 0 <= i - 1 && an[i] == an[i - 1];
                boolean sameRight = i + 1 < n && an[i] == an[i + 1];

                if (sameLeft || sameRight) {
                    result--;
                }

                index++;
                checkList[index] = an[i];
            }
        }

        return result;

    }

    private boolean isDivisible(int index, int next, int[] checkList) {
        for (int i = 0; i <= index; i++) {
            if (next % checkList[i] == 0) {
                return true;
            }
        }
        return false;

    }

    private int[] getAnAndSort(BufferedReader br) throws IOException {
        int[] an = new int[n];

        int[] bucket = new int[1000001];
        for (int i = 0; i < n; i++) {
            bucket[readInt(br)]++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                an[index] = i;
                index++;
            }

        }
        return an;
    }

    private int readInt(BufferedReader br) throws IOException {
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

    int execute() {
        return result;

    }
}