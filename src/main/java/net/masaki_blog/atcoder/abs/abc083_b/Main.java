package net.masaki_blog.atcoder.abs.abc083_b;

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

	final int n, a, b;

	Main(BufferedReader br) throws Exception {
		String[] nab = br.readLine().split(" ");
		this.n = Integer.parseInt(nab[0]);
		this.a = Integer.parseInt(nab[1]);
		this.b = Integer.parseInt(nab[2]);
	}

	int execute() {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			int val = value(i);
			if (a <= val && val <= b) {
				sum += i;
			}

		}
		return sum;
	}

	private static int value(int n) {
		int ret = 0;
		int q = n;
		while (true) {
			if (q == 0) {
				return ret;
			}
			ret += q % 10;
			q = q / 10;
		}
	}
}