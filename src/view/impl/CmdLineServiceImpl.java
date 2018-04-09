package view.impl;
import view.CmdLineService;
import view.Validator;
import services.FoodService;
import services.impl.FoodServiceImpl;
import view.ValidatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//этот сервис принимает комманды из командной строки, и отображает всякие менюхи
public class CmdLineServiceImpl implements CmdLineService{

    private FoodService foodService;
    private Validator validator=new ValidatorImpl();

    private  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public CmdLineServiceImpl(FoodServiceImpl foodService) {
        this.foodService=foodService;

    }

    public void runMenu() {     //метод для считывания команд из меню
        String s;
        try {
            do {
                menu();
                s = br.readLine();
                switch (s) {
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
        boolean chekforFood;
        int weight;
        String  product;

        System.out.println("Введите название продукта для того, чтобы добавить его в список калорий за день");
        product = br.readLine();
        chekforFood = this.foodService.containsFood(product); //отслеживаем что продукт есть в базе

             if (chekforFood == true) {

                while(true){
                     System.out.println("Введите количество продукта в граммах");
                     weight = this.validator.checkForNumbers(br.readLine());

                     if (weight != 0) {
                         this.foodService.addCurrentFood(product, weight);
                         System.out.println("Продукт был добавлен в список учета калорий за день");//если введенное количество представлено числами и его можно распарсить
                         break;
                     }
                     else { System.out.println("Убедитесь, что вы используете цифры");
                     }
                 }
                }

                else {System.out.println("Такого продукта нет в базе!");
                 addingNewFood();

                 }

                }

    public void addingNewFood() throws IOException {
        menuforAdding();
        String desicion = br.readLine();

        switch (desicion) {

            case "1":
                System.out.println("Введите название продукта, который Вы хотите добавить в базу");
                String newFoodName = br.readLine();
              do{
                System.out.println("Введите калорийность продукта на 100 грамм");
                int calories = this.validator.checkForNumbers(br.readLine());
                if (calories!=0){
                this.foodService.addFoodtoData(newFoodName, calories);
                System.out.println("Продукт был добавлен в базу");break;}
                else {
                    System.out.println("Убедитесь, что вы используете цифры");
                }
              }
               while (true);

            case "2":addFoodtoList();break;
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
        String foodName;
        int calories;
        boolean chekforFoodinList;
        System.out.println("Введите название продукта, который вы хотите удалить из списка");
        foodName=br.readLine();
        chekforFoodinList = this.foodService.containsFood(foodName); //отслеживаем что продукт есть в базе
        if (chekforFoodinList==true){

            while (true) {
                System.out.println("Введите количество калорий, которые Вы употребили с этим продуктом");
                calories = this.validator.checkForNumbers(br.readLine());
//сделать метод который пожтвержает что такой продукт с таким количеством калорий есть в списке
                if (calories != 0) {
                    this.foodService.removeFood(foodName, calories);
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

    static void menu() {
        System.out.println();
        System.out.println("Добавить продукт нажмите <<1>>  " +
                "Посмотреть данные за день <<2>>  " +
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



}
