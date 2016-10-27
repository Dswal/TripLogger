package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by dswal on 17/10/2016.
 */
public class TripSettingsActivity extends SingleFragmentActivity
{
    private static final String EXTRA_TRIP_ID = "Insert_Random_Text_For_Debug";

    public static Intent newIntent(Context packageContext, UUID tripId) {
        Intent intent = new Intent(packageContext, TripSettingsActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, tripId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID tripId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_TRIP_ID);
        return TripSettingsFragment.newInstance(tripId);
    }



}
