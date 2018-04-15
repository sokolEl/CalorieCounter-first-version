package view.impl;

import services.FoodService;
import services.impl.FoodServiceImpl;
import view.CmdLineService;
import view.ValidatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//этот сервис принимает комманды из командной строки, и отображает всякие менюхи
public class CmdLineServiceImpl implements CmdLineService{

    private FoodService foodService;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public CmdLineServiceImpl(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    //TODO Модификаторы доступа могут быть везде private кроме метода runMenu()! Исправить.

    //TODO Статические методы обычно идут в конце класса. перенести!
    //пусть каждый пункт будет с новой строки, мне кажется так удобнее читать
    static void menu() {
        System.out.println();
        System.out.println("Добавить продукт нажмите <<1>>  " +
                "Посмотреть данные за день <<2>>  " + "\n" +
                "Удалить продукт из списка нажмите <<3>>  " +
                "Для сохранения данных и выхода из приложения нажмите <<4>>");
        System.out.println();// сделать нормальные отступы, не через sout
    }

    static void menuforAdding() {
        System.out.println();
        System.out.println("Добавить новый продукт в базу нажмите <<1>>  " +
                "Для продолжения введите <<2>>  ");
        System.out.println();
    }

    public void runMenu() {     //метод для считывания команд из меню
        String s;
        try {
            do {
                menu();
                switch (s = br.readLine()) {
                    case "1":
                        addFoodtoList();
                        break;
                    case "2":
                        showEatenFood();
                        break;
                    case "3":
                        deleteFromList();
                        break;
                    case "4":
                        saveAll();
                        s="exit";
                        break;
                }
            }
            while (!(s.equals("exit")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFoodtoList() throws IOException {

        System.out.println("Введите название продукта для того, чтобы добавить его в список калорий за день");
        String product = br.readLine();
        if (foodService.containsFood(product)) {//отслеживаем что продукт есть в базе

            while (true) {
                System.out.println("Введите количество продукта в граммах");
                int weight = ValidatorImpl.checkForNumbers(br.readLine());
                if (weight != 0) {
                    foodService.addCurrentFood(product, weight);
                    System.out.println("Продукт был добавлен в список учета калорий за день");//если введенное количество представлено числами и его можно распарсить
                    break;
                } else {
                    System.out.println("Убедитесь, что вы используете цифры");
                }
            }
        } else {
            System.out.println("Такого продукта нет в базе!");
            addingNewFood();
        }

    }

    public void addingNewFood() throws IOException {
        menuforAdding();
        String decision = br.readLine();
        switch (decision) {

            case "1":
                System.out.println("Введите название продукта, который Вы хотите добавить в базу");
                String newFoodName = br.readLine();
                do {
                    System.out.println("Введите калорийность продукта на 100 грамм");
                    int calories = ValidatorImpl.checkForNumbers(br.readLine());
                    if (calories != 0) {
                        foodService.addFoodtoData(newFoodName, calories);
                        System.out.println("Продукт был добавлен в базу");
                        break;
                    } else {
                        System.out.println("Убедитесь, что вы используете цифры");
                    }
                }
                while (true);
            case "2":
                addFoodtoList();
                break;
            default:
                System.out.println("Необходимо ввести числа 1 или 2");
                addingNewFood();
                break;
        }
    }

    public void showEatenFood()  {

        foodService.showCurrentFoods();

    }

    void deleteFromList() throws IOException {
        System.out.println("Введите название продукта, который вы хотите удалить из списка");
        String foodName = br.readLine();
        if (this.foodService.containsFood(foodName)) {//отслеживаем что продукт есть в базе

            while (true) {
                System.out.println("Введите количество калорий, которые Вы употребили с этим продуктом");
                int calories = ValidatorImpl.checkForNumbers(br.readLine());
//сделать метод который подтверждает что такой продукт с таким количеством калорий есть в списке
                if (calories != 0) {
                    foodService.removeFood(foodName, calories);
                    System.out.println("Продукт был удален из списка учета калорий за день");
                    break;
                } else {
                    System.out.println("Убедитесь, что вы используете цифры");
                }
            }
        }
    else {
            System.out.println("Такого продукта нет в списке");
          deleteFromList();
        }

    }

     void saveAll() {
        foodService.saveFood();
        System.out.println("Все данные были сохранены");
    }



}
