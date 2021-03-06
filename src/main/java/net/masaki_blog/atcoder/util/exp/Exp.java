package net.masaki_blog.atcoder.util.exp;

public class Exp {

    private final long law;

    private final long[] list;

    Exp(int tei, int max, long law) {
        this.law = law;
        this.list = list(tei, max);
    }

    long get(int i) {
        return list[i];
    }

    private long[] list(int tei, int max) {
        long[] list = new long[max + 1];
        list[0] = 1;
        for (int i = 1; i <= max; i++) {
            list[i] = times(list[i - 1], tei);
        }
        return list;
    }

    private long times(long a, long b) {
        long val = a * b;
        if (val >= law) {
            val = val % law;
        }
        return val;
    }
}
