package com.example.smartrealitymodules.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartrealitymodules.R;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.ui.adapter.GalleryImagesAdapter;
import com.example.smartrealitymodules.utils.Constants;

import java.util.ArrayList;

/**
 * Created by user on 28/2/17.
 */
public class GalleryImagesFragment extends Fragment {

    private RecyclerView recyclerGalleryImages;
    private RecyclerView.LayoutManager mLayoutManager;
    private GalleryImagesAdapter mGalleryImagesAdapter;

    private ArrayList<String> mGalleryImages;
    private ArrayList<ProjectDetailsRes.Architectures> gallery_Architectures;
    private ArrayList<ProjectDetailsRes.Constructions> gallery_Constructions;
    private TextView txt_gallery_images;
    private String type = "";

    public GalleryImagesFragment() {
    }

    public GalleryImagesFragment newInstance(ArrayList<ProjectDetailsRes.Architectures> gallery_Architectures, String type) {

        GalleryImagesFragment fragment = new GalleryImagesFragment();
        Bundle b = new Bundle();
        b.putString(Constants.TYPE_KEY, type);
        b.putParcelableArrayList(Constants.ARCHITECTURE_KEY, gallery_Architectures);
        fragment.setArguments(b);

        return fragment;
    }

    public GalleryImagesFragment newInstance(ArrayList<ProjectDetailsRes.Constructions> gallery_Constructions) {

        GalleryImagesFragment fragment = new GalleryImagesFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList(Constants.CUNSTRUCTION_KEY, gallery_Constructions);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_achitectural, container, false);

        type = this.getArguments().getString(Constants.TYPE_KEY);
        gallery_Architectures = this.getArguments().getParcelableArrayList(Constants.ARCHITECTURE_KEY);
        gallery_Constructions = this.getArguments().getParcelableArrayList(Constants.CUNSTRUCTION_KEY);
        findViews(rootView);

        return rootView;
    }

    private void findViews(View rootView) {
        txt_gallery_images = (TextView) rootView.findViewById(R.id.txt_gallery_images);
        recyclerGalleryImages = (RecyclerView) rootView.findViewById(R.id.recycler_gallery_images);
        recyclerGalleryImages.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerGalleryImages.setLayoutManager(mLayoutManager);

        initailizeGalleryImages();
    }

    private void initailizeGalleryImages() {

        mGalleryImages = new ArrayList<>();

        if (gallery_Architectures != null && gallery_Architectures.size() > 0) {
            for (int i = 0; i < gallery_Architectures.size(); i++) {
                mGalleryImages.add(gallery_Architectures.get(i).getFilePath());
            }
        } else if (gallery_Constructions != null && gallery_Constructions.size() > 0) {
            for (int i = 0; i < gallery_Constructions.size(); i++) {
                mGalleryImages.add(gallery_Constructions.get(i).getFilePath());
            }
        }

        if (mGalleryImages.size() > 0) {
            mGalleryImagesAdapter = new GalleryImagesAdapter(mGalleryImages, getActivity());
            recyclerGalleryImages.setAdapter(mGalleryImagesAdapter);
            recyclerGalleryImages.setVisibility(View.VISIBLE);
            txt_gallery_images.setVisibility(View.GONE);
        } else {
            recyclerGalleryImages.setVisibility(View.GONE);
            txt_gallery_images.setVisibility(View.VISIBLE);
        }
    }
}
