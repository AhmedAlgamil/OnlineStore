package com.algamil.mywaystore.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.algamil.mywaystore.ui.fragments.FinishIntroFragment;
import com.algamil.mywaystore.ui.fragments.FoodIntroFragment;
import com.algamil.mywaystore.ui.fragments.GameFragment;
import com.algamil.mywaystore.ui.fragments.PerfumIntroFragment;
import com.algamil.mywaystore.ui.fragments.PrinterIntroFragment;

public class WalkThroughAdapter extends FragmentStatePagerAdapter  {
    private Fragment[] fr_screens = {new GameFragment ()
            ,new FoodIntroFragment ()
            ,new PerfumIntroFragment ()
            ,new PrinterIntroFragment ()
            ,new FinishIntroFragment ()};

    public int totals = fr_screens.length;

    public WalkThroughAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public WalkThroughAdapter ( @NonNull FragmentManager fm , int behavior ) {
        super ( fm , behavior );
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fr_screen = null;
        for (int i = 0;i<totals;i++) {
            if (position == i) {
                fr_screen = fr_screens[i];
            }
        }

       return fr_screen;
    }

    @Override
    public int getCount() {
        return totals;
    }
}
