package net.masaki_blog.atcoder.util.reference;

import java.util.function.BiPredicate;

class ResferenceElement {
    Chaine<? extends ResferenceElement> chaine;
}

class Chaine<E extends ResferenceElement> {
    E ins;
    Chaine<E> prev;
    Chaine<E> next;

    void addInstance(E newInstance, BiPredicate<E, E> order) {
        if (this.ins == null) {
            this.ins = newInstance;
            this.ins.chaine = this;
            return;
        }

        if (order.test(this.ins, newInstance)) {
            if (this.next == null) {
                Chaine<E> newNext = new Chaine<E>();
                newNext.ins = newInstance;
                newNext.ins.chaine = newNext;
                this.next = newNext;
            } else {
                this.next.addInstance(newInstance, order);
            }
            this.next.prev = this;
        } else {
            Chaine<E> newNext = new Chaine<E>();
            newNext.ins = this.ins;
            newNext.ins.chaine = newNext;
            newNext.next = this.next;
            this.ins = newInstance;
            this.ins.chaine = this;
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
                this.ins.chaine = this;
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
            }
        }
    }

    void removeInstancePrev(E target) {
        if (this.ins == target) {
            if (this.next == null) {
                this.ins = null;
                if (this.prev != null) {
                    this.prev.next = null;
                }
            } else {
                this.ins = this.next.ins;
                this.ins.chaine = this;
                this.next = this.next.next;
                if (this.prev != null) {
                    this.prev.next = this;
                }
                if (this.next != null) {
                    this.next.prev = this;
                }
            }
        } else {
            if (this.prev != null) {
                this.prev.removeInstancePrev(target);
            }
        }
    }
}
