package net.masaki_blog.atcoder.abs.abc088_b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(new Main(br).execute());
        pw.flush();

    }

    int n;
    int[] an;

    Main(BufferedReader br) throws Exception {
        n = Integer.parseInt(br.readLine());
        int[] an = new int[n];
        for (int i = 0; i < n; i++) {
            int a = 0;
            while (true) {
                int read = br.read();
                if (48 <= read && read <= 57) {
                    a = a * 10 + read - 48;
                } else {
                    break;
                }
            }

            an[i] = a;
        }
        this.an = an;
    }

    int execute() {
        sort();

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result += an[n - i - 1];
            } else {
                result -= an[n - i - 1];
            }
        }
        return result;
    }

    void sort() {
        int[] bucket = new int[101];

        for (int a : an) {
            bucket[a] += 1;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                an[index] = i;
                index++;
            }

        }

    }

}