package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by dswal on 25/08/2016.
 */
public class TripListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TripListFragment();
    }
}
