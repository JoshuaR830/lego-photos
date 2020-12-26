package com.joshuarichardson.legophototile.ui.pictureCreator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshuarichardson.legophototile.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PictureCreatorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_creator, container, false);

        return view;
    }
}
