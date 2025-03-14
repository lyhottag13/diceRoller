import java.util.Random;

public abstract class Dice {

    private int faces;
    private Random random = new Random();

    public Dice(int faces) {
        this.faces = faces;
    }

    public String roll(int rolls) {
        String output = "";
        for (int i = 0; i < rolls; i++) {
            output += "You rolled a: " + (random.nextInt(faces) + 1) + "\n";
        }
        return output;
    }
}