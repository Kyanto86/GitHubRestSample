package pbrtests.restsampleapp.util;

public class DateFormatter {

    //static is important!!
    public static String getFormattedDate (String date){

        //This is a hack. Only use the first 9 characters of a string
        return date.substring(0,10);
    }
}
