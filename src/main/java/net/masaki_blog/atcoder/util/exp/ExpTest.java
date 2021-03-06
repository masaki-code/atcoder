package net.masaki_blog.atcoder.util.exp;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

class ExpTest {

    @Test
    void test() {
        int tei = 2;
        int max = 1000_000;
        long law = 1000_000_000 + 7;
        Exp e = new Exp(tei, max, law);
        assertThat(e.get(0), is(1L));
        assertThat(e.get(1), is(2L));
        assertThat(e.get(10), is(1024L));
    }

    @Test
    void test2() {
        int tei = 25;
        int max = 10;
        long law = 1000_000_000 + 7;
        Exp e = new Exp(tei, max, law);
        assertThat(e.get(0), is(1L));
        assertThat(e.get(1), is(25L));
        assertThat(e.get(10), is(430973056L)); //25^10 = 95_367_431_640_625L
    }
}
