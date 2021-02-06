package net.masaki_blog.atcoder.abs.abc085_b;

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

    int n;
    int[] dn;

    Main(BufferedReader br) throws Exception {
        this.n = Integer.parseInt(br.readLine());
        dn = new int[n];
        for (int i = 0; i < n; i++) {
            dn[i] = readInt(br);
        }

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
        sort();
        int count = 0;
        int before = 0;
        for (int d : dn) {
            if (d != before) {
                count++;
                before = d;
            }
        }
        return count;

    }

    void sort() {
        int[] bucket = new int[101];
        for (int a : dn) {
            bucket[a] += 1;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                dn[index] = i;
                index++;
            }
        }
    }
}