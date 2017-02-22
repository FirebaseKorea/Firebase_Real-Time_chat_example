package com.firebasekorea.chat.utils;

/**
 * Created by namhoonkim on 17/02/2017.
 */

public class UserUtil {
    public static void saveUserName(String userName) {
        SharedPreferenceUtil.getInstance().putString(SharedPreferenceUtil.USER_NAME, userName);
    }
    public static String loadUserName() {
        return SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.USER_NAME, null);
    }

}
