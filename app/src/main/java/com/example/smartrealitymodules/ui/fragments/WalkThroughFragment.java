package com.example.smartrealitymodules.ui.fragments;

/**
 * Created by user on 28/2/17.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartrealitymodules.R;

/**
 * Created by vijay on 23/11/16.
 */
public class WalkThroughFragment extends Fragment {

    String mUrl = "";
//    VideoFragment frag;

    public WalkThroughFragment() {
    }

    public WalkThroughFragment newInstance(String url) {

        WalkThroughFragment fragment = new WalkThroughFragment();

        Bundle b = new Bundle();
        b.putString("url", url);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_walkthrough, container, false);


        mUrl = this.getArguments().getString("url");
        VideoFragment f = VideoFragment.newInstance(mUrl);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f).commit();



        return rootView;
    }
}
