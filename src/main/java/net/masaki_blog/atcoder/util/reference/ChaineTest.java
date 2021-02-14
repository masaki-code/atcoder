package net.masaki_blog.atcoder.util.reference;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        assertResference(chaine);
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
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_2);

        // remove
        chaine.removeInstance(data_1);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_2);

        // remove
        chaine.removeInstance(data_3);
        assertResference(chaine);
        assertChaineNext(chaine, data_2);

        // remove
        chaine.removeInstance(data_3);
        assertResference(chaine);
        assertChaineNext(chaine, data_2);

        // remove
        chaine.removeInstance(data_2);
        assertThat(chaine.ins, is(nullValue()));

        //add
        chaine.addInstance(data_1, Simple.order);
        chaine.addInstance(data_2, Simple.order);
        chaine.addInstance(data_3, Simple.order);
        chaine.addInstance(data_4, Simple.order);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_4, data_2);
    }

    @Test
    void test_remove_2() throws Exception {
        Simple data_1 = new Simple(2);
        Simple data_2 = new Simple(3);
        Simple data_3 = new Simple(1);
        Simple data_4 = new Simple(2);

        Chaine<Simple> chaine = new Chaine<Simple>();
        chaine.addInstance(data_1, Simple.order);
        chaine.addInstance(data_2, Simple.order);
        chaine.addInstance(data_3, Simple.order);
        chaine.addInstance(data_4, Simple.order);

        // test
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_4, data_2);

        // remove and add
        chaine.removeInstance(data_1);
        chaine.addInstance(data_1, Simple.order);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_1, data_2);

        // remove and change and add
        chaine.removeInstance(data_1);
        data_1.val = 10;
        chaine.addInstance(data_1, Simple.order);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_2, data_1);
    }

    @Test
    void test_path() throws Exception {
        Simple data_1 = new Simple(2);
        Simple data_2 = new Simple(3);
        Simple data_3 = new Simple(1);
        Simple data_4 = new Simple(2);

        Chaine<Simple> chaine = new Chaine<Simple>();
        chaine.addInstance(data_1, Simple.order);
        chaine.addInstance(data_2, Simple.order);
        chaine.addInstance(data_3, Simple.order);
        chaine.addInstance(data_4, Simple.order);

        // test
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_4, data_2);
        assertChainePrev(chaine, data_3, data_1, data_4, data_2);

        // remove
        chaine.removeInstance(data_1);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_2);
        assertChainePrev(chaine, data_3, data_4, data_2);

        // remove
        chaine.removeInstance(data_2);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4);
        assertChainePrev(chaine, data_3, data_4);

        // remove
        chaine.removeInstance(data_3);
        assertResference(chaine);
        assertChaineNext(chaine, data_4);
        assertChainePrev(chaine, data_4);

        // add
        chaine.addInstance(data_3, Simple.order);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4);
        assertChainePrev(chaine, data_3, data_4);

        // add
        chaine.addInstance(data_2, Simple.order);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_2);
        assertChainePrev(chaine, data_3, data_4, data_2);
    }

    private void assertResference(Chaine<Simple> chaine) {
        Chaine<Simple> test = null;

        test = chaine;
        while (test != null) {
            assertThat(test, is(test.ins.chaine));
            test = test.next;
        }

        test = chaine;
        while (test != null) {
            assertThat(test, is(test.ins.chaine));
            test = test.prev;
        }

    }

    private void assertChaineNext(Chaine<Simple> chaine, Simple... simples) {
        Chaine<Simple> test = chaine;
        for (Simple simple : simples) {
            assertThat(test.ins, is(simple));
            test = test.next;
        }
    }

    private void assertChainePrev(Chaine<Simple> chaine, Simple... simples) {
        Chaine<Simple> test = chaine;
        while (test.next != null) {
            test = test.next;
        }

        List<Simple> reverseList = Arrays.asList(simples);
        Collections.reverse(reverseList);

        for (Simple simple : reverseList) {
            assertThat(test.ins, is(simple));
            test = test.prev;
        }
    }

    static class Simple extends ResferenceElement {
        int val;

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