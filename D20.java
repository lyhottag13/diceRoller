public class D20 extends Dice {
    public D20() {
        super(20);
    }
    public String roll(int rolls) {
        String output = "D20:\n";
        output += super.roll(rolls);
        return output;
    }
}