package net.masaki_blog.atcoder.abs.abc081_b;

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

	int n;
	int[] an;

	Main(BufferedReader br) throws Exception {
		this.n = Integer.parseInt(br.readLine());

		String[] ans = br.readLine().split(" ");
		int[] an = new int[ans.length];
		for (int i = 0; i < an.length; i++) {
			an[i] = Integer.parseInt(ans[i]);
		}

		this.an = an;
	}

	int execute() {
		int count = 0;
		while (true) {
			for (int i = 0; i < an.length; i++) {
				if (an[i] % 2 == 0) {
					an[i] = an[i] / 2;
				} else {
					return count;
				}
			}

			count++;
		}
	}

}