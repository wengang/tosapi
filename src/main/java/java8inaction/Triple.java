package java8inaction;

public class Triple {
    private final int one;
    private final int two;
    private final double three;

    public Triple(int one, int two, double three) {
        this.one = one;
        this.two = two;
        this.three = three;
        System.out.println(String.format("triple: %s created",toString()));
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }

    public double getThree() {
        return three;
    }
    public boolean isValid(){
        return three % 1 == 0;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%g",one,two,three);
    }
}
