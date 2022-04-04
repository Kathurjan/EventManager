package BLL;

public class TextConverter {
    public double convertStringToDouble(String input) throws BLLException {
        try{
           return Double.parseDouble(input);
        }catch (NumberFormatException ex){
            throw new BLLException("Your double is not working lol", ex);
        }
    }

    public String convertStartTimeToOneString(String string1, String string2) {
        String output = string1 + ":" + string2;
        return output;
    }
}
