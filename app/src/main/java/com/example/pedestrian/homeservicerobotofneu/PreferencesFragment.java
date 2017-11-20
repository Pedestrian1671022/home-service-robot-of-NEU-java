package com.example.pedestrian.homeservicerobotofneu;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Fragment containing the Preferences screen.
 *
 * Created by Michael Brunson on 11/7/15.
 */
public class PreferencesFragment extends PreferenceFragment {

    // Log tag String
    private static final String TAG = "PreferencesFragment";

    /**
     * Default Constructor.
     */
    public PreferencesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add the preferences
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceManager().setSharedPreferencesName("settings");
    }
//    @Override
//    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        if(RobotItem.ROBOT_NAME_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.ROBOT_NAME_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.MASTER_URI_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.MASTER_URI_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.PC_VOICE_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.PC_VOICE_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.ANDROID_VOICE_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.ANDROID_VOICE_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.CAMERA_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.CAMERA_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.JOYSTICK_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.JOYSTICK_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.ODOMETRY_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.ODOMETRY_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.SCAN_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.SCAN_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.MAP_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.MAP_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.INITIAL_POSE_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.INITIAL_POSE_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.GLOBAL_PLAN_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.GLOBAL_PLAN_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.GOAL_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.GOAL_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        if(RobotItem.SIMPLE_GOAL_TOPIC_KEY.equals(preference.getKey())){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(RobotItem.SIMPLE_GOAL_TOPIC_KEY, ((BetterEditTextPreference)preference).getText());
//        }
//        return super.onPreferenceTreeClick(preferenceScreen, preference);
//    }
}
