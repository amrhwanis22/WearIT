package com.example.amr.wearit.Comman;

import com.example.amr.wearit.Model.User;

/**
 * Created by amr on 12/26/17.
 */

public class Comman {

    public static User CurrentUser;

    public static final String DELETE = "Remove item";

    public static String convertCodeToStatus(String status)
    {

        if(status.equals("0"))
        {
            return "Placed";
        }
        else if(status.equals("1"))
        {
            return "Shipping";
        }
        else

            return "Delivered";

    }
}
