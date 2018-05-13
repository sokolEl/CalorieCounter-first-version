package dao;

import model.Food;

import java.util.List;

/**
 * Created by elinasokol on 05.05.18.
 */
public interface FileSystemDao {

    void writeToDiary(Food Food);

    List<Food> showAllDiary();

    void deleteFromDiary();
}
