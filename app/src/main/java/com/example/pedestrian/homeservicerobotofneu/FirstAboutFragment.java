package com.example.pedestrian.homeservicerobotofneu;

import android.annotation.SuppressLint;
import android.app.Fragment;
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
public class FirstAboutFragment extends Fragment {

    /**
     * Default Constructor.
     */
    public FirstAboutFragment() {}

    /**
     * Called when the activity is  created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.about_fragment, null);

        WebView webView = (WebView) view.findViewById(R.id.abouttxt);
        webView.loadData(Utils.readText(getActivity(), R.raw.about), "text/html", null);

        return view;
    }
}

