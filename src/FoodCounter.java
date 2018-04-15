import services.impl.FoodServiceImpl;
import view.CmdLineService;
import view.impl.CmdLineServiceImpl;

import java.io.IOException;

public class FoodCounter {

    public static void main(String[] args) throws IOException {
        CmdLineService cmd = new CmdLineServiceImpl(new FoodServiceImpl());
        cmd.runMenu();

    }
}