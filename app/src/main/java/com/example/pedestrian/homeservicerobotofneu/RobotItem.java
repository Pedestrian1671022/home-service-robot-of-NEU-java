package com.example.pedestrian.homeservicerobotofneu;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Container for information about connections to specific Robots.
 *
 * Created by Michael Brunson on 1/23/16.
 */
public class RobotItem {

    public static final String ROBOT_NAME_KEY = "ROBOT_NAME_KEY";
    public static final String MASTER_URI_KEY = "MASTER_URI_KEY";
    public static final String PC_VOICE_TOPIC_KEY = "PC_VOICE_TOPIC_KEY";
    public static final String ANDROID_VOICE_TOPIC_KEY = "ANDROID_VOICE_TOPIC_KEY";
    public static final String CAMERA_TOPIC_KEY = "CAMERA_TOPIC_KEY";
    public static final String JOYSTICK_TOPIC_KEY = "JOYSTICK_TOPIC_KEY";
    public static final String ODOMETRY_TOPIC_KEY = "ODOMETRY_TOPIC_KEY";
    public static final String SCAN_TOPIC_KEY = "SCAN_TOPIC_KEY";
    public static final String MAP_TOPIC_KEY = "MAP_TOPIC_KEY";
    public static final String INITIAL_POSE_TOPIC_KEY = "INITIAL_POSE_TOPIC_KEY";
    public static final String GLOBAL_PLAN_TOPIC_KEY = "GLOBAL_PLAN_TOPIC_KEY";
    public static final String GOAL_TOPIC_KEY = "GOAL_TOPIC_KEY";
    public static final String SIMPLE_GOAL_TOPIC_KEY = "SIMPLE_GOAL_TOPIC_KEY";


    private String robot_name;
    private String master_uri;

    // Topic names
    private String pc_voice_topic;
    private String android_voice_topic;
    private String camera_topic;
    private String joystick_topic;
    private String odometry_topic;
    private String scan_topic;
    private String map_topic;
    private String initial_pose_topic;
    private String global_plan_topic;
    private String goal_topic;
    private String simple_goal_topic;

    public String getRobot_name() {
        return robot_name;
    }

    public void setRobot_name(String robot_name) {
        this.robot_name = robot_name;
    }

    public String getMaster_uri() {
        return master_uri;
    }

    public void setMaster_uri(String master_uri) {
        this.master_uri = master_uri;
    }

    public String getPc_voice_topic() {
        return pc_voice_topic;
    }

    public void setPc_voice_topic(String pc_voice_topic) {
        this.pc_voice_topic = pc_voice_topic;
    }

    public String getAndroid_voice_topic() {
        return android_voice_topic;
    }

    public void setAndroid_voice_topic(String android_voice_topic) {
        this.android_voice_topic = android_voice_topic;
    }

    public String getCamera_topic() {
        return camera_topic;
    }

    public void setCamera_topic(String camera_topic) {
        this.camera_topic = camera_topic;
    }

    public String getJoystick_topic() {
        return joystick_topic;
    }

    public void setJoystick_topic(String joystick_topic) {
        this.joystick_topic = joystick_topic;
    }

    public String getOdometry_topic() {
        return odometry_topic;
    }

    public void setOdometry_topic(String odometry_topic) {
        this.odometry_topic = odometry_topic;
    }

    public String getScan_topic() {
        return scan_topic;
    }

    public void setScan_topic(String scan_topic) {
        this.scan_topic = scan_topic;
    }

    public String getMap_topic() {
        return map_topic;
    }

    public void setMap_topic(String map_topic) {
        this.map_topic = map_topic;
    }

    public String getInitial_pose_topic() {
        return initial_pose_topic;
    }

    public void setInitial_pose_topic(String initial_pose_topic) {
        this.initial_pose_topic = initial_pose_topic;
    }

    public String getGlobal_plan_topic() {
        return global_plan_topic;
    }

    public void setGlobal_plan_topic(String global_plan_topic) {
        this.global_plan_topic = global_plan_topic;
    }

    public String getGoal_topic() {
        return goal_topic;
    }

    public void setGoal_topic(String goal_topic) {
        this.goal_topic = goal_topic;
    }

