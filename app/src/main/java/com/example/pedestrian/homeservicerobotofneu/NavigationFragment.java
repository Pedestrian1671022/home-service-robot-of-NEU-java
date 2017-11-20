package com.example.pedestrian.homeservicerobotofneu;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.github.rosjava.android_remocons.common_tools.apps.AppParameters;
import com.github.rosjava.android_remocons.common_tools.apps.MasterNameResolver;
import com.google.common.collect.Lists;

import org.ros.android.BitmapFromCompressedImage;
import org.ros.android.view.*;
import org.ros.android.view.visualization.VisualizationView;
import org.ros.android.view.visualization.layer.CameraControlListener;
import org.ros.android.view.visualization.layer.LaserScanLayer;
import org.ros.android.view.visualization.layer.Layer;
import org.ros.android.view.visualization.layer.OccupancyGridLayer;
import org.ros.android.view.visualization.layer.PathLayer;
import org.ros.namespace.NameResolver;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import sensor_msgs.CompressedImage;


public class NavigationFragment extends Fragment {

    private MasterNameResolver masterNameResolver;
    private AppParameters appParameters;
//    private AppRemappings remaps;
    private RosImageView<CompressedImage> cameraView;
    private VirtualJoystickView virtualJoystickView;
    private VisualizationView mapView;
    private ViewGroup mainLayout;
    private ViewGroup sideLayout;
    private MapPosePublisherLayer mapPosePublisherLayer;
    private NodeMainExecutor nodeMainExecutor;
    private NodeConfiguration nodeConfiguration;
    
    public NavigationFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        View customView = inflater.inflate(R.layout.point_selection, null);

        RadioButton radioButton_pose = customView.findViewById(R.id.set_pose_button);
        RadioButton radioButton_goal = customView.findViewById(R.id.set_goal_button);
        radioButton_pose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapPosePublisherLayer.setPoseMode();
            }
        });

        radioButton_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapPosePublisherLayer.setGoalMode();
            }
        });
        getActivity().getActionBar().setCustomView(customView);
        getActivity().getActionBar().setDisplayShowCustomEnabled(true);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.navigation_fragment, null);

        this.masterNameResolver = new MasterNameResolver();
        this.masterNameResolver.setMasterName("");
        this.appParameters = new AppParameters();
//        this.remaps = new AppRemappings();

        cameraView = (RosImageView<CompressedImage>) view.findViewById(R.id.image);
        cameraView.setMessageType(CompressedImage._TYPE);
        cameraView.setMessageToBitmapCallable(new BitmapFromCompressedImage());
        virtualJoystickView = (VirtualJoystickView) view.findViewById(R.id.virtual_joystick);
        mapView = (VisualizationView) view.findViewById(R.id.map_view);
        mapView.onCreate(Lists.<Layer>newArrayList());

        mapView.getCamera().jumpToFrame(RobotItemActivity.static_robotItem.getMap_topic());
        mainLayout = (ViewGroup) view.findViewById(R.id.main_layout);
        sideLayout = (ViewGroup) view.findViewById(R.id.side_layout);

        nodeMainExecutor.execute(this.masterNameResolver, this.nodeConfiguration.setNodeName("/masterNameResolver"));
        this.masterNameResolver.waitForResolver();

        String joyTopic = RobotItemActivity.static_robotItem.getJoystick_topic();
        String camTopic = RobotItemActivity.static_robotItem.getCamera_topic();

        NameResolver nameResolver = masterNameResolver.getMasterNameSpace();
        cameraView.setTopicName(camTopic);
        virtualJoystickView.setTopicName(joyTopic);

        nodeMainExecutor.execute(cameraView,
                nodeConfiguration.setNodeName("android/navigation/compressedImageSubscriber"));
        nodeMainExecutor.execute(virtualJoystickView,
                nodeConfiguration.setNodeName("android/navigation/velocityPublisher"));

        ViewControlLayer viewControlLayer =
                new ViewControlLayer(getActivity(), nodeMainExecutor.getScheduledExecutorService(), cameraView,
                        mapView, mainLayout, sideLayout, appParameters);

        String mapTopic   = RobotItemActivity.static_robotItem.getMap_topic();
        String scanTopic  = RobotItemActivity.static_robotItem.getScan_topic();
        String planTopic  = RobotItemActivity.static_robotItem.getGlobal_plan_topic();

        OccupancyGridLayer occupancyGridLayer = new OccupancyGridLayer(nameResolver.resolve(mapTopic).toString());
        LaserScanLayer laserScanLayer = new LaserScanLayer(nameResolver.resolve(scanTopic).toString());
        PathLayer pathLayer = new PathLayer(nameResolver.resolve(planTopic).toString());
        mapPosePublisherLayer = new MapPosePublisherLayer(getActivity(), nameResolver, appParameters);

        mapView.addLayer(viewControlLayer);
        mapView.addLayer(occupancyGridLayer);
        mapView.addLayer(laserScanLayer);
        mapView.addLayer(pathLayer);
        mapView.addLayer(mapPosePublisherLayer);

        mapView.init(nodeMainExecutor);
        viewControlLayer.addListener(new CameraControlListener() {
            @Override
            public void onZoom(float focusX, float focusY, float factor) {}
            @Override
            public void onDoubleTap(float x, float y) {}
            @Override
            public void onTranslate(float distanceX, float distanceY) {}
            @Override
            public void onRotate(float focusX, float focusY, double deltaAngle) {}
        });

        nodeMainExecutor.execute(mapView, nodeConfiguration.setNodeName("android/navigation/mapSubscriber"));


        return view;
    }

    public void initialize(NodeMainExecutor nodeMainExecutor, NodeConfiguration nodeConfiguration) {
        this.nodeMainExecutor = nodeMainExecutor;
        this.nodeConfiguration = nodeConfiguration;
    }

    public void shutdown(){
        nodeMainExecutor.shutdownNodeMain(cameraView);
        nodeMainExecutor.shutdownNodeMain(virtualJoystickView);
        mapPosePublisherLayer.shutdown();
    }
}
