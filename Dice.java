import java.lang.Math;

public class Dice {

    private int faces;
    private boolean percent;

    public Dice(int faces, boolean percent) {
        this.faces = faces;
        this.percent = percent;
    }
    public Dice(int faces) {
        this.faces = faces;
        percent = false;
    }
    public int getFaces() {
        return faces;
    }
    public void setFaces(int faces) {
        this.faces = faces;
    }
    public boolean isPercent() {
        return percent;
    }
    // Rolls the dice. If the dice is a D10, then it rolls differently since D10s have faces from 0 to 9 and 00 to 90.
    public static String roll(int faces, int rolls, boolean percent) {
        if (rolls == 0) {
            return "";
        }
        String output = "D" + faces + ":\n";
        for (int i = 0; i < rolls; i++) {
            output += "You rolled a: ";
            if (faces == 10) {
                output += ((int) Math.floor(Math.random() * faces));
                if (percent) {
                    output += "0";
                }
                output += "\n";
            } else {
                output += ((int) Math.floor(Math.random() * faces) + 1) + "\n";
            }
        }
        return output;
    }
}