package com.example.pedestrian.homeservicerobotofneu;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * About Fragment for the Robot Chooser screen.
 *
 * Created by Kenneth Spear on 3/15/16.
 */
public class SecondAboutFragment extends Fragment {

    /**
     * Default Constructor.
     */
    public SecondAboutFragment() {}

    /**
     * Called when the activity is  created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getActivity().getActionBar().setDisplayShowCustomEnabled(false);

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.about_fragment, null);

        WebView webView = (WebView) view.findViewById(R.id.abouttxt);
        webView.loadData(Utils.readText(getActivity(), R.raw.about), "text/html", null);

        return view;


    }
}

