package com.anastasiyayuragina.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private final List<CountryViewModel> mValues;
    private final CountryFragment.OnListFragmentInteractionListener mListener;

    public MyCountryRecyclerViewAdapter(List<CountryViewModel> items, CountryFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CountryViewModel viewModel = mValues.get(position);
        holder.mItem = viewModel;
        holder.mIdView.setText(viewModel.name);
        holder.mContentView.setText(viewModel.region);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public CountryViewModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.county_name);
            mContentView = (TextView) view.findViewById(R.id.country_region);
        }

        @Override
        public java.lang.String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
