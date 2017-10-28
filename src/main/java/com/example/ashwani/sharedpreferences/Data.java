package com.example.ashwani.sharedpreferences;

import static android.R.attr.description;

/**
 * Created by ashwani on 25-10-2017.
 */

public class Data {
    String Title, Description;

    public Data(String title, String description ){
        this.Title = title;
        this.Description = description;
    }


    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }
}
