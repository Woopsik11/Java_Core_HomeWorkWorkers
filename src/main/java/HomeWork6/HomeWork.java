package HomeWork6;

public class HomeWork {
    public static void main(String[] args) {

        new Cat("Барсик").run(50);
        new Cat("Федя").run(25);
        new Cat("Тима").swim(0);
        new Dog("Бобик").swim(7);
        new Dog("Шарик").swim(20);
        System.out.println("Создано животных: " + Animal.getQuantity());
        System.out.println("Из них кошек: " + Cat.getQuantity());
        System.out.println("Из них собак: " + Dog.getQuantity());

    }
}
