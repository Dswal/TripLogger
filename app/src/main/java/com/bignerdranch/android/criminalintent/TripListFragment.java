package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

/**
 * Created by dswal on 25/08/2016.
 */
public class TripListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private TripAdapter mAdapter;

    private Button mButton;
    private Button mSettingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.trip_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mButton = (Button) view.findViewById(R.id.new_trip_button);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Trip trip = new Trip();
                TripDatabase.get(getActivity()).addTrip(trip);
                Intent intent = TripActivity.newIntent(getActivity(), trip.getId());
                startActivity(intent);
            }
        });

        mSettingsButton = (Button) view.findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Trip trip = new Trip();
                Intent intent = TripSettingsActivity.newIntent(getActivity(), trip.getId());
                startActivity(intent);
            }
        });


        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TripDatabase tripDatabase = TripDatabase.get(getActivity());
        List<Trip> trips = tripDatabase.getTrip();

        if (mAdapter == null) {
            mAdapter = new TripAdapter(trips);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Trip mTrip;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mDestionationTextView;

        public TripHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mDestionationTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_destination_text_view);
        }

        @Override
        public void onClick(View v) {
            Intent intent = TripActivity.newIntent(getActivity(), mTrip.getId());
            startActivity(intent);
        }

        public void bindTrip(Trip trip) {
            mTrip = trip;
            mTitleTextView.setText(mTrip.getTitle());
            mDateTextView.setText(mTrip.getDate().toString());
            mDestionationTextView.setText(mTrip.getDestination());
        }

    }

    private class TripAdapter extends RecyclerView.Adapter<TripHolder> {

        private List<Trip> mTrips;

        public TripAdapter(List<Trip> trips) {
            mTrips = trips;
        }

        @Override
        public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_trip, parent, false);
            return new TripHolder(view);
        }

        @Override
        public void onBindViewHolder(TripHolder holder, int position) {
            Log.d("TTTest, adapterposition", position+"");
            Trip trip = mTrips.get(position);
            holder.bindTrip(trip);
        }

        @Override
        public int getItemCount() {
            Log.d("TTTest, adapter", mTrips.size()+"");
            return mTrips.size();
        }

        public void setTrips(List<Trip> trips) {
            mTrips = trips;
        }
    }

}
