package com.example.pedestrian.homeservicerobotofneu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddEditRobotItemDialogFragment.DialogListener, ConfirmDeleteDialogFragment.DialogListener, AdapterView.OnItemClickListener{


    public static Context context;
    private MenuItem menuItem;
    private RecyclerView recyclerView;
    private List<RobotItem> list_robotItem;
    private ViewHolderAdapter viewHolderAdapter;
    private DrawerLayout drawerLayout;
    private String[] drawerItems;
    private ListView listView;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainActivity.context = getApplicationContext();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        drawerItems = getResources().getStringArray(R.array.first_drawer_items);
        listView = (ListView)findViewById(R.id.left_drawer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        list_robotItem = new ArrayList<RobotItem>();
        list_robotItem.add(new RobotItem("Robot", "http://192.168.1.107:11311"));

        viewHolderAdapter = new ViewHolderAdapter(this, list_robotItem);
        recyclerView.setAdapter(viewHolderAdapter);


        // Icons for the navigation drawer
        int[] imgRes = new int[]{
                R.drawable.ic_android_black_24dp,
                R.drawable.ic_settings_black_24dp,
                R.drawable.ic_info_outline_black_24dp
        };

        // Populate the navigation drawer
        List<DrawerItem> list = new ArrayList<>();

        for (int i = 0; i < drawerItems.length; i++) {
            list.add(new DrawerItem(drawerItems[i], imgRes[i]));
        }

        DrawerAdapter drawerAdapter = new DrawerAdapter(this,
                R.layout.drawer_list_view,
                list);

        listView.setAdapter(drawerAdapter);
        listView.setOnItemClickListener(this);
//        listView.setSelection(0);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_robot_item_menu, menu);
        menuItem = menu.findItem(R.id.action_add_robot);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        AddEditRobotItemDialogFragment addRobotItemDialogFragment = new AddEditRobotItemDialogFragment(new RobotItem());
        addRobotItemDialogFragment.show(getSupportFragmentManager(), "addrobotitemdialog");
        return true;
    }

    @Override
    public void onAddEditDialogPositiveClick(RobotItem robotItem, int position) {
        if (position == -1)
            viewHolderAdapter.addData(0, robotItem);
        else {
            list_robotItem.set(position, robotItem);
            viewHolderAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onAddEditDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onConfirmDeleteDialogPositiveClick(int position) {
        viewHolderAdapter.removeData(position);
    }

    @Override
    public void onConfirmDeleteDialogNegativeClick() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        fragmentManager = getFragmentManager();
        switch (position){
            case 0:
                if (fragment != null) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                menuItem.setVisible(true);
                break;

            case 1:
                fragment = new PreferencesFragment();
                recyclerView.setVisibility(View.GONE);

                if (fragment != null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                }
                menuItem.setVisible(false);
                break;

            case 2:
                fragment = new FirstAboutFragment();
                recyclerView.setVisibility(View.GONE);

                if (fragment != null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                }
                menuItem.setVisible(false);
                break;

            default:
                break;
        }

//        listView.setSelection(position);
        drawerLayout.closeDrawers();
    }
}
