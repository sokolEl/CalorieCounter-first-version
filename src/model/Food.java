package model;

public class Food {

    //Это класс который представляей данные у него только геттеры и сеттеры, ну иконструктор

    private String foodName;
    private int cal;


    public Food(String foodName, int cal) {
        this.foodName = foodName;
        this.cal = cal;

    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {

        this.foodName = foodName;
    }

    public int getCal() {

        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }


}
