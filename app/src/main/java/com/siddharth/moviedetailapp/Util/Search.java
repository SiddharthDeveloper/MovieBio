package com.siddharth.moviedetailapp.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Search {

    private SharedPreferences sharedPreferences;

    public Search(Activity activity) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

    }

    public void SetSearch(String search) {
        sharedPreferences.edit().putString("search", search).apply();
    }

    public String getSearch() {

        return sharedPreferences.getString("search", "Batman");

    }

}
