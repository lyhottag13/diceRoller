public class D10 extends Dice {
    public D10() {
        super(10);
    }
    public String roll(int rolls) {
        String output = "D10:\n";
        output += super.roll(rolls);
        return output;
    }
}