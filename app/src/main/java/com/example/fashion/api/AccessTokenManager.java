package com.example.fashion.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fashion.model.ReponseModel;

public class AccessTokenManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static AccessTokenManager INSTANCE = null;

    private AccessTokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized AccessTokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new AccessTokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(ReponseModel token){
        editor.putString("ACCESS_TOKEN", token.getAccesss_token()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
    }

    public ReponseModel getToken(){
        ReponseModel token = new ReponseModel();
        token.setAccesss_token(prefs.getString("ACCESS_TOKEN", null));
        return token;
    }

}
