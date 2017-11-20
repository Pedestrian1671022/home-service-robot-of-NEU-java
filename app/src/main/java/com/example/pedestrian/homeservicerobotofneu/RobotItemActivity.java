package com.example.pedestrian.homeservicerobotofneu;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedestrian-username on 17-7-13.
 */

public class RobotItemActivity extends RosActivity implements  AdapterView.OnItemSelectedListener, ListView.OnItemClickListener {

    public static RobotItem static_robotItem;
    public static NodeConfiguration nodeConfiguration;
    public static NodeMainExecutor nodeMainExecutor;

//    private Spinner actionMenuSpinner;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] drawerItems;
    private ListView listView;

    private Fragment fragment;

    public RobotItemActivity() {
        super("Robot Item", "Robot Item", URI.create(static_robotItem.getMaster_uri()));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.robot_item_activity);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (getActionBar() != null) {
            ActionBar actionBar = getActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
               /* R.drawable.ic_drawer,*/ R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


        listView = (ListView) findViewById(R.id.left_drawer);
        drawerItems = getResources().getStringArray(R.array.second_drawer_items);

        int[] imgRes = new int[]{
                R.drawable.ic_android_black_24dp,
                R.drawable.ic_linked_camera_black_24dp,
//                R.drawable.ic_videocall_black_24dp,
                R.drawable.ic_navigation_black_24dp,
                R.drawable.ic_help_black_24dp
        };
        List<DrawerItem> list = new ArrayList<>();

        for (int i = 0; i < drawerItems.length; i++) {
            list.add(new DrawerItem(drawerItems[i], imgRes[i]));
        }

        DrawerAdapter drawerAdapter = new DrawerAdapter(this, R.layout.drawer_list_view, list);

        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {

        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname(), getMasterUri());

        this.nodeMainExecutor = nodeMainExecutor;
        this.nodeConfiguration = nodeConfiguration;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                selectedItem(0);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        selectedItem(position);
    }

    public void selectedItem(int position){
        if(fragment != null){
            if(fragment instanceof OrderFragment){
                ((OrderFragment) fragment).shutdown();
            }
            if(fragment instanceof CameraFragment){
                ((CameraFragment) fragment).shutdown();
            }
//            if(fragment instanceof VideoChatFragment){
//                ((VideoChatFragment) fragment).shutdown();
//            }
            if(fragment instanceof NavigationFragment){
                ((NavigationFragment) fragment).shutdown();
            }
        }

        switch (position) {

            case 0:
                OrderFragment orderFragment = new OrderFragment();
                orderFragment.initialize(nodeMainExecutor, nodeConfiguration);
                fragment = orderFragment;
                break;

            case 1:
                CameraFragment cameraFragment = new CameraFragment();
                cameraFragment.initialize(nodeMainExecutor, nodeConfiguration);
                fragment = cameraFragment;
                break;

//            case 2:
//                VideoChatFragment videoChatFragment = new VideoChatFragment();
//                videoChatFragment.initialize(nodeMainExecutor, nodeConfiguration);
//                fragment = videoChatFragment;
//                break;

            case 2:
                NavigationFragment navigationFragment = new NavigationFragment();
                navigationFragment.initialize(nodeMainExecutor, nodeConfiguration);
                fragment = navigationFragment;
                break;

            case 3:
                fragment = new SecondAboutFragment();
                break;

            default:
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        drawerLayout.closeDrawers();
    }
}
