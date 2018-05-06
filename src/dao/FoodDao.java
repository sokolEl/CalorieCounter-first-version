package dao;

import model.Food;

public interface FoodDao {

    int getCalories(String name);

    void addFoodDBC(Food newProduct);

    void deleteFoodDBC(String name);

    boolean isProductDBC(String name);

}
