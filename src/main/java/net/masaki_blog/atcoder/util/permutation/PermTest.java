package net.masaki_blog.atcoder.util.permutation;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

public class PermTest {

    @Test
    void test() {
        int max = 20;
        long mod = 1000_000_000 + 7;
        Perm p = new Perm(max, mod);

        assertThat(p.get(1, 0), is(1L));
        assertThat(p.get(2, 0), is(1L));
        assertThat(p.get(2, 1), is(2L));
        assertThat(p.get(4, 1), is(4L));
        assertThat(p.get(4, 2), is(12L));
        assertThat(p.get(6, 2), is(30L));

    }

}
