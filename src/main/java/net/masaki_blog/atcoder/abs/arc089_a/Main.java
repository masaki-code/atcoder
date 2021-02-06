package net.masaki_blog.atcoder.abs.arc089_a;

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

    final int n;
    final int[] tn, xn, yn;

    Main(BufferedReader br) throws Exception {
        this.n = Integer.parseInt(br.readLine());
        tn = new int[n];
        xn = new int[n];
        yn = new int[n];
        for (int i = 0; i < n; i++) {
            String[] txy = br.readLine().split(" ");
            tn[i] = Integer.parseInt(txy[0]);
            xn[i] = Integer.parseInt(txy[1]);
            yn[i] = Integer.parseInt(txy[2]);
        }
    }

    String execute() {

        int t = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < n; i++) {
            int diff_t = tn[i] - t;
            int diff_x = xn[i] - x;
            int diff_y = yn[i] - y;

            diff_x = diff_x > 0 ? diff_x : -diff_x;
            diff_y = diff_y > 0 ? diff_y : -diff_y;

            if (diff_t < diff_x + diff_y) {
                return "No";
            }

            int diff_txy = diff_t - diff_x - diff_y;
            if (diff_txy % 2 == 1) {
                return "No";

            }

            t = tn[i];
            x = xn[i];
            y = yn[i];
        }
        return "Yes";
    }
}