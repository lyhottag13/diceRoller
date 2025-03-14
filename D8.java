public class D8 extends Dice {
    public D8() {
        super(8);
    }
    public String roll(int rolls) {
        String output = "D8:\n";
        output += super.roll(rolls);
        return output;
    }
}