package services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Food;
import services.FoodService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class FoodServiceImpl implements FoodService {
    private String fileName = "/Users/elinasokol/Desktop/java/CalorieCounter-master/food.txt";
    private Map<String, Food> map = new HashMap<>(); //массив с данными
    private List<Food> currentFoods = new ArrayList<>();//массив для записи данных о продуктах и калориях за день

    public FoodServiceImpl() {

        addFromFile();
    }


    public void addFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] mas = lines.split(";");
                String foodName = mas[0];
                int cal = Integer.parseInt(mas[1]);
                Food food = new Food(foodName, cal);
                map.put(foodName, food);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addCurrentFood(String product, int weight) {

        Food getToKnowAboutCurrentFood = map.get(product);
        int currentCal = (weight * getToKnowAboutCurrentFood.getCal()) / 100;
        Food addCurrentFood = new Food(product, currentCal);
        currentFoods.add(addCurrentFood);
    }

    public void showCurrentFoods() {

        for (int i = 0; i < currentFoods.size(); i++) {
            Food f = currentFoods.get(i);
            System.out.println(f.getFoodName() + " " + f.getCal() + "ккал");
        }
    }

    public void removeFood(String name, int k) {

        for (int i = 0; i < currentFoods.size(); i++) {
            Food removeFood = currentFoods.get(i);
            if (removeFood.getFoodName().equals(name) && removeFood.getCal() == k) {
                currentFoods.remove(i);
            }
        }
    }

    public void saveFood() {  //TODO: сделать так чтобы создавался новый файл каждый день

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/elinasokol/Desktop/java/CalorieCounter-master/test.txt"))) {
            String text;
            for (int i = 0; i < currentFoods.size(); i++) {
                Food saveFood = currentFoods.get(i);
                text = saveFood.getFoodName() + " " + Integer.toString(saveFood.getCal());
                bw.write(text+"\n");
            }
        }
        catch(IOException ex){
              System.out.println(ex.getMessage());
        }
    }
    public void addFoodtoData(String foodName, int calories) throws IOException {
        Food newFoodinData = new Food(foodName, calories);
        map.put(foodName, newFoodinData);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/elinasokol/Desktop/java/CalorieCounter-master/test.txt"))) {
            String text;
                text = newFoodinData.getFoodName() + " " + Integer.toString(newFoodinData.getCal());
                bw.write(text+"\n");
            }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsFood(String FoodName) {
        return map.containsKey(FoodName);
    }

    public boolean containsFoodinList(String FoodName) {
        return map.containsKey(FoodName);
    }
    }
