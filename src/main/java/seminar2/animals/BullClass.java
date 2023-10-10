package seminar2.animals;

public class BullClass implements Bull {
    @Override
    public void walk() {
        System.out.println("Walks on hooves");
    }

    @Override
    public void talk() {
        System.out.println("MoooOOoooOOOoooo");
    }
}
