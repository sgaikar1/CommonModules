package com.example.smartrealitymodules.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dhaval Parmar on 9/9/2016.
 */
public class PreferenceUtils {
    /**
     * SharedPrefrence's Name
     */
    public static final String APP_PREF = "spr_preference";

    /**
     * Common Tags
     */
    // Cannot clear token as it is generated only once by FCM
    public final String KEY_DEVICE_TOKEN = "device_token";
//    public final String USER_TYPE = "userType";
//    public final String USER_ID = "userId";


    public final String KEY_USER_NAME = "Name";
    public final String KEY_USER_EMAILID = "EmailID";
    public final String KEY_USER_ADDRESS = "Address";
    public final String KEY_USER_MOBILE_NO = "MobileNo";
    public final String KEY_USER_DOB = "DOB";
    public final String KEY_USER_IsNotification = "IsNotification";
    public final String KEY_USER_AltMobileNo = "AltMobileNo";
    public final String KEY_USER_PROFILE_PATH = "ProfilePath";
    public final String KEY_USER_PROJECTID = "ProjectID";

    public final String KEY_APP_RATING = "Rating";

    public final String IS_MIS_SENT = "MIS_SENT";

    protected Context mContext;
    protected SharedPreferences mSettings;
    protected SharedPreferences.Editor mEditor;

    /**
     * Consturctor
     *
     * @param mContext       - context of Activity
     * @param preferenceName - Name of Sharedpreference for use
     */
    public PreferenceUtils(Context mContext, String preferenceName) {

        this.mContext = mContext;

        mSettings = mContext.getSharedPreferences(preferenceName,
                Context.MODE_PRIVATE);
        mEditor = mSettings.edit();
    }

    /**
     * Set a string value for the key
     */
    public void setStringValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key - key of the sharedpreferenec
     * @return string value of the key from shared preference
     */
    public String getStringValue(String key) {
        return mSettings.getString(key, "");
    }

    /**
     * Set a integer value for the key
     */
    public void setIntegerValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key          - key of the sharedpreferenec
     * @param defaultValue - Default value for the key, if one is not found.
     * @return integer value of the key from shared preference
     */
    public int getIntegerValue(String key, int defaultValue) {
        return mSettings.getInt(key, defaultValue);
    }

    /**
     * Set a double value for the key
     */
    public void setDoubleValue(String key, double value) {
        setStringValue(key, Double.toString(value));
    }

    /**
     * Set a Long value for the key
     */
    public void setLongValue(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key          - key of the sharedpreferenec
     * @param defaultValue - Default value for the key, if one is not found.
     * @return Long value of the key from shared preference
     */
    public long getLongValue(String key, long defaultValue) {
        return mSettings.getLong(key, defaultValue);
    }

    /**
     * Set a Boolean value for the key
     */
    public void setBooleanValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     * Gets the value from the preferences stored natively on the device.
     *
     * @param key      - key of the sharedpreferenec
     * @param defValue - Default value for the key, if one is not found.
     */
    public boolean getBooleanValue(String key, boolean defValue) {
        return mSettings.getBoolean(key, defValue);
    }


    /****
     * Clear all the preferences store in this {@link Editor}
     ****/
    public void clear() {
        mEditor.clear().commit();
    }

    /**
     * Removes preference entry for the given key.
     *
     * @param key
     */
    public void removeValue(String key) {
        if (mEditor != null) {
            mEditor.remove(key).commit();
        }
    }
}
