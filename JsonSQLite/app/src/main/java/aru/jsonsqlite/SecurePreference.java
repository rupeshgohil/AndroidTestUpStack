package aru.jsonsqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SecurePreference {

    public static String Preference_Name ="android";
    static SharedPreferences sh_Pref;

    public static String getPreference(String key, String Default,
                                       Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        return sh_Pref.getString(key, Default);
    }

    //    public static HashMap<String, String> getPreferencefornotify(String key, String Default,
//                                                                 Context activity) {
//        sh_Pref = activity.getSharedPreferences(Preference_Name,
//                Context.MODE_PRIVATE);
//        return sh_Pref.getString(key, Default);
//    }
    public static boolean getPreferenceBoolean(String key, boolean Default,
                                               Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        return sh_Pref.getBoolean(key, Default);
    }

    public static int getPreferenceInt(String key, int Default, Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        return sh_Pref.getInt(key, Default);
    }

    public static boolean setPreference(String key, String value, Context activity) {
        if (value != null) {
            if (getPreference(key, "", activity).equals(value)) {
                return false;
            } else {
                sh_Pref = activity.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
                Editor editor = sh_Pref.edit();
                editor.putString(key, value);
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public static void setPreferenceBoolean(String key, boolean value,
                                            Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        Editor editor = sh_Pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setPreferenceInt(String key, int value, Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        Editor editor = sh_Pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void deletePref(Context activity) {
        sh_Pref = activity.getSharedPreferences(Preference_Name,
                Context.MODE_PRIVATE);
        sh_Pref.edit().clear().commit();
    }

}
