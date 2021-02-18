package net.masaki_blog.atcoder.util.reference;

import java.util.function.BiPredicate;

class ResferenceElement {
    Chaine chaine;

    int val;

    ResferenceElement(int val) {
        this.val = val;
    }

    static BiPredicate<ResferenceElement, ResferenceElement> order = new BiPredicate<ResferenceElement, ResferenceElement>() {

        @Override
        public boolean test(ResferenceElement t, ResferenceElement u) {
            return t.val <= u.val;
        }
    };

}

class Chaine {
    ResferenceElement ins;
    Chaine prev;
    Chaine next;

    void addInstance(ResferenceElement newInstance, BiPredicate<ResferenceElement, ResferenceElement> order) {
        if (this.ins == null) {
            this.ins = newInstance;
            this.ins.chaine = this;
            return;
        }

        if (order.test(this.ins, newInstance)) {
            if (this.next == null) {
                Chaine newNext = new Chaine();
                newNext.ins = newInstance;
                newNext.ins.chaine = newNext;
                this.next = newNext;
            } else {
                this.next.addInstance(newInstance, order);
            }
            this.next.prev = this;
        } else {
            Chaine newNext = new Chaine();
            newNext.ins = this.ins;
            newNext.ins.chaine = newNext;
            newNext.next = this.next;
            this.ins = newInstance;
            this.ins.chaine = this;
            this.next = newNext;
            this.next.prev = this;
        }
    }

    void removeBidirectional(ResferenceElement target,
            BiPredicate<ResferenceElement, ResferenceElement> order) {

        if (this.prev != null && order.test(target, this.prev.ins)) {
            this.removeInstancePrev(this.ins);
        }

        this.removeInstance(this.ins);

    }

    void removeInstance(ResferenceElement target) {
        if (this.ins == target) {
            this.removeSelf();
        } else {
            if (this.next != null) {
                this.next.removeInstance(target);
            }
        }
    }

    void removeInstancePrev(ResferenceElement target) {
        if (this.ins == target) {
            this.removeSelf();
        } else {
            if (this.prev != null) {
                this.prev.removeInstancePrev(target);
            }
        }
    }

    private void removeSelf() {
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
    }
}
