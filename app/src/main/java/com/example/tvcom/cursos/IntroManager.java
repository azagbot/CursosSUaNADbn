package com.example.tvcom.cursos;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tvcom on 02/11/16.
 */

public class IntroManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    public IntroManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences("first",0);
        editor = pref.edit();
    }
    public void setFirst(boolean isFirst){
        editor.putBoolean("check",isFirst);
        editor.commit();
    }
    public boolean Check(){
        return pref.getBoolean("check",true);
    }
}
