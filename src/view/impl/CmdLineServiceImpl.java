package view.impl;

import services.FoodService;
import services.impl.FoodServiceImpl;
import util.ValidatorImpl;
import view.CmdLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CmdLineServiceImpl implements CmdLineService {

    private FoodService foodService;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//в конструктор

    public CmdLineServiceImpl(FoodServiceImpl foodService) {
        this.foodService = foodService;

    }


    public void runMainMenu() {
        String s;
        try {
            do {
                showMainMenu();
                s = br.readLine();
                switch (s) {
                    case "1":
                        runJDBCMenu();
                        break;
                    case "2":
                        runDiaryMenu();
                        break;
                    case "3":
                        s = "exit";
                        break;
                }
            }
            while (!(s.equals("exit")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runJDBCMenu() {
        String s;
        try {
            do {
                dbcMenu();
                s = br.readLine();
                switch (s) {
                    case "1":
                        addProductJDBC();
                        break;
                    case "2":
                        deleteProductJDBC();
                        break;
                    case "3":
                        s = "exit";
                        break;
                }
            }
            while (!(s.equals("exit")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void runDiaryMenu() {
        String s;
        try {
            do {
                diaryMenu();
                s = br.readLine();
                switch (s) {
                    case "1":
                        addFoodDiary();
                        break;
                    case "2":
                        showEatenFood();
                        break;
                    case "3":
                        deleteFromDiary();
                        break;
                    case "4":
                        s = "exit";
                        break;
                }
            }
            while (!(s.equals("exit")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addFoodDiary() throws IOException {

        System.out.println("Введите название продукта для того, чтобы добавить его в список калорий за день");
        String product = br.readLine();
        if (foodService.containsProductDBC(product)) {

            while (true) {
                System.out.println("Введите количество продукта в граммах");
                int weight = ValidatorImpl.checkForNumbers(br.readLine());
                if (weight != 0) {
                    foodService.addToDiary(product, weight);
                    System.out.println("Продукт был добавлен в список учета калорий за день");//если введенное количество представлено числами и его можно распарсить
                    break;
                } else {
                    System.out.println("Убедитесь, что вы используете цифры");
                }
            }
        } else {
            System.out.println("Такого продукта нет в базе!");
            System.out.println("Добавить новый продукт в базу нажмите <<1>>");
            System.out.println("Для выхода в меню ежедневника нажмите <<2>>");
            String decision = br.readLine();
            switch (decision) {
                case "1":
                   addProductJDBC();
                   break;
                case "2":
                    runDiaryMenu();
                    break;
            }
        }

    }

    private void addProductJDBC() throws IOException {
        System.out.println("Введите название продукта, который Вы хотите добавить в базу");
        String productName = br.readLine();
        do {
            System.out.println("Введите калорийность продукта на 100 грамм");
            int calories = ValidatorImpl.checkForNumbers(br.readLine());
            if (calories != 0) {
                foodService.addProductDBC(productName, calories);
                System.out.println("Продукт был добавлен в базу");
                break;
            } else {
                System.out.println("Убедитесь, что вы используете цифры");
            }
        }
        while (true);
    }


    private void showEatenFood() {

        foodService.showDiaryFoods();
    }

    private void deleteFromDiary() throws IOException {
        System.out.println("Введите название продукта, который вы хотите удалить из списка");
        String foodName = br.readLine();
        if (this.foodService.containsDiaryFood(foodName)) {

        while (true) {
            System.out.println("Введите количество калорий, которые Вы употребили с этим продуктом");
            int calories = ValidatorImpl.checkForNumbers(br.readLine());
            if (calories != 0) {
                foodService.removeDiaryFood(foodName, calories);
                System.out.println("Продукт был удален из списка учета калорий за день");
                break;
            } else {
                System.out.println("Убедитесь, что вы используете цифры");
            }
        }
    }
      else {
            System.out.println("Такого продукта нет в ежедневнике");
      }
    }

    private void deleteProductJDBC() throws IOException {
        System.out.println("Ввведите имя продукта для удаления");
        String name = br.readLine();
        foodService.deleteProductDBC(name);
        System.out.println("Продукт был удален");
    }


    private static void showMainMenu() {
        System.out.println();
        System.out.println("Для работы с базой калорийности продуктов нажмите <<1>>");
        System.out.println("Для работы с ежедневником калорий нажмите <<2>> ");
        System.out.println("Для выхода нажмите <<3>> ");
        System.out.println();
    }

    private void diaryMenu() {
        System.out.println();
        System.out.println("Добавить продукт в ежедневник нажмите <<1>>");
        System.out.println("Посмотреть данные за день <<2>>  ");
        System.out.println("Удалить продукт из списка нажмите <<3>>");
        System.out.println("Для выхода в главное меню нажмите <<4>>");
        System.out.println();
    }

    private void dbcMenu() {
        System.out.println();
        System.out.println("Добавить продукт в базу калорийности <<1>>");
        System.out.println("Удалить продукт из базы калорийности <<2>> ");
        System.out.println("Для выхода в главное меню нажмите <<3>> ");
        System.out.println();
    }

}
