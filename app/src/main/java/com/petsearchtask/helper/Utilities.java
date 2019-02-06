package com.petsearchtask.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {




    public static String getTimeFromDate(String dateString) {
        Date initDate = null;
        String parsedDate = null;
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(dateString);

            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy ", Locale.US);
            parsedDate = formatter.format(initDate);
            System.out.println(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }


}