    public String getSimple_goal_topic() {
        return simple_goal_topic;
    }

    public void setSimple_goal_topic(String simple_goal_topic) {
        this.simple_goal_topic = simple_goal_topic;
    }

    public RobotItem() {
        SharedPreferences prefs = MainActivity.context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        this.robot_name = prefs.getString(ROBOT_NAME_KEY, "Robot");
        this.master_uri = prefs.getString(MASTER_URI_KEY, "http://localhost:11311");
        this.pc_voice_topic = prefs.getString(PC_VOICE_TOPIC_KEY, "/pc_remote_control");
        this.android_voice_topic = prefs.getString(ANDROID_VOICE_TOPIC_KEY, "/android_remote_control");
        this.camera_topic = prefs.getString(CAMERA_TOPIC_KEY, "/camera/rgb/image_raw/compressed");
        this.joystick_topic = prefs.getString(JOYSTICK_TOPIC_KEY, "/mobile_base/commands/velocity");
        this.odometry_topic = prefs.getString(ODOMETRY_TOPIC_KEY, "/odom");
        this.scan_topic = prefs.getString(SCAN_TOPIC_KEY, "/scan");
        this.map_topic = prefs.getString(MAP_TOPIC_KEY, "/map");
        this.initial_pose_topic = prefs.getString(INITIAL_POSE_TOPIC_KEY, "/initialpose");
        this.global_plan_topic = prefs.getString(GLOBAL_PLAN_TOPIC_KEY, "/move_base/DWAPlannerROS/global_plan");
        this.goal_topic = prefs.getString(GOAL_TOPIC_KEY, "/move_base/goal");
        this.simple_goal_topic = prefs.getString(SIMPLE_GOAL_TOPIC_KEY, "/move_base_simple/goal");
    }

    public RobotItem(String robot_name, String master_uri) {
        SharedPreferences prefs = MainActivity.context.getSharedPreferences("settings", Context.MODE_PRIVATE);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        this.robot_name = robot_name;
        this.master_uri = master_uri;
        this.pc_voice_topic = prefs.getString(PC_VOICE_TOPIC_KEY, "/pc_remote_control");
        this.android_voice_topic = prefs.getString(ANDROID_VOICE_TOPIC_KEY, "/android_remote_control");
        this.camera_topic = prefs.getString(CAMERA_TOPIC_KEY, "/camera/rgb/image_raw/compressed");
        this.joystick_topic = prefs.getString(JOYSTICK_TOPIC_KEY, "/mobile_base/commands/velocity");
        this.odometry_topic = prefs.getString(ODOMETRY_TOPIC_KEY, "/odom");
        this.scan_topic = prefs.getString(SCAN_TOPIC_KEY, "/scan");
        this.map_topic = prefs.getString(MAP_TOPIC_KEY, "/map");
        this.initial_pose_topic = prefs.getString(INITIAL_POSE_TOPIC_KEY, "/initialpose");
        this.global_plan_topic = prefs.getString(GLOBAL_PLAN_TOPIC_KEY, "/move_base/DWAPlannerROS/global_plan");
        this.goal_topic = prefs.getString(GOAL_TOPIC_KEY, "/move_base/goal");
        this.simple_goal_topic = prefs.getString(SIMPLE_GOAL_TOPIC_KEY, "/move_base_simple/goal");
    }

    public RobotItem(String name, String masterUriString, String pc_voice_topic, String android_voice_topic, String camera_topic,
                     String joystick_topic, String odometry_topic, String scan_topic, String map_topic, String initial_pose_topic,
                     String global_plan_topic, String goal_topic, String simple_goal_topic) {
        this.robot_name = name;
        this.master_uri = masterUriString;
        this.pc_voice_topic = pc_voice_topic;
        this.android_voice_topic = android_voice_topic;
        this.camera_topic = camera_topic;
        this.joystick_topic = joystick_topic;
        this.odometry_topic = odometry_topic;
        this.scan_topic = scan_topic;
        this.map_topic = map_topic;
        this.initial_pose_topic = initial_pose_topic;
        this.global_plan_topic = global_plan_topic;
        this.goal_topic = goal_topic;
        this.simple_goal_topic = simple_goal_topic;
    }
}
