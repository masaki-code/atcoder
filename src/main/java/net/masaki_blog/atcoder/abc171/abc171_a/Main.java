package net.masaki_blog.atcoder.abc171.abc171_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
        String a = br.readLine();
        if (a.toUpperCase().equals(a)) {
            pw.println("A");
        } else {
            pw.println("a");
        }
        pw.flush();
    }
}