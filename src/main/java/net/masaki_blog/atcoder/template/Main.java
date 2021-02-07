package net.masaki_blog.atcoder.template;

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

    final int a;

    Main(BufferedReader br) throws Exception {

        String s = br.readLine();
        this.a = Integer.parseInt(s);

    }

    Object execute() {
        return "";
    }

}