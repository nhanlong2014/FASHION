package com.example.fashion.api;

import android.content.SharedPreferences;

public class AccessTokenManager {

    //SharedPreferences là nơi để lưu khi xóa app thì mất hoặc update cũng mất
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static AccessTokenManager instance = null;

    public AccessTokenManager(SharedPreferences prefs, SharedPreferences.Editor editor) {
        this.prefs = prefs;
        this.editor = editor;
    }

//    public static synchronized AccessTokenManager getInstance(SharedPreferences _prefs){
//        if(instance == null){
//            instance = new AccessTokenManager(_prefs);
//        }
//        return instance;
//    }

    public void saveToken(AccessToken token){
        //lưu dưới dạng key values
        editor.putString("ACCESS_TOKEN", token.getAccess_token()).commit();
    }
    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
    }

    //hàm lấy ra
    public AccessToken getToken(){
        return new AccessToken(prefs.getString("ACCESS_TOKEN",null));
    }
}
