package model;

/**
 *
 * @author kmate
 */
class IntPair implements Comparable<IntPair> {

    public int first;
    public int second;

    public IntPair() {
        this(0, 0);
    }

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(IntPair o) throws NullPointerException, ClassCastException {
        if (first > o.first || (first == o.first && second > o.second)) {
            return 1;
        }
        if (first < o.first || (first == o.first && second < o.second)) {
            return -1;
        }
        return 0;
    }
}
