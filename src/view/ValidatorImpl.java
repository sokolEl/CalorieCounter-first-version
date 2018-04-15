package view;


public class ValidatorImpl {


    //TODO создай отдельный пакедж util. И все Util классы путь будут там.

    private ValidatorImpl() {
    }

    //Все хорошо, можно упростить:
    //            int numb = Integer.parseInt(line);
    //            return numb;
    //    равносильно:
    //            return Integer.parseInt(line)



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


