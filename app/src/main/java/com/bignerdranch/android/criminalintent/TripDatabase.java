package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.bignerdranch.android.criminalintent.database.TripBaseHelper;
import com.bignerdranch.android.criminalintent.database.TripCursorWrapper;
import com.bignerdranch.android.criminalintent.database.TripDbSchema.TripTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dswal on 25/08/2016.
 */
public class TripDatabase {

    private static TripDatabase sTripDatabase;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TripDatabase get(Context context) {
        if (sTripDatabase == null) {
            sTripDatabase = new TripDatabase(context);
        }
        return sTripDatabase;
    }

    private TripDatabase(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TripBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addTrip(Trip c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(TripTable.NAME, null, values);
    }

//    http://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android

    public boolean deleteTrip(String name)
    {
        return mDatabase.delete(TripTable.NAME, "TITLE" + "='" + name +"' ;", null) > 0;

    }

    public List<Trip> getTrip(){
        List<Trip> trips = new ArrayList<>();

        TripCursorWrapper cursor = queryTrip(null, null);

        Log.d("TTTEST", cursor.getCount()+"");

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                trips.add(cursor.getTrip());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return trips;
    }

    public Trip getTrip(UUID id){
        TripCursorWrapper cursor = queryTrip(
                TripTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTrip();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Trip trip) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, trip.getPhotoFilename());
    }

    public void updateTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        ContentValues values = getContentValues(trip);

        mDatabase.update(TripTable.NAME, values,
                TripTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Trip trip) {
        ContentValues values = new ContentValues();
        values.put(TripTable.Cols.UUID, trip.getId().toString());
        values.put(TripTable.Cols.TITLE, trip.getTitle());
        values.put(TripTable.Cols.DATE, trip.getDate().getTime());
        values.put(TripTable.Cols.DESTINATION, trip.getDestination());
        values.put(TripTable.Cols.DURATION, trip.getDuration());
        values.put(TripTable.Cols.COMMENT, trip.getComment());
        return values;
    }

    private TripCursorWrapper queryTrip(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TripTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TripCursorWrapper(cursor);
    }
}
