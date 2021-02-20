package net.masaki_blog.atcoder.abc170.abc170_f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SuppressWarnings("unused")
public class Main {

    public static void main(String... args) throws Exception {
        new Main().execute();
    }

    BufferedReader br;
    PrintWriter pw;

    Main() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(System.out);
    }

    void execute() throws Exception {
        Input input = new Input(br);
        pw.flush();
    }

    private static class Input {

        final BufferedReader br;

        final int H, W, K;

        final int x1, y1;
        final int x2, y2;

        final boolean[][] lotus;

        Input(BufferedReader br) throws IOException {
            this.br = br;
            this.H = readInt(br);
            this.W = readInt(br);
            this.K = readInt(br);
            this.x1 = readInt(br);
            this.y1 = readInt(br);
            this.x2 = readInt(br);
            this.y2 = readInt(br);

            this.lotus = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    lotus[i][j] = readLotus(br);
                }
                br.read();
            }
        }

        boolean readLotus(BufferedReader br) throws IOException {
            return br.read() == '@';
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