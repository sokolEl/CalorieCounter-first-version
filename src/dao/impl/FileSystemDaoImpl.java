package dao.impl;

import dao.FileSystemDao;
import dao.FoodDao;
import model.Food;

import java.io.*;


public class FileSystemDaoImpl implements FileSystemDao {


    public FileSystemDaoImpl() {
    }

    public void writeToDiary(Food food) {  //TODO: сделать так чтобы создавался новый файл каждый день

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/elinasokol/Desktop/CalorieCounter-master/test.txt", true))) {
            String text = food.getFoodName() + " " + Integer.toString(food.getCal());
            bw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void showAllDiary() {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/elinasokol/Desktop/CalorieCounter-master/test.txt"))) {
            String lines;
            while ((lines = br.readLine()) != null) {
                System.out.println(lines);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteFromDiary() {
        try (FileWriter writer = new FileWriter("/Users/elinasokol/Desktop/CalorieCounter-master/test.txt", false)) {
            String text = "";
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}


