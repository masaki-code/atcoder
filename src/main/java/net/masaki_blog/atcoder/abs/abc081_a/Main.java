package net.masaki_blog.atcoder.abs.abc081_a;

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

	int s1, s2, s3;

	Main(BufferedReader br) throws Exception {

		char[] sss = br.readLine().toCharArray();
		s1 = sss[0] == '1' ? 1 : 0;
		s2 = sss[1] == '1' ? 1 : 0;
		s3 = sss[2] == '1' ? 1 : 0;
	}

	int execute() {
		return s1 + s2 + s3;
	}

}