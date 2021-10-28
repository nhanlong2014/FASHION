package com.example.fashion.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fashion.model.User;


public class SharedPrefManager {

    private static String SHARED_PREF_NAME="thecodingshef";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;


    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("fullname",user.getFullname());
        editor.putString("email",user.getEmail());
        editor.apply();


    }

   public boolean isLoggedIn(){
       sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("logged",false);
    }


    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("fullname",null),
                sharedPreferences.getString("email1",null),
        sharedPreferences.getString("hash_passwrod",null),
        sharedPreferences.getInt("sdt",0));

    }


   public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

        }
}
