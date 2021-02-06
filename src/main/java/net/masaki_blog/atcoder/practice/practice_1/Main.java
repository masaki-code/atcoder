package net.masaki_blog.atcoder.practice.practice_1;

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

	int a;
	int b;
	int c;
	String s;

	Main(BufferedReader br) throws Exception {
		this.a = Integer.parseInt(br.readLine());

		String[] bc = br.readLine().split(" ");
		this.b = Integer.parseInt(bc[0]);
		this.c = Integer.parseInt(bc[1]);

		this.s = br.readLine();
	}

	String execute() {
		int sum = a + b + c;
		return sum + " " + s;

	}

}