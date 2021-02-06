package net.masaki_blog.atcoder.abs.abc086_a;

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

	int a, b;

	Main(BufferedReader br) throws Exception {
		String abRead = br.readLine();
		String[] ab = abRead.split(" ");

		this.a = Integer.parseInt(ab[0]);
		this.b = Integer.parseInt(ab[1]);

	}

	String execute() {
		int p = a * b;

		if (p % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}

	}

}