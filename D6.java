public class D6 extends Dice {
    public D6() {
        super(6);
    }
    public String roll(int rolls) {
        String output = "D6:\n";
        output += super.roll(rolls);
        return output;
    }
}