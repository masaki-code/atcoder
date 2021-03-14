package net.masaki_blog.atcoder.util.factorial;

import java.math.BigInteger;

public class Fact {
    private final int max;
    private final long[] fac;
    private final long[] inv;

    private long law;

    public Fact(int max, long law) {
        this.max = max;
        this.law = law;
        this.fac = new long[max + 1];
        this.inv = new long[max + 1];
        init();
    }

    private void init() {
        fac[0] = 1;
        for (int i = 1; i <= max; i++) {
            fac[i] = times(fac[i - 1], i);
        }
        inv[0] = 1;
        inv[1] = 1;
        inv[max] = modInverse(fac[max]);
        for (int i = max - 1; i > 1; i--) {
            inv[i] = times(inv[i + 1], i + 1);
        }
    }

    private long modInverse(long val) {
        return BigInteger.valueOf(val).modInverse(BigInteger.valueOf(law)).longValue();
    }

    /**
     *  n!
     */
    public long get(int n) {
        return fac[n];
    }

    /**
     *  nCk= n!/[k! (n-k)!]
     */
    public long comb(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return times(fac[n], inv[k], inv[n - k]);
    }

    /**
     *  P(n,k)= n!/(n-k)!
     */
    public long perm(int n, int k) {
        if (k == 0) {
            return 1;
        }
        return times(fac[n], inv[n - k]);
    }

    private long times(long a, long b, long c) {
        return times(times(a, b), c);

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
