import java.util.ArrayList;

public class Main {
    public static ArrayList<Dice> dieList;
    public static void main(String[] args) {
        dieList = new ArrayList<>();
        dieList.add(new D4());
        dieList.add(new D6());
        dieList.add(new D8());
        dieList.add(new D10());
        dieList.add(new D10Tens());
        dieList.add(new D12());
        dieList.add(new D20());
        
        new DiceRollerApp();
    }
    public static ArrayList<Dice> getList() {
        return dieList;
    }
}
