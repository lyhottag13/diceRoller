public class D4 extends Dice {
    public D4() {
        super(4);
    }
    public String roll(int rolls) {
        String output = "D4:\n";
        output += super.roll(rolls);
        return output;
    }
}