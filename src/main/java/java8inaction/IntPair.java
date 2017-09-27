package java8inaction;

public class IntPair {
    private final int one;
    private final int two;

    public IntPair(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }
    public int sum(){
        return one + two;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",one,two);
    }
}
