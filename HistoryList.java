import java.util.LinkedList;

public class HistoryList extends LinkedList<String> {
    RollHistory<String> head;
    RollHistory<String> tail;
    RollHistory<String> selector;
    int indexTracker;
    public HistoryList() {
        indexTracker = 1;
    }
    public void addNewHistory(String rollResult) {
        RollHistory<String> newRollHistory = new RollHistory<>(rollResult);
        if (head == null) {
            head = newRollHistory;
            tail = head;
        } else {
            newRollHistory.last = tail;
            tail.next = newRollHistory;
            tail = newRollHistory;
        }
        selector = newRollHistory;
    }
    public void selectNext() {
        if (selector.next != null)
            selector = selector.next;
    }
    public void selectLast() {
        if (selector.last != null)
            selector = selector.last;
    }
    public String getSelector() {
        return "ROLL " + selector.getIndex() + ":\n" + selector.getResult();
    }


    private class RollHistory<E> {
        RollHistory<String> last;
        RollHistory<String> next;
        String rollResult;
        int index;
        private RollHistory(String rollResult) {
            this.rollResult = rollResult;
            index = indexTracker++;
        }
        private String getResult() {
            return rollResult;
        }
        private int getIndex() {
            return index;
        }
    }
}
