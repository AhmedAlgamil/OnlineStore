package com.algamil.mywaystore.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutFragment extends Fragment {


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_about , container , false );
        ButterKnife.bind(this , view);
        return view;
    }

    @OnClick ( { R.id.facebook_btn , R.id.whatsapp_btn , R.id.prog_fab_facebook , R.id.prog_fab_whats , R.id.prog_fab_linked , R.id.prog_fab_twitter , R.id.prog_fab_youtube } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.facebook_btn:
                openUrl ( "https://www.facebook.com/groups/212937796393461" );
                break;
            case R.id.whatsapp_btn:
                openUrl ( "https://chat.whatsapp.com/BplyojHC02KHKkFYtKR9uk" );
                break;
            case R.id.prog_fab_facebook:
                openUrl ( "https://www.facebook.com/eng.ahmed.5811877" );
                break;
            case R.id.prog_fab_whats:
                openUrl ( "https://wa.me/201277823947" );
                break;
            case R.id.prog_fab_linked:
                openUrl ( "https://www.linkedin.com/in/ahmed-algamil-861b1a187" );
                break;
            case R.id.prog_fab_twitter:
                openUrl ( "https://twitter.com/ahmedalgamil231?s=08 " );
                break;
            case R.id.prog_fab_youtube:
                openUrl ( "https://www.youtube.com/channel/UC7lZXUGd1ODOHtoRzu48Tfg" );
                break;
        }
    }

    public void openUrl ( String Url ) {
        Uri uri = Uri.parse ( Url );
        Intent intent = new Intent ( Intent.ACTION_VIEW , uri );
        startActivity ( intent );
    }

}
