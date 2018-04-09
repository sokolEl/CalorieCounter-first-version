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

    String fileName = "ссылка на базу/food.txt";
    Map<String, Food> map = new HashMap<>(); //массив с данными
    List<Food> currentFoods = new ArrayList<>();//массив для записи данных о продуктах и калориях за день

    public FoodServiceImpl() {

        addFromFile();
    }


    public void addFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] mas = new String[2];
                mas = s.split(";");
                String foodName = mas[0];
                int cal = Integer.parseInt(mas[1]);
                Food a = new Food(foodName, cal);
                map.put(foodName, a);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addCurrentFood(String product, int weight) {

        Food b = map.get(product);
        int currentCal = (weight * b.getCal()) / 100;
        Food a = new Food(product, currentCal);
        currentFoods.add(a);
    }

    public void showCurrentFoods() {

        for (int i = 0; i < currentFoods.size(); i++) {
            Food f = currentFoods.get(i);
            System.out.println(f.getFoodName() + " " + f.getCal() + "ккал");
        }
    }

    public void removeFood(String name, int k) {

        for (int i = 0; i < currentFoods.size(); i++) {
            Food z = currentFoods.get(i);
            if (z.getFoodName().equals(name) && z.getCal() == k) {
                currentFoods.remove(i);
            }
        }
    }
    public void saveFood() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"))) {
            String text;
            for (int i = 0; i < currentFoods.size(); i++) {
                Food f = currentFoods.get(i);
                text = f.getFoodName() + " " + Integer.toString(f.getCal());
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ссылка куда сохранять/test.txt"))) {
            String text;
                text = newFoodinData.getFoodName() + " " + Integer.toString(newFoodinData.getCal());
                bw.write(text+"\n");
            }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsFood(String nameOfFood){
        boolean cont= map.containsKey(nameOfFood);
        if( cont ==true) return true;
        else return false;
    }

    public boolean containsFoodinList(String nameOfFood){
        boolean cont= map.containsKey(nameOfFood);
        if( cont ==true) return true;
        else return false;
    }
    }
