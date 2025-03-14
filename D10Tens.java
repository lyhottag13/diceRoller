public class D10Tens extends Dice {
    public D10Tens() {
        super(10);
    }
    public String roll(int rolls) {
        String output = "D10:\n";
        output += super.roll(rolls);
        return output;
    }
}