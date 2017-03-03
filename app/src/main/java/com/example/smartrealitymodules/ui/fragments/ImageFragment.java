package com.example.smartrealitymodules.ui.fragments;

/**
 * Created by user on 28/2/17.
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.utils.TouchImageView;
import com.example.smartrealitymodules.utils.Utils;


public class ImageFragment extends Fragment {

    public TouchImageView touchview;

    TextView Noview;
    String url;
    int pos=1, count=1;

    public ImageFragment() {
        // Required empty public constructor
    }

    public ImageFragment newInstance(String url, int pos, int count) {

        ImageFragment fragment = new ImageFragment();

        Bundle b = new Bundle();
        b.putString("url", url);
        b.putInt("pos", pos);
        b.putInt("count", count);
        fragment.setArguments(b);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.view_image, container, false);

        url = this.getArguments().getString("url");
        pos = this.getArguments().getInt("pos",0);
        count = this.getArguments().getInt("count",0);

        touchview = (TouchImageView) view.findViewById(R.id.touchview);
        Noview = (TextView) view.findViewById(R.id.Noview);

        Noview.setVisibility(View.VISIBLE);
        Noview.setText((pos+1) + " of " + count);

        if (url.contains("http://") || url.contains("https://") && !url.isEmpty()) {

           Utils mUtils = new Utils();
            mUtils.loadImageInImageview(getContext(),url,touchview);


        }
        return view;
    }

}