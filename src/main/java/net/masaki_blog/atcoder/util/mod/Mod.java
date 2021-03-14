package net.masaki_blog.atcoder.util.mod;

public class Mod {

    private final long law;

    public Mod(long law) {
        this.law = law;
    }

    public long plus(long a, long b) {
        return mod(a + b);
    }

    public long square(long a) {
        return times(a, a);
    }

    public long times(long a, long b) {
        return mod(a * b);
    }

    public long times(long a, long b, long c) {
        return mod(mod(a * b) * c);
    }

    private long mod(long val) {
        if (val >= law) {
            val = val % law;
        }
        return val;
    }
}
