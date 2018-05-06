package util;


public class ValidatorImpl {


    private ValidatorImpl() {

    }

    static public int checkForNumbers(String line) {
        try {
            int numb = Integer.parseInt(line);
            return numb;
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return 0;
    }
}


