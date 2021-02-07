package net.masaki_blog.atcoder.template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        new Main(br, pw).execute();
        pw.flush();

    }

    private void output(Object obj) {
        pw.println(obj);
    }

    final PrintWriter pw;
    final int a;

    Main(BufferedReader br, PrintWriter pw) throws Exception {
        this.pw = pw;
        String s = br.readLine();
        this.a = Integer.parseInt(s);

    }

    void execute() {
        output("");
    }

}