package com.example.tracnghiemv1.Score;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tracnghiemv1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    ListView lvListScore;
    ScoreController scoreController;
    ScoreAdapter scoreAdapter;
    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scoreController = new ScoreController(getActivity());
        lvListScore = (ListView) getActivity().findViewById(R.id.lvListScore);
        Cursor cursor = scoreController.getScore();
        scoreAdapter = new ScoreAdapter(getActivity(),cursor,true);
        lvListScore.setAdapter(scoreAdapter);
    }
}
