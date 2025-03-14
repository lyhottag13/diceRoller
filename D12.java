public class D12 extends Dice {
    public D12() {
        super(12);
    }
    public String roll(int rolls) {
        String output = "D12:\n";
        output += super.roll(rolls);
        return output;
    }
}