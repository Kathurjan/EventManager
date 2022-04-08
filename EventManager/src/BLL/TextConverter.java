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

    public String[] convertStartTimeToTwoString(String string){
        String[] arr = new String[2];
        arr[0] = string.substring(0,2);
        arr[1] = string.substring(3,5);
        return arr;
    }

    public String convertDoubleToString(Double doub){
        return String.valueOf(doub);
    }
}
