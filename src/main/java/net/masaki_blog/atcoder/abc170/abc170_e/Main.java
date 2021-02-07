package net.masaki_blog.atcoder.abc170.abc170_e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        for (Object output : new Main(br).execute()) {
            pw.println(output);
        }
        pw.flush();
    }

    private static final int RATE = 0;
    private static final int CHILD = 0;
    private static final int YOCHIEN = 1;

    final int[][] children, tenen;
    final int n, q;
    final int max;

    Main(BufferedReader br) throws Exception {
        this.n = readInt(br);
        this.q = readInt(br);

        int max = 0;
        children = new int[n][2];
        for (int i = 0; i < n; i++) {
            children[i] = new int[2];
            children[i][RATE] = readInt(br);
            children[i][YOCHIEN] = readInt(br);
            max = max > children[i][YOCHIEN] ? max : children[i][YOCHIEN];
        }

        tenen = new int[q][2];
        for (int i = 0; i < q; i++) {
            tenen[i] = new int[2];
            tenen[i][CHILD] = readInt(br);
            tenen[i][YOCHIEN] = readInt(br);
            max = max > tenen[i][YOCHIEN] ? max : tenen[i][YOCHIEN];
        }

        this.max = max;
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

    Object[] execute() {
        Object[] output = new Object[q];

        int index = 0;

        for (int[] en : tenen) {
            int target = en[CHILD] - 1;
            int next = en[YOCHIEN];

            children[target][YOCHIEN] = next;
            output[index] = equality();
            index++;
        }

        return output;
    }

    private int equality() {
        int[] bucket = new int[max];

        for (int[] child : children) {
            int rate = child[RATE];
            int yochien = child[YOCHIEN] - 1;
            int max_rate = bucket[yochien];
            bucket[yochien] = max_rate > rate ? max_rate : rate;
        }

        int equality = 1_000_000_000;
        for (int yochien : bucket) {
            if (yochien != 0 && yochien < equality) {
                equality = yochien;
            }
        }
        return equality;
    }
}