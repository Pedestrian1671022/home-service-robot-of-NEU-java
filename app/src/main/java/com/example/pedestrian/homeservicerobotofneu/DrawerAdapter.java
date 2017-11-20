package com.example.pedestrian.homeservicerobotofneu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter class for the NavDrawer.
 *
 * Created by Michael Brunson on 1/23/16.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    List<DrawerItem> list;
    int layoutResID;

    /**
     * Creates a NavDrawerAdapter.
     * @param context The Adapter's context
     * @param layoutResourceID The resource id of the layout
     * @param list The list of items for the NavDrawer
     */
    public DrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> list) {
        super(context, layoutResourceID, list);
        this.context = context;
        this.list = list;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemHolder drawerHolder;

        if (convertView == null) {

            drawerHolder =new DrawerItemHolder();
            convertView = LayoutInflater.from(context).inflate(layoutResID, null);
            convertView.setTag(drawerHolder);
        } else {
            drawerHolder = (DrawerItemHolder) convertView.getTag();
        }

        drawerHolder.ItemName = (TextView) convertView.findViewById(R.id.drawer_item_text_view);
        drawerHolder.icon = (ImageView) convertView.findViewById(R.id.drawer_item_image_view);

        drawerHolder.ItemName.setText(list.get(position).getItemName());
        drawerHolder.icon.setImageResource(list.get(position).getImgResID());

        return convertView;
    }

    /*
     * Container class for a drawer item
     */
    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }
}
