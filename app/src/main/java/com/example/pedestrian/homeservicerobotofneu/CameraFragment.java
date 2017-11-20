package com.example.pedestrian.homeservicerobotofneu;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jboss.netty.buffer.ChannelBuffer;
import org.ros.android.MessageCallable;
import org.ros.android.view.RosImageView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import sensor_msgs.CompressedImage;

public class CameraFragment extends Fragment {

    private RosImageView<CompressedImage> cameraView;
    private VirtualJoystickView virtualJoystickView;
    private TextView textView;
    private NodeMainExecutor nodeMainExecutor;
    private NodeConfiguration nodeConfiguration;


    public CameraFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActivity().getActionBar().setDisplayShowCustomEnabled(false);

        final View view = inflater.inflate(R.layout.camera_fragment, null);

        cameraView = (RosImageView<CompressedImage>) view.findViewById(R.id.camera_fragment_camera_view);
        virtualJoystickView = (VirtualJoystickView) view.findViewById(R.id.virtual_joystick);

        textView = (TextView) view.findViewById(R.id.noCameraTextView);

        cameraView.setTopicName(RobotItemActivity.static_robotItem.getCamera_topic());
        cameraView.setMessageType(CompressedImage._TYPE);
        cameraView.setMessageToBitmapCallable(new MessageCallable<Bitmap, CompressedImage>() {
            @Override
            public Bitmap call(CompressedImage compressedImage) {
                if(compressedImage != null)
                    textView.setVisibility(View.GONE);
                ChannelBuffer buffer = compressedImage.getData();
                byte[] data = buffer.array();
                return BitmapFactory.decodeByteArray(data, buffer.arrayOffset(), buffer.readableBytes());
            }
        });
        virtualJoystickView.setTopicName(RobotItemActivity.static_robotItem.getJoystick_topic());
        nodeMainExecutor.execute(cameraView, nodeConfiguration.setNodeName("/android/compressedImage/Subscriber"));
        nodeMainExecutor.execute(virtualJoystickView, nodeConfiguration.setNodeName("/android/velocity/Publisher"));
        return view;
    }

    public void initialize(NodeMainExecutor nodeMainExecutor, NodeConfiguration nodeConfiguration) {
        this.nodeMainExecutor = nodeMainExecutor;
        this.nodeConfiguration = nodeConfiguration;
    }

    public void shutdown(){
        nodeMainExecutor.shutdownNodeMain(cameraView);
        nodeMainExecutor.shutdownNodeMain(virtualJoystickView);
    }
}