package net.masaki_blog.atcoder.util.reference2;

import java.util.function.BiPredicate;

class ResferenceElement {
    Chaine chaine;

    int val;

    ResferenceElement(int val) {
        this.val = val;
    }

}

class Chaine {
    ResferenceElement ins;
    Chaine prev;
    Chaine next;

    static BiPredicate<ResferenceElement, ResferenceElement> order = new BiPredicate<ResferenceElement, ResferenceElement>() {

        @Override
        public boolean test(ResferenceElement t, ResferenceElement u) {
            return t.val <= u.val;
        }
    };

    void addInstance(ResferenceElement newInstance) {
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
                this.next.addInstance(newInstance);
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

    void addInstancePrev(ResferenceElement newInstance) {
        if (this.ins == null) {
            this.ins = newInstance;
            this.ins.chaine = this;
            return;
        }

        if (order.test(newInstance, this.ins)) {
            if (this.prev == null) {
                Chaine newNext = new Chaine();
                newNext.ins = this.ins;
                newNext.ins.chaine = newNext;
                newNext.next = this.next;

                this.ins = newInstance;
                this.ins.chaine = this;
                this.next = newNext;
                this.next.prev = this;
            } else {
                this.prev.addInstancePrev(newInstance);
            }
        } else {
            Chaine newNext = new Chaine();
            newNext.ins = newInstance;
            newNext.ins.chaine = newNext;
            newNext.next = this.next;
            this.next = newNext;
            this.next.prev = this;
        }
    }

    void reset() {
        ResferenceElement beforeIns = this.ins;
        if (this.prev != null && order.test(this.ins, this.prev.ins)) {
            this.removeSelf();
            this.addInstancePrev(beforeIns);
        }

        if (this.next != null && order.test(this.next.ins, this.ins)) {
            this.addInstance(beforeIns);
            this.removeSelf();
        }
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
            if (this.next != null) {
                this.next.prev = this;
            }
        }
    }
}
