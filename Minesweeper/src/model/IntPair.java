package model;

/**
 *
 * @author kmate
 */
class IntPair implements Comparable<IntPair> {

    public int first;
    public int second;

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
