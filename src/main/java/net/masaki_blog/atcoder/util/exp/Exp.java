package net.masaki_blog.atcoder.util.exp;

import net.masaki_blog.atcoder.util.mod.Mod;

public class Exp {
    private final int tei;
    private final int max;
    private final long[] val;

    private Mod m;

    Exp(int tei, int max, long mod) {
        this.tei = tei;
        this.max = max;
        this.m = new Mod(mod);
        this.val = new long[max + 1];
        init();
    }

    private void init() {
        val[0] = 1;
        for (int i = 1; i <= max; i++) {
            val[i] = m.times(val[i - 1], tei);
        }
    }

    long get(int i) {
        return val[i];
    }
}
