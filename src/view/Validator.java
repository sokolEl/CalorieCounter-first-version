package view;

import java.io.IOException;

/**
 * Created by elinasokol on 07.04.18.
 */
public interface Validator {

    int checkForNumbers(String line) throws IOException;
}
