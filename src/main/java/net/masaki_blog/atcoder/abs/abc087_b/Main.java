package net.masaki_blog.atcoder.abs.abc087_b;

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

	int a, b, c, x;

	Main(BufferedReader br) throws Exception {
		this.a = Integer.parseInt(br.readLine());
		this.b = Integer.parseInt(br.readLine());
		this.c = Integer.parseInt(br.readLine());
		this.x = Integer.parseInt(br.readLine());

	}

	int execute() {

		int count = 0;
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				for (int k = 0; k <= c; k++) {
					if (500 * i + 100 * j + 50 * k == x) {
						count++;
					}
				}
			}
		}
		return count;
	}
}