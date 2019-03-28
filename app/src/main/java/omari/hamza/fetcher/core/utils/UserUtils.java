package omari.hamza.fetcher.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.net.IDN;

import omari.hamza.fetcher.core.models.User;

public class UserUtils {

    private static final String SHARED_PREFERENCES_TAG = "SHARED_PREFERENCES";
    private static final String USER_ID_TAG = "ID";
    private static final String USERNAME_TAG = "USERNAME";
    private static final String EMAIL_TAG = "EMAIL";
    private static final String MOBILE_TAG = "MOBILE";
    private static final String TYPE_TAG = "TYPE";
    private static final String USER_TOKEN_TAG = "USER_TOKEN";
    private static final String COMPANY_WEBSITE_TAG = "WEBSITE";
    private static final String COMPANY_EMAIL_TAG = "COMPANY_EMAIL";
    private static final String COMPANY_ADDRESS_TAG = "COMPANY_ADDRESS";
    private static final String WORK_FIELD_TAG = "WORK_FIELD";

    public static void loginUser(@NonNull Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putInt(USER_ID_TAG, user.getId());
        mEditor.putString(USERNAME_TAG, user.getUsername());
        mEditor.putString(EMAIL_TAG, user.getEmail());
        mEditor.putString(MOBILE_TAG, user.getMobile());
        mEditor.putString(TYPE_TAG, user.getType());
        mEditor.putString(COMPANY_WEBSITE_TAG, user.getCompanyWebsite());
        mEditor.putString(COMPANY_EMAIL_TAG, user.getEmail());
        mEditor.putString(COMPANY_ADDRESS_TAG, user.getCompanyAddress());
        mEditor.putString(WORK_FIELD_TAG, user.getWorkField());
        mEditor.apply();
    }

    public static User getLoggedUser(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        return new User(sharedPreferences.getInt(USER_ID_TAG, 0),
                sharedPreferences.getString(WORK_FIELD_TAG, ""),
                sharedPreferences.getString(USERNAME_TAG, ""),
                sharedPreferences.getString(EMAIL_TAG, ""),
                sharedPreferences.getString(MOBILE_TAG, ""),
                sharedPreferences.getString(TYPE_TAG, ""),
                sharedPreferences.getString(COMPANY_WEBSITE_TAG, ""),
                sharedPreferences.getString(COMPANY_EMAIL_TAG, ""),
                sharedPreferences.getString(COMPANY_ADDRESS_TAG, "")
        );
    }

    public static boolean isUserLoggedIn(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        return !sharedPreferences.getString(USERNAME_TAG, "null").equalsIgnoreCase("null");
    }

    public static void saveUserToken(@NonNull Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(USER_TOKEN_TAG, "bearer " + token);
        mEditor.apply();
    }

    public static String getUserToken(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        return sharedPreferences.getString(USER_TOKEN_TAG, "");
    }

    public static void logoutUser(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TAG, 0);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.clear();
        mEditor.apply();
    }
}
