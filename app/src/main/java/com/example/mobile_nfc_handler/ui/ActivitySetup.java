package com.example.mobile_nfc_handler.ui;

import android.app.Activity;
import android.widget.Button;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ActivitySetup {

     Activity activity;

    public ActivitySetup(Activity activity_Id) {

        this.activity = activity_Id;

    }



     public List<Button> setUpButtons(List<Button> buttonList, int layoutid) {

            List<Button> templist = new LinkedList<>();

        for (Iterator i = buttonList.iterator(); i.hasNext(); ) {

            templist.add(activity.findViewById(layoutid));
            i.next();
        }
        return templist;
    }
}
