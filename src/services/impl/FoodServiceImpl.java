package services.impl;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.FileSystemDao;
import dao.FoodDao;
import model.Food;
import services.FoodService;

import java.util.HashMap;


public class FoodServiceImpl implements FoodService {
    private String fileName = "/Users/elinasokol/Desktop/CalorieCounter-master/test.txt";

    private final FoodDao foodDao;
    private final FileSystemDao fileSystemDao;

    public FoodServiceImpl(FoodDao foodDao, FileSystemDao fileSystemDao) {
        this.foodDao = foodDao;
        this.fileSystemDao = fileSystemDao;
    }


    public void addToDiary(String product, int weight) { //вопрос к базе
        String name = product;
        int currentCalories=foodDao.getCalories(name);
        int currentCal = (weight * currentCalories) / 100;
        Food addCurrentFood = new Food(product, currentCal);
        fileSystemDao.writeToDiary(addCurrentFood);

    }

    public void showDiaryFoods() {
        fileSystemDao.showAllDiary();
    }

    public void removeDiaryFood(String name, int calories) {
        List<Food> currentFoods = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] mas = lines.split(" ");
                String foodName = mas[0];
                int cal = Integer.parseInt(mas[1]);
                Food food = new Food(foodName, cal);
                currentFoods.add(food);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < currentFoods.size(); i++) {
            Food removeFood = currentFoods.get(i);
            if (removeFood.getFoodName().equals(name) && removeFood.getCal() == calories) {
                currentFoods.remove(i);
            }
        }

        fileSystemDao.deleteFromDiary();

        for (int i = 0; i < currentFoods.size(); i++) {
            Food rewrite = currentFoods.get(i);
            fileSystemDao.writeToDiary(rewrite);
        }
    }


    public void addProductDBC(String foodName, int calories) {
        Food newProduct = new Food(foodName, calories);
        foodDao.addFoodDBC(newProduct);
    }

    public void deleteProductDBC(String name) {
        foodDao.deleteFoodDBC(name);
    }

    public boolean containsDiaryFood(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] mas = lines.split(" ");
                String foodName = mas[0];
                if (foodName.equals(name)) return true;
            }
        } catch (IOException ex) {
        }
        return false;
    }

    public boolean containsProductDBC(String name) {

        return foodDao.isProductDBC(name);
    }

}
