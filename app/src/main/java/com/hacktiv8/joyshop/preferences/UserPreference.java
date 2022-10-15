package com.hacktiv8.joyshop.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hacktiv8.joyshop.R;
import com.hacktiv8.joyshop.model.User;

public class UserPreference {
    private static final String PREFS_NAME = "user_pref";
    private static final String UID = "uid";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ROLE = "role";

    private final SharedPreferences preferences;

    public UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public void setUserPref(User value) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(UID, value.getuId());
        editor.putString(NAME, value.getUsername());
        editor.putString(EMAIL, value.getEmail());
        editor.putString(PHONE, value.getPhone());
        editor.putString(ROLE, value.getRole());

        editor.apply();
    }

    public void deleteUserPref() {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(UID, "");
        editor.putString(NAME, "");
        editor.putString(EMAIL, "");
        editor.putString(PHONE, "");
        editor.putString(ROLE, "");

        editor.apply();
    }

    public User getUserPref() {
        User user = new User();

        user.setuId(preferences.getString(UID, ""));
        user.setUsername(preferences.getString(NAME, ""));
        user.setEmail(preferences.getString(EMAIL, ""));
        user.setPhone(preferences.getString(PHONE, ""));
        user.setRole(preferences.getString(ROLE, ""));

        return user;
    }

}
