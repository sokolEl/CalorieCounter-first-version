
import java.io.IOException;

import dao.FileSystemDao;
import dao.impl.DBCFoodDao;
import services.impl.FoodServiceImpl;
import view.CmdLineService;
import view.impl.CmdLineServiceImpl;
import dao.FoodDao;
import dao.impl.FileSystemDaoImpl;

public class FoodCounter {

    public static void main(String[] args) throws IOException {
        FoodDao foodDao = new DBCFoodDao();
        FileSystemDao fileSystemDao=new FileSystemDaoImpl();

        CmdLineService cmd = new CmdLineServiceImpl(new FoodServiceImpl(foodDao,fileSystemDao));
         cmd.runMainMenu();

        }
}