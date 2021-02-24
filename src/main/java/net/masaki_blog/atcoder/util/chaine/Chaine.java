package net.masaki_blog.atcoder.util.chaine;

import java.util.function.BiPredicate;

class Chaine<E> {
    E ins;
    Chaine<E> next;

    void addInstance(E newInstance, BiPredicate<E, E> order) {
        if (this.ins == null) {
            this.ins = newInstance;
            return;
        }

        if (order.test(this.ins, newInstance)) {
            if (this.next == null) {
                Chaine<E> newNext = new Chaine<E>();
                newNext.ins = newInstance;
                this.next = newNext;
            } else {
                this.next.addInstance(newInstance, order);
            }
        } else {
            Chaine<E> newNext = new Chaine<E>();
            newNext.ins = this.ins;
            newNext.next = this.next;
            this.next = newNext;
            this.ins = newInstance;
        }
    }

    void removeInstance(E target) {
        if (this.ins == target) {
            if (this.next == null) {
                this.ins = null;
            } else {
                this.ins = this.next.ins;
                this.next = this.next.next;
            }
        } else {
            if (this.next != null) {
                this.next.removeInstance(target);
            }
        }
    }
}
