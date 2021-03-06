package net.masaki_blog.atcoder.util.comb;

import java.math.BigInteger;

import net.masaki_blog.atcoder.util.mod.Mod;

public class Comb {
    private final int max;
    private final long[] kai;
    private final long[] inv;

    private long mod;

    private Mod m;

    Comb(int max, long mod) {
        this.max = max;
        this.mod = mod;
        this.m = new Mod(mod);
        this.kai = new long[max + 1];
        this.inv = new long[max + 1];
        init();
    }

    private void init() {
        kai[0] = 1;
        for (int i = 1; i <= max; i++) {
            kai[i] = m.times(kai[i - 1], i);
        }
        inv[0] = 1;
        inv[1] = 1;
        inv[max] = modInverse(kai[max]);
        for (int i = max - 1; i > 1; i--) {
            inv[i] = m.times(inv[i + 1], i + 1);
        }
    }

    private long modInverse(long val) {
        return BigInteger.valueOf(val).modInverse(BigInteger.valueOf(mod)).longValue();
    }

    long get(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return m.times(kai[n], inv[k], inv[n - k]);
    }
}
