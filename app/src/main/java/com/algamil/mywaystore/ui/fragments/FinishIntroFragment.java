package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FinishIntroFragment extends Fragment {

    @BindView ( R.id.pen )
    ImageView pen;
    @BindView ( R.id.pencil )
    ImageView pencil;
    @BindView ( R.id.paper )
    ImageView paper;
    @BindView ( R.id.toys )
    ImageView toys;
    @BindView ( R.id.game )
    ImageView game;
    @BindView ( R.id.game2 )
    ImageView game2;
    @BindView ( R.id.printer )
    ImageView printer;
    @BindView ( R.id.marker )
    ImageView marker;
    @BindView ( R.id.glue )
    ImageView glue;
    @BindView ( R.id.shawer )
    ImageView shawer;
    @BindView ( R.id.soup )
    ImageView soup;
    @BindView ( R.id.clean2 )
    ImageView clean2;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate ( R.layout.fragment_finish_intro , container , false );
        ButterKnife.bind ( this , view );
        return view;
    }


}
