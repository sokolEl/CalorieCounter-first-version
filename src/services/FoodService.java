package services;


import model.Food;

import java.util.List;

public interface FoodService {


    void addToDiary(String product, int weight);

    List<Food> showDiaryFoods();

    void removeDiaryFood(String name, int calories);

    boolean containsDiaryFood(String name);

    boolean containsProductDBC(String name);

    void addProductDBC(String foodName, int calories);

    void deleteProductDBC(String name);


}