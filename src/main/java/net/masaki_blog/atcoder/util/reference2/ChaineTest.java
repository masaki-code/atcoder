package net.masaki_blog.atcoder.util.reference2;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Chaine chaine = new Chaine();
        chaine.addInstance(new ResferenceElement(2));
        chaine.addInstance(new ResferenceElement(1));
        chaine.addInstance(new ResferenceElement(3));
        chaine.addInstance(new ResferenceElement(2));

        Chaine test = chaine;
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
        ResferenceElement data_1 = new ResferenceElement(2);
        ResferenceElement data_2 = new ResferenceElement(3);
        ResferenceElement data_3 = new ResferenceElement(1);
        ResferenceElement data_4 = new ResferenceElement(2);

        Chaine chaine = new Chaine();
        chaine.addInstance(data_1);
        chaine.addInstance(data_2);
        chaine.addInstance(data_3);

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
        chaine.addInstance(data_1);
        chaine.addInstance(data_2);
        chaine.addInstance(data_3);
        chaine.addInstance(data_4);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_4, data_2);
    }

    @Test
    void test_remove_2() throws Exception {
        ResferenceElement data_1 = new ResferenceElement(2);
        ResferenceElement data_2 = new ResferenceElement(3);
        ResferenceElement data_3 = new ResferenceElement(1);
        ResferenceElement data_4 = new ResferenceElement(2);

        Chaine chaine = new Chaine();
        chaine.addInstance(data_1);
        chaine.addInstance(data_2);
        chaine.addInstance(data_3);
        chaine.addInstance(data_4);

        // test
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_1, data_4, data_2);

        // remove and add
        chaine.removeInstance(data_1);
        chaine.addInstance(data_1);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_1, data_2);

        // remove and change and add
        chaine.removeInstance(data_1);
        data_1.val = 10;
        chaine.addInstance(data_1);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_2, data_1);
    }

    @Test
    void test_path() throws Exception {
        ResferenceElement data_1 = new ResferenceElement(2);
        ResferenceElement data_2 = new ResferenceElement(3);
        ResferenceElement data_3 = new ResferenceElement(1);
        ResferenceElement data_4 = new ResferenceElement(2);

        Chaine chaine = new Chaine();
        chaine.addInstance(data_1);
        chaine.addInstance(data_2);
        chaine.addInstance(data_3);
        chaine.addInstance(data_4);

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
        chaine.addInstance(data_3);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4);
        assertChainePrev(chaine, data_3, data_4);

        // add
        chaine.addInstance(data_2);
        assertResference(chaine);
        assertChaineNext(chaine, data_3, data_4, data_2);
        assertChainePrev(chaine, data_3, data_4, data_2);
    }

    @Test
    void test_remove_prev() throws Exception {
        ResferenceElement data_1 = new ResferenceElement(2);
        ResferenceElement data_2 = new ResferenceElement(3);
        ResferenceElement data_3 = new ResferenceElement(1);
        ResferenceElement data_4 = new ResferenceElement(2);
        ResferenceElement data_e = new ResferenceElement(99);

        Chaine chaineF = new Chaine();
        chaineF.addInstance(data_1);
        chaineF.addInstance(data_2);
        chaineF.addInstance(data_3);
        chaineF.addInstance(data_e);

        Chaine chaineE = (Chaine) data_e.chaine;

        // test
        assertResference(chaineF);
        assertChaineNext(chaineF, data_3, data_1, data_2, data_e);
        assertChainePrev(chaineE, data_3, data_1, data_2, data_e);

        // remove
        chaineE.removeInstancePrev(data_1);
        assertResference(chaineF);
        assertChaineNext(chaineF, data_3, data_2, data_e);

        // remove
        chaineF.removeInstancePrev(data_3);
        assertResference(chaineF);
        assertChaineNext(chaineF, data_2, data_e);

        // remove
        chaineF.removeInstancePrev(data_3);
        assertResference(chaineF);
        assertChaineNext(chaineF, data_2, data_e);

        // remove
        chaineF.removeInstancePrev(data_2);
        assertResference(chaineF);
        assertChaineNext(chaineF, data_e);

        //add
        chaineF.addInstance(data_1);
        chaineF.addInstance(data_2);
        chaineF.addInstance(data_3);
        chaineF.addInstance(data_4);
        assertResference(chaineF);
        assertChaineNext(chaineF, data_3, data_1, data_4, data_2, data_e);
    }

    @Test
    void test_reset() throws Exception {
        ResferenceElement data_1 = new ResferenceElement(10);
        ResferenceElement data_2 = new ResferenceElement(20);
        ResferenceElement data_3 = new ResferenceElement(30);
        ResferenceElement data_4 = new ResferenceElement(40);
        ResferenceElement data_e = new ResferenceElement(99);

        Chaine chaineF = new Chaine();
        chaineF.addInstance(data_2);
        chaineF.addInstance(data_1);
        chaineF.addInstance(data_4);
        chaineF.addInstance(data_e);
        chaineF.addInstance(data_3);

        // test
        assertResference(chaineF);
        assertChaineNext(chaineF, data_1, data_2, data_3, data_4, data_e);
        // data_1: 10, data_2: 20, data_3: 30, data_4: 40, data_e: 99

        // prev
        data_3.val = 15;
        data_3.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_1, data_3, data_2, data_4, data_e);
        // data_1: 10, data_3: 15, data_2: 20, data_4: 40, data_e: 99

        // next
        data_2.val = 45;
        data_2.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_1, data_3, data_4, data_2, data_e);
        // data_1: 10, data_3: 15, data_4: 40, data_2: 45, data_e: 99

        // prev to top
        data_4.val = 5;
        data_4.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_4, data_1, data_3, data_2, data_e);
        // data_4: 5, data_1: 10, data_3: 15, data_2: 45, data_e: 99

        // next to bottom
        data_3.val = 100;
        data_3.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_4, data_1, data_2, data_e, data_3);
        // data_4: 5, data_1: 10, data_2: 45, data_e: 99, data_3: 100

        // prev top to top
        data_4.val = 1;
        data_4.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_4, data_1, data_2, data_e, data_3);
        // data_4: 1, data_1: 10, data_2: 45, data_e: 99, data_3: 100

        // next bottom to bottom
        data_3.val = 105;
        data_3.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_4, data_1, data_2, data_e, data_3);
        // data_4: 1, data_1: 10, data_2: 45, data_e: 99, data_3: 105

        // no move
        data_2.val = 43;
        data_2.chaine.reset();
        assertResference(chaineF);
        assertChaineNext(chaineF, data_4, data_1, data_2, data_e, data_3);
        // data_4: 1, data_1: 10, data_2: 43, data_e: 99, data_3: 105

    }

    private void assertResference(Chaine chaine) {
        Chaine test = null;

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

    private void assertChaineNext(Chaine chaine, ResferenceElement... simples) {
        Chaine test = chaine;
        for (ResferenceElement simple : simples) {
            assertThat(test.ins.val, is(simple.val));
            assertThat(test.ins, is(simple));
            test = test.next;
        }
    }

    private void assertChainePrev(Chaine chaine, ResferenceElement... simples) {
        Chaine test = chaine;
        while (test.next != null) {
            test = test.next;
        }

        List<ResferenceElement> reverseList = Arrays.asList(simples);
        Collections.reverse(reverseList);

        for (ResferenceElement simple : reverseList) {
            assertThat(test.ins, is(simple));
            test = test.prev;
        }
    }

}