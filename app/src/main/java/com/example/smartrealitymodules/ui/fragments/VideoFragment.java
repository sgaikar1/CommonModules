package com.example.smartrealitymodules.ui.fragments;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by user on 28/2/17.
 */
public class VideoFragment extends YouTubePlayerSupportFragment {
    // private YouTubePlayer activePlayer;
    int current_seektime = 0;

    public VideoFragment() {
    }

    public static VideoFragment newInstance(String url) {

        VideoFragment f = null;
        try {
            f = new VideoFragment();

            Bundle b = new Bundle();
            b.putString("url", url);

            f.setArguments(b);
            f.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    private void init() {

        initialize("AIzaSyBIXR7GSapsXiaWBQIi8Szv1pXHJ08v3pU",
                new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider arg0,
                                                        YouTubeInitializationResult arg1) {
                        Toast.makeText(getActivity(), arg1.toString(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onInitializationSuccess(
                            YouTubePlayer.Provider provider,
                            YouTubePlayer player, boolean wasRestored) {

                        if (!wasRestored) {
                            if (current_seektime == 0) {
                                player.cueVideo(getArguments().getString("url"));
                                current_seektime = player
                                        .getCurrentTimeMillis();
                            } else {
                                player.seekToMillis(current_seektime);
                            }
                        }

                    }

                });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            current_seektime = savedInstanceState.getInt("seekValue");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // int seekValue; //set this to the current position;
        super.onSaveInstanceState(outState);
        outState.putInt("seekValue", current_seektime);

    }

}