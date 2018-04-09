package services;

import java.io.IOException;

public interface FoodService {


    void addFromFile();

    void addCurrentFood(String product,int weight) ;

    void showCurrentFoods();

    void removeFood(String name, int k);

    void saveFood();

    boolean containsFood(String nameOfFood);

    void addFoodtoData(String foodName, int calories) throws IOException;
}