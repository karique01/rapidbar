package pe.edu.upc.rapidbar.models;

import android.content.Context;
import android.content.SharedPreferences;

import pe.edu.upc.rapidbar.helpers.Constants;

public class SharedPreferencesAccess {

    public static void SaveUserLogin (Context Contexto, UserLogin userLogin)
    {
        SharedPreferences sharedPref = Contexto.getSharedPreferences(Constants.USER_LOGIN_SESSION,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.USER_LOGIN_ATTRIBUTE_ID, userLogin.getId());
        editor.putString(Constants.USER_LOGIN_ATTRIBUTE_NAME, userLogin.getName());
        editor.putString(Constants.USER_LOGIN_ATTRIBUTE_USERNAME, userLogin.getUserName());
        editor.putString(Constants.USER_LOGIN_ATTRIBUTE_USERTYPE, userLogin.getUserType());
        editor.apply();
    }
    public static UserLogin LoadUserLogin (Context mContext)
    {
        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.USER_LOGIN_SESSION,0);
        UserLogin userLogin = new UserLogin();
        userLogin.setId(sharedPref.getString(Constants.USER_LOGIN_ATTRIBUTE_ID,Constants.SHARED_PREFERENCES_NOT_FOUND));
        userLogin.setName(sharedPref.getString(Constants.USER_LOGIN_ATTRIBUTE_NAME,Constants.SHARED_PREFERENCES_NOT_FOUND));
        userLogin.setUserName(sharedPref.getString(Constants.USER_LOGIN_ATTRIBUTE_USERNAME,Constants.SHARED_PREFERENCES_NOT_FOUND));
        userLogin.setUserType(sharedPref.getString(Constants.USER_LOGIN_ATTRIBUTE_USERTYPE,Constants.SHARED_PREFERENCES_NOT_FOUND));
        return userLogin.getId().equals(Constants.SHARED_PREFERENCES_NOT_FOUND) ? null : userLogin;
    }
    public static void DeleteUserLogin(Context mContext)
    {
        SharedPreferences settings = mContext.getSharedPreferences(Constants.USER_LOGIN_SESSION,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(Constants.USER_LOGIN_ATTRIBUTE_ID);
        editor.remove(Constants.USER_LOGIN_ATTRIBUTE_NAME);
        editor.remove(Constants.USER_LOGIN_ATTRIBUTE_USERNAME);
        editor.remove(Constants.USER_LOGIN_ATTRIBUTE_USERTYPE);
        editor.apply();
    }
}
