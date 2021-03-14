package net.masaki_blog.atcoder.util.factorial;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

public class FactTest {

    @Test
    void test() {
        int max = 20;
        long mod = 1000_000_000 + 7;
        Fact f = new Fact(max, mod);
        assertThat(f.get(1), is(1L));
        assertThat(f.get(2), is(2L));
        assertThat(f.get(3), is(6L));
        assertThat(f.get(4), is(24L));
        assertThat(f.get(5), is(120L));

        mod = 23;
        f = new Fact(max, mod);
        assertThat(f.get(1), is(1L));
        assertThat(f.get(2), is(2L));
        assertThat(f.get(3), is(6L));
        assertThat(f.get(4), is(1L));
        assertThat(f.get(5), is(5L));
    }

    @Test
    void testI() {
        int max = 20;
        long mod = 23;
        Fact f = new Fact(max, mod);
        assertThat(f.inverse(1), is(1L));
        assertThat(f.inverse(2), is(12L));
        assertThat(f.inverse(3), is(4L));
        assertThat(f.inverse(4), is(1L));
        assertThat(f.inverse(5), is(14L));
    }

    @Test
    void testP() {
        int max = 20;
        long mod = 1000_000_000 + 7;
        Fact f = new Fact(max, mod);
        assertThat(f.perm(1, 0), is(1L));
        assertThat(f.perm(2, 0), is(1L));
        assertThat(f.perm(2, 1), is(2L));
        assertThat(f.perm(4, 1), is(4L));
        assertThat(f.perm(4, 2), is(12L));
        assertThat(f.perm(6, 2), is(30L));
    }

    @Test
    void testC() {
        int max = 5;
        long mod = 1000_000_000 + 7;
        Fact f = new Fact(max, mod);
        assertThat(f.comb(1, 0), is(1L));
        assertThat(f.comb(2, 0), is(1L));
        assertThat(f.comb(2, 1), is(2L));
        assertThat(f.comb(4, 1), is(4L));
        assertThat(f.comb(4, 2), is(6L));
    }
}
