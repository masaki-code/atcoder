package net.masaki_blog.atcoder.util.combination;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

class CombTest {

    @Test
    void test() {
        int max = 5;
        long mod = 1000_000_000 + 7;
        Comb c = new Comb(max, mod);

        assertThat(c.get(1, 0), is(1L));
        assertThat(c.get(2, 0), is(1L));
        assertThat(c.get(2, 1), is(2L));
        assertThat(c.get(4, 1), is(4L));
        assertThat(c.get(4, 2), is(6L));

    }

}
