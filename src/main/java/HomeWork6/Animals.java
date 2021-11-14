package HomeWork6;

abstract class Animal {
    private static int quantity = 0;
    protected String name;

    public abstract void swim(int distance);


    public void run(int distance) {
        System.out.println("Животное пробежало расстояние: " + distance);
    }


    public static int getQuantity() {
        return quantity;
    }

    public static void incrementQuantity() {
        quantity++;
    }
}
