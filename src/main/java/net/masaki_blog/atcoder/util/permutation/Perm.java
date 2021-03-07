package net.masaki_blog.atcoder.util.permutation;

import java.math.BigInteger;

public class Perm {
    private final int max;
    private final long[] kai;
    private final long[] inv;

    private long law;

    public Perm(int max, long law) {
        this.max = max;
        this.law = law;
        this.kai = new long[max + 1];
        this.inv = new long[max + 1];
        init();
    }

    private void init() {
        kai[0] = 1;
        for (int i = 1; i <= max; i++) {
            kai[i] = times(kai[i - 1], i);
        }
        inv[0] = 1;
        inv[1] = 1;
        inv[max] = modInverse(kai[max]);
        for (int i = max - 1; i > 1; i--) {
            inv[i] = times(inv[i + 1], i + 1);
        }
    }

    private long modInverse(long val) {
        return BigInteger.valueOf(val).modInverse(BigInteger.valueOf(law)).longValue();
    }

    /**
     *  P(n,k)= n!/(n-k)!
     */
    public long get(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return times(kai[n], inv[n - k]);
    }

    private long times(long a, long b) {
        return mod(a * b);
    }

    private long mod(long val) {
        if (val >= law) {
            val = val % law;
        }
        return val;
    }

}
