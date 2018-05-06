package services;


public interface FoodService {


    void addToDiary(String product, int weight);

    void showDiaryFoods();

    void removeDiaryFood(String name, int calories);

    boolean containsDiaryFood(String name);

    boolean containsProductDBC(String name);

    void addProductDBC(String foodName, int calories);

    void deleteProductDBC(String name);


}