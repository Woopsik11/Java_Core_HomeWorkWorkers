package HomeWork7;

public class HomeWork7 {
    public static void main(String[] args) {
        Cat[] cats = {
                new Cat("Пупсик", 20),
                new Cat("Тима", 30),
                new Cat("Кошара", 40),
                new Cat("Малыш", 70),
                new Cat("Помойный", 40)
        };
        Plate plate = new Plate(150,100);

        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].eat(plate)){
                plate.addFood(50);
                i--;
            }
        }
        cats[2].eat(plate);
        plate.addFood(200);
    }
}
