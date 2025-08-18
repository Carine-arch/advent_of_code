package year_18.day_9;

import java.util.LinkedList;

public class CircularList<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private int currentIndex = 0;

    public void nextIndex(int steps) {
        if (list.isEmpty()) {
            return;
        }
        // le -1 est du au fait que lors du add on ajoute l'élément à la position + 1
        currentIndex = ((currentIndex + steps - 1) % list.size());
    }

    public T previous() {
        return previous(1);
    }

    public T previous(int steps) {
        if (list.isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex - steps + list.size()) % list.size();
        return list.get(currentIndex);
    }

    public int size() {
        return list.size();
    }

    public void add(int index, T element) {
        list.add(index + 1, element);
        currentIndex = (currentIndex + 1);
    }

    public void add(T element) {
        list.add(element);
        currentIndex = (currentIndex + 1);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public LinkedList<T> getList() {
        return list;
    }

    // double end queue
}
