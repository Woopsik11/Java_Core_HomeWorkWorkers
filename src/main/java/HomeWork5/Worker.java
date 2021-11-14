package HomeWork5;

public class Worker {

        private static String name;
        private static String position;
        private static String email;
        private static String callNumber;
        private static int salary;
        private static int age;

        public Worker(String name, String position, String email, String callNumber, int salary, int age) {
            this.name = name;
            this.position = position;
            this.email = email;
            this.callNumber = callNumber;
            this.salary = salary;
            this.age = age;
        }

        public void printWorker() {
            System.out.printf("\nИмя: %s \nДолжность: %s \nПочта: %s \nНомер: %s \nЗарплата: %d \nВозраст: %d",
                    name,
                    position,
                    email,
                    callNumber,
                    salary,
                    age
            );
        }

        public static void main(String[] args) {

            Worker[] workArray = new Worker[5];
            workArray[0] = new Worker("Васильев Петр Генадьевич", "Junior developer", "vas@mail.ru", "89194444444", 20000, 22);
            workArray[1] = new Worker("Александров Александр Александрович", "Middle developer", "cxvsd@mail.ru", "89194445444", 80000, 37);
            workArray[2] = new Worker("Пупкин Пуп Пупович", "Junior developer", "qwefa@mail.ru", "89194442244", 50000, 31);
            workArray[3] = new Worker("Матроскин Котофей Бедросович", "Senior developer", "fwefwf@mail.ru", "89196644444", 150000, 29);
            workArray[4] = new Worker("Маск Илон Генадьевич", "CEO", "askdal@mail.ru", "89194422244", 1000000, 44);
            for (int i = 0; i < workArray.length; i++) {
                if (workArray[i].age < 40) workArray[i].printWorker();

            }
        }
        

    }
