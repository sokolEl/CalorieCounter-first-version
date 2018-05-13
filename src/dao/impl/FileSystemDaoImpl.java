package dao.impl;

import dao.FileSystemDao;
import model.Food;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileSystemDaoImpl implements FileSystemDao {

    private List<Food> list = new ArrayList<>();
    private static final String FILE_NAME = "test.txt";

    public FileSystemDaoImpl() {
    }

    public void writeToDiary(Food food) {  //TODO: сделать так чтобы создавался новый файл каждый день

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt", true))) {
            String text = food.getFoodName() + " " + Integer.toString(food.getCal());
            bw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<Food> showAllDiary() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String lines;
            while ((lines = br.readLine()) != null) {
                String[] mas = lines.split(" ");
                String foodName = mas[0];
                int cal = Integer.parseInt(mas[1]);
                Food a = new Food(foodName, cal);
                list.add(a);

                System.out.println(lines);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void deleteFromDiary() {
        try (FileWriter writer = new FileWriter("test.txt", false)) {
            String text = "";
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}


