package com.joshuarichardson.legophototile.ui.displayTiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joshuarichardson.legophototile.MainActivity;
import com.joshuarichardson.legophototile.R;
import com.joshuarichardson.legophototile.StudPatternAdapter;

import static com.joshuarichardson.legophototile.MainActivity.STUD_WIDTH;

public class DisplayTilesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_display_tiles, container, false);

        MainActivity activity = (MainActivity) getActivity();

        RecyclerView studRecyclerView = view.findViewById(R.id.plateGridView);

        studRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 24));

        StudPatternAdapter adapter = new StudPatternAdapter(getContext(), activity.pixelsList);
        studRecyclerView.setAdapter(adapter);

        return view;
    }
}
