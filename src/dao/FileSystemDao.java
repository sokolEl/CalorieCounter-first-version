package dao;

import model.Food;

import java.io.FileNotFoundException;

/**
 * Created by elinasokol on 05.05.18.
 */
public interface FileSystemDao {

    void writeToDiary(Food Food);

    void showAllDiary();

    void deleteFromDiary();
}
