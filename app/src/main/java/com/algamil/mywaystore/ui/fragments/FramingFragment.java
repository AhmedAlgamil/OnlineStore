package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;

public class FramingFragment extends Fragment {

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_framing , container , false );
        HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () ,R.id.framing_frame ,new CategoryFragment () );
        return view;
    }
}