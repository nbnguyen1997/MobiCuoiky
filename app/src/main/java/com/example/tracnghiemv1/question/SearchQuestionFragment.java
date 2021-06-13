package com.example.tracnghiemv1.question;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tracnghiemv1.R;

public class SearchQuestionFragment extends Fragment {

    EditText edtSearch;
    ListView lvResult;
    QuestionController questionController;
    QuestionAdapter questionAdapter;
    public SearchQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_question, container, false);
    }

    public void begin(){
        edtSearch= (EditText) getActivity().findViewById(R.id.edtSearch);
        lvResult= (ListView) getActivity().findViewById(R.id.lvResult);
        questionController = new QuestionController(getActivity());

        listCursor(questionController.getSearchQuestion(edtSearch.getText().toString()));
    }

    public  void listCursor (Cursor cursor){
        questionAdapter = new QuestionAdapter(getActivity(),cursor,true);
        lvResult.setAdapter(questionAdapter);
        questionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        begin();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listCursor (questionController.getSearchQuestion(edtSearch.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



}
