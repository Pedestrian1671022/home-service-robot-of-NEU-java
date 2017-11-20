package com.example.pedestrian.homeservicerobotofneu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class AddEditRobotItemDialogFragment extends DialogFragment {

    public static final String POSITION_KEY = "POSITION_KEY";

    private EditText robot_name_edit_text;
    private EditText master_uri_edit_text;

    private LinearLayout mAdvancedOptionsView;
    private EditText mPCVoiceTopicEditTextView;
    private EditText mAndroidVoiceTopicEditTextView;
    private EditText mCameraTopicEditTextView;
    private EditText mJoystickTopicEditTextView;
    private EditText mOdometryTopicEditTextView;
    private EditText mScanTopicEditTextView;
    private EditText mMapTopicEditTextView;
    private EditText mInitialPoseTopicEditTextView;
    private EditText mGlobalPlanTopicEditView;
    private EditText mGoalTopicEditTextView;
    private EditText mSimpleGoalTopicEditTextView;

    private RobotItem robotItem;
    private DialogListener mListener;
    private int position = -1;

    public AddEditRobotItemDialogFragment(RobotItem robotItem){
        this.robotItem = robotItem;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            //throw new ClassCastException(activity.toString()  + " must implement DialogListener");
        }
    }

    public void setArguments(Bundle args) {
        super.setArguments(args);

        if (args != null) {
            this.position = args.getInt(POSITION_KEY, -1);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.dialog_add_robot_item, null);
        robot_name_edit_text = (EditText) v.findViewById(R.id.robot_name_edit_text);
        master_uri_edit_text = (EditText) v.findViewById(R.id.master_uri_edit_text);

        CheckBox mAdvancedOptionsCheckbox = (CheckBox) v.findViewById(R.id.advanced_options_checkbox_view);
        mAdvancedOptionsView = (LinearLayout) v.findViewById(R.id.advanced_options_view);
        mJoystickTopicEditTextView = (EditText) v.findViewById(R.id.joystick_topic);
        mPCVoiceTopicEditTextView = (EditText) v.findViewById(R.id.pc_voice_topic);
        mAndroidVoiceTopicEditTextView = (EditText) v.findViewById(R.id.android_voice_topic);
        mCameraTopicEditTextView = (EditText) v.findViewById(R.id.camera_topic);
        mJoystickTopicEditTextView = (EditText) v.findViewById(R.id.joystick_topic);
        mOdometryTopicEditTextView = (EditText) v.findViewById(R.id.odometry_topic);
        mScanTopicEditTextView = (EditText) v.findViewById(R.id.scan_topic);
        mMapTopicEditTextView = (EditText) v.findViewById(R.id.map_topic);
        mInitialPoseTopicEditTextView = (EditText) v.findViewById(R.id.initial_pose_topic);
        mGlobalPlanTopicEditView = (EditText) v.findViewById(R.id.global_plan_topic);
        mGoalTopicEditTextView = (EditText) v.findViewById(R.id.goal_topic);
        mSimpleGoalTopicEditTextView = (EditText) v.findViewById(R.id.simple_goal_topic);

        robot_name_edit_text.setText(robotItem.getRobot_name());
        master_uri_edit_text.setText(robotItem.getMaster_uri());

        mAdvancedOptionsCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    mAdvancedOptionsView.setVisibility(View.VISIBLE);
                } else {
                    mAdvancedOptionsView.setVisibility(View.GONE);
                }
            }
        });

        mPCVoiceTopicEditTextView.setText(robotItem.getPc_voice_topic());
        mAndroidVoiceTopicEditTextView.setText(robotItem.getAndroid_voice_topic());
        mCameraTopicEditTextView.setText(robotItem.getCamera_topic());
        mJoystickTopicEditTextView.setText(robotItem.getJoystick_topic());
        mOdometryTopicEditTextView.setText(robotItem.getOdometry_topic());
        mScanTopicEditTextView.setText(robotItem.getScan_topic());
        mMapTopicEditTextView.setText(robotItem.getMap_topic());
        mInitialPoseTopicEditTextView.setText(robotItem.getInitial_pose_topic());
        mGlobalPlanTopicEditView.setText(robotItem.getGlobal_plan_topic());
        mGoalTopicEditTextView.setText(robotItem.getGoal_topic());
        mSimpleGoalTopicEditTextView.setText(robotItem.getSimple_goal_topic());

        builder.setTitle("Edit/Add RobotItem")
                .setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = robot_name_edit_text.getText().toString().trim();
                        String masterUri = master_uri_edit_text.getText().toString().trim();
                        
                        String pc_voice_topic = mPCVoiceTopicEditTextView.getText().toString().trim();
                        String android_voice_topic = mAndroidVoiceTopicEditTextView.getText().toString().trim();
                        String camera_topic = mCameraTopicEditTextView.getText().toString().trim();
                        String joystick_topic = mJoystickTopicEditTextView.getText().toString().trim();
                        String odometry_topic = mOdometryTopicEditTextView.getText().toString().trim();
                        String scan_topic = mScanTopicEditTextView.getText().toString().trim();
                        String map_topic = mMapTopicEditTextView.getText().toString().trim();
                        String initial_pose_topic = mInitialPoseTopicEditTextView.getText().toString().trim();
                        String global_plan_topic = mGlobalPlanTopicEditView.getText().toString().trim();
                        String goal_topic = mGoalTopicEditTextView.getText().toString().trim();
                        String simple_goal_topic = mSimpleGoalTopicEditTextView.getText().toString().trim();

                        if (masterUri.equals("")) {
                            Toast.makeText(getActivity(), "Master URI required", Toast.LENGTH_SHORT).show();
                        } else if (name.equals("")) {
                            Toast.makeText(getActivity(), "Robot name required", Toast.LENGTH_SHORT).show();
                        } else if(pc_voice_topic.equals("") || android_voice_topic.equals("") || camera_topic.equals("")
                                || joystick_topic.equals("") || odometry_topic.equals("") || scan_topic.equals("")
                                || map_topic.equals("") || initial_pose_topic.equals("") || global_plan_topic.equals("")
                                || goal_topic.equals("") || simple_goal_topic.equals("")) {
                            Toast.makeText(getActivity(), "All topic names are required", Toast.LENGTH_SHORT).show();
                        } else {
                            mListener.onAddEditDialogPositiveClick(new RobotItem(name, masterUri, pc_voice_topic, android_voice_topic,
                                    camera_topic, joystick_topic, odometry_topic, scan_topic, map_topic, initial_pose_topic,
                                    global_plan_topic, goal_topic, simple_goal_topic), position);
                            dialog.dismiss();
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onAddEditDialogNegativeClick(AddEditRobotItemDialogFragment.this);
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public interface DialogListener {
        void onAddEditDialogPositiveClick(RobotItem robotItem, int position);

        void onAddEditDialogNegativeClick(DialogFragment dialog);
    }
}
