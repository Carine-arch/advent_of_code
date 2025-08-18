package year_18.day_9;

public class MarbleCircleHellService {

    public int part1(int numberOfPlayers, int lastMarbleTotal) {
        test();
        return 0;
    }


    private void test() {
        CircularList<Integer> circularList = new CircularList<>();
        // ajout première bille
        circularList.add(0);
        System.out.println(circularList.getList());

        // ajout seconde à vingt-troisième billes
        for (int i = 1; i <= 23; i++) {
            // on ajoute la bille au current index + 2
            circularList.nextIndex(2);
            circularList.add(circularList.getCurrentIndex(), i);
            System.out.println(circularList.getList());
        }
    }

}
