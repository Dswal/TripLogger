package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Trip;
import com.bignerdranch.android.criminalintent.database.TripDbSchema.TripTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dswal on 7/09/2016.
 */
public class TripCursorWrapper extends CursorWrapper {

    public TripCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Trip getTrip() {
        String uuidString = getString(getColumnIndex(TripTable.Cols.UUID));
        String title = getString(getColumnIndex(TripTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TripTable.Cols.DATE));
        String destination = getString(getColumnIndex(TripTable.Cols.DESTINATION));
        String duration = getString(getColumnIndex(TripTable.Cols.DURATION));
        String comment = getString(getColumnIndex(TripTable.Cols.COMMENT));

        Trip trip = new Trip(UUID.fromString(uuidString));
        trip.setTitle(title);
        trip.setDate(new Date(date));
        trip.setDestination(destination);
        trip.setDuration(duration);
        trip.setComment(comment);
        return trip;
    }
}
