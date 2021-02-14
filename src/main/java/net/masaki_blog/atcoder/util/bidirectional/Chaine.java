package net.masaki_blog.atcoder.util.bidirectional;

import java.util.function.BiPredicate;

class Chaine<E> {
    E ins;
    Chaine<E> prev;
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
            this.next.prev = this;
        } else {
            Chaine<E> newNext = new Chaine<E>();
            newNext.ins = this.ins;
            newNext.next = this.next;
            this.ins = newInstance;
            this.next = newNext;
            this.next.prev = this;
        }
    }

    void removeInstance(E target) {
        if (this.ins == target) {
            if (this.next == null) {
                this.ins = null;
                if (this.prev != null) {
                    this.prev.next = null;
                }
            } else {
                this.ins = this.next.ins;
                this.next = this.next.next;
                if (this.prev != null) {
                    this.prev.next = this;
                }
                if (this.next != null) {
                    this.next.prev = this;
                }
            }
        } else {
            if (this.next != null) {
                this.next.removeInstance(target);
                if (this.next != null) {
                    this.next.prev = this;
                }
            }
        }
    }
}
