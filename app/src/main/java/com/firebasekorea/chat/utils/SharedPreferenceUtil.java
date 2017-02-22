package com.firebasekorea.chat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by namhoonkim on 17/02/2017.
 */

public class SharedPreferenceUtil {
    public static final String DEFAULT_SETTING = "FIREBASE_KOREA_REAL_TIME_CHAT";
    public static final String USER_NAME = "user_name";

    private static volatile SharedPreferenceUtil instance = null;

    private Context context;
    private SharedPreferences pref;

    private SharedPreferenceUtil() {}

    public static SharedPreferenceUtil getInstance() {
        if(instance == null) {
            synchronized (SharedPreferenceUtil.class) {
                instance = new SharedPreferenceUtil();
            }
        }
        return instance;
    }


    public void init(Context _context) {
        context = _context;
        pref = context.getSharedPreferences(DEFAULT_SETTING, context.MODE_PRIVATE);
    }

    public void putString(String _key, String _value) {
        if(pref == null){
            return;
        }
        SharedPreferences.Editor e = pref.edit();
        e.putString(_key, _value);
        e.commit();
    }

    public String getString(String _key, String _value) {
        if(pref == null){
            return null;
        }
        return pref.getString(_key, _value);
    }

    public void putBoolean(String _key, boolean _value) {
        if(pref == null) {
            return;
        }
        SharedPreferences.Editor e = pref.edit();
        e.putBoolean(_key, _value);
        e.commit();
    }

    public boolean getBoolean(String _key, boolean _value) {
        if(pref == null) {
            return false;
        }
        return pref.getBoolean(_key, _value);
    }

    public void putInt(String _key, int _value) {
        if(pref == null) {
            return;
        }
        SharedPreferences.Editor e = pref.edit();
        e.putInt(_key, _value);
        e.commit();
    }

    public int getInt(String _key, int _value) {
        if(pref == null) {
            return -1;
        }
        return pref.getInt(_key, _value);
    }

    public void putLong(String _key, long _value) {
        if(pref == null) {
            return;
        }
        SharedPreferences.Editor e = pref.edit();
        e.putLong(_key, _value);
        e.commit();
    }

    public long getLong(String _key, long _value) {
        if(pref == null) {
            return -1;
        }
        return pref.getLong(_key, _value);
    }

    public void clear() {
        SharedPreferences.Editor e =pref.edit();
        e.clear();
        e.commit();
    }

}

