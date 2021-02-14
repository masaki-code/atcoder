package net.masaki_blog.atcoder.util.chaine;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.function.BiPredicate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChaineTest {

    @BeforeEach
    public void before() {

    }

    @AfterEach
    public void after() {

    }

    @Test
    void test_sort() throws Exception {
        Chaine<Simple> chaine = new Chaine<Simple>();
        chaine.addInstance(new Simple(2), Simple.order);
        chaine.addInstance(new Simple(1), Simple.order);
        chaine.addInstance(new Simple(3), Simple.order);
        chaine.addInstance(new Simple(2), Simple.order);

        Chaine<Simple> test = chaine;
        assertThat(test.ins.val, is(1));
        test = test.next;
        assertThat(test.ins.val, is(2));
        test = test.next;
        assertThat(test.ins.val, is(2));
        test = test.next;
        assertThat(test.ins.val, is(3));
    }

    @Test
    void test_remove() throws Exception {
        Simple data_1 = new Simple(2);
        Simple data_2 = new Simple(3);
        Simple data_3 = new Simple(1);
        Simple data_4 = new Simple(2);

        Chaine<Simple> chaine = new Chaine<Simple>();
        chaine.addInstance(data_1, Simple.order);
        chaine.addInstance(data_2, Simple.order);
        chaine.addInstance(data_3, Simple.order);

        // test
        Chaine<Simple> test = chaine;
        assertThat(test.ins, is(data_3));
        test = test.next;
        assertThat(test.ins, is(data_1));
        test = test.next;
        assertThat(test.ins, is(data_2));

        // remove
        chaine.removeInstance(data_1);

        // test
        test = chaine;
        assertThat(test.ins, is(data_3));
        test = test.next;
        assertThat(test.ins, is(data_2));

        // remove
        chaine.removeInstance(data_3);

        // test
        test = chaine;
        assertThat(test.ins, is(data_2));

        // remove
        chaine.removeInstance(data_3);

        // test
        test = chaine;
        assertThat(test.ins, is(data_2));

        // remove
        chaine.removeInstance(data_2);

        // test
        test = chaine;
        assertThat(test.ins, is(nullValue()));

        //add
        chaine.addInstance(data_1, Simple.order);
        chaine.addInstance(data_2, Simple.order);
        chaine.addInstance(data_3, Simple.order);
        chaine.addInstance(data_4, Simple.order);

        // test
        test = chaine;
        assertThat(test.ins, is(data_3));
        test = test.next;
        assertThat(test.ins, is(data_1));
        test = test.next;
        assertThat(test.ins, is(data_4));
        test = test.next;
        assertThat(test.ins, is(data_2));

    }

    static class Simple {
        final int val;

        Simple(int val) {
            this.val = val;
        }

        static BiPredicate<Simple, Simple> order = new BiPredicate<ChaineTest.Simple, ChaineTest.Simple>() {

            @Override
            public boolean test(Simple t, Simple u) {
                return t.val <= u.val;
            }
        };

    }

}