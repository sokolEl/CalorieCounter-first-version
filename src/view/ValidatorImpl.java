package view;


public class ValidatorImpl implements Validator {



   public int checkForNumbers(String line) throws NumberFormatException {
       try{ int numb= Integer.parseInt(line);
           return numb;
       }
       catch (NumberFormatException e){
           System.out.println(e);
       }
       return 0;
}
}


