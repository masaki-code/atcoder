package net.masaki_blog.atcoder.util.integer;

public class PositiveNumber {

    private static final int FIX_LEN = 11;

    private final int[] mag;

    private int intLen;

    PositiveNumber(long val) {
        mag = new int[FIX_LEN];

        int highWord = (int) (val >>> 32);
        if (highWord == 0) {
            mag[0] = (int) val;
            intLen = 1;
        } else {
            mag[0] = highWord;
            mag[1] = (int) val;
            intLen = 2;
        }
    }

    PositiveNumber multiply(int i) {
        new MultiplyByInt(this).multiplyByInt(i);
        return this;
    }

    PositiveNumber divide(int i) {
        int intLen = this.mag.length;// TODO
        new DivideOneWord(this.mag, intLen).divideOneWord(i).value();
        return this;
    }

    int remainderValue(int i) {
        int intLen = this.mag.length;// TODO
        return new DivideOneWord(this.mag, intLen).divideOneWord(i).r();
    }

    private static class MultiplyByInt {
        final static long LONG_MASK = 0xffffffffL;

        PositiveNumber myInt;

        MultiplyByInt(PositiveNumber myInt) {
            this.myInt = myInt;
        }

        void multiplyByInt(int y) {
            if (Integer.bitCount(y) == 1) {
                shiftLeft(Integer.numberOfTrailingZeros(y));
            }

            int xlen = myInt.intLen;
            int[] rmag = new int[xlen + 1];
            long carry = 0;
            long yl = y & LONG_MASK;
            int rstart = rmag.length - 1;
            for (int i = xlen - 1; i >= 0; i--) {
                long product = (myInt.mag[i] & LONG_MASK) * yl + carry;
                rmag[rstart--] = (int) product;
                carry = product >>> 32;
            }
            if (carry == 0L) {
                System.arraycopy(rmag, 1, myInt.mag, 0, rmag.length - 1);
            } else {
                myInt.mag[rstart] = (int) carry;
                myInt.intLen++;
            }

        }

        private int[] shiftLeft(int n) {
            int[] mag = myInt.mag;
            int nInts = n >>> 5;
            int nBits = n & 0x1f;
            int magLen = myInt.intLen;
            int newMag[] = null;

            if (nBits == 0) {
                myInt.intLen = magLen + nInts;
            } else {
                int i = 0;
                int nBits2 = 32 - nBits;
                int highBits = mag[0] >>> nBits2;
                if (highBits != 0) {
                    newMag = new int[magLen + nInts + 1];
                    newMag[i++] = highBits;
                } else {
                    newMag = new int[magLen + nInts];
                }
                int j = 0;
                while (j < magLen - 1)
                    newMag[i++] = mag[j++] << nBits | mag[j] >>> nBits2;
                newMag[i] = mag[j] << nBits;
            }
            return newMag;
        }

    }

    private static class DivideOneWord {
        final static long LONG_MASK = 0xffffffffL;

        private int[] value;

        private int r;

        private int intLen;

        private int offset;

        DivideOneWord(int[] value, int intLen) {
            this.value = value;
            this.intLen = intLen;
        }

        int r() {
            return this.r;
        }

        int[] value() {
            return this.value;
        }

        DivideOneWord divideOneWord(int divisor) {
            if (intLen == 0) {
                this.r = divideOneWord1(divisor);
            } else {
                this.r = divideOneWordN(divisor);
            }
            return this;
        }

        private int divideOneWord1(int divisor) {
            long divisorLong = divisor & LONG_MASK;
            long dividendValue = value[offset] & LONG_MASK;
            int q = (int) (dividendValue / divisorLong);
            int r = (int) (dividendValue - q * divisorLong);
            value[0] = q;
            intLen = (q == 0) ? 0 : 1;
            offset = 0;
            return r;
        }

        private int divideOneWordN(int divisor) {
            long divisorLong = divisor & LONG_MASK;

            int rem = value[0];
            long remLong = rem & LONG_MASK;
            if (remLong < divisorLong) {
                value[0] = 0;
            } else {
                value[0] = (int) (remLong / divisorLong);
                rem = (int) (remLong - (value[0] * divisorLong));
                remLong = rem & LONG_MASK;
            }
            int xlen = intLen;
            while (--xlen > 0) {
                long dividendEstimate = (remLong << 32) |
                        (value[intLen - xlen] & LONG_MASK);
                int q;
                if (dividendEstimate >= 0) {
                    q = (int) (dividendEstimate / divisorLong);
                    rem = (int) (dividendEstimate - q * divisorLong);
                } else {
                    long tmp = divWord(dividendEstimate, divisor);
                    q = (int) (tmp & LONG_MASK);
                    rem = (int) (tmp >>> 32);
                }
                value[intLen - xlen] = q;
                remLong = rem & LONG_MASK;
            }

            int shift = Integer.numberOfLeadingZeros(divisor);
            if (shift > 0) {
                return rem % divisor;
            } else {
                return rem;
            }
        }

        private static long divWord(long n, int d) {
            long dLong = d & LONG_MASK;
            long r;
            long q;
            if (dLong == 1) {
                q = (int) n;
                r = 0;
                return (r << 32) | (q & LONG_MASK);
            }
            q = (n >>> 1) / (dLong >>> 1);
            r = n - q * dLong;
            while (r < 0) {
                r += dLong;
                q--;
            }
            while (r >= dLong) {
                r -= dLong;
                q++;
            }
            return (r << 32) | (q & LONG_MASK);
        }

    }

}
