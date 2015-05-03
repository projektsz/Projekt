package model;

/**
 * Classic pair class like C++ style std::pair&lt;int, int&gt;. It has public first,
 * second field compareTo method (implements Comparable).
 *
 * @author kmate
 */
public class IntPair implements Comparable<IntPair> {

    public int first;
    public int second;

    public IntPair() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        return first == ((IntPair) o).first && second == ((IntPair) o).second;
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

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public int hashCode() {
        return first + second;
    }
}
