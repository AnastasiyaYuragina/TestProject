package com.anastasiyayuragina.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anastasiyayuragina.testproject.JsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.screen.country_list.CountryFragment;

import java.util.ArrayList;
import java.util.List;

public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private final List<Country> mValues = new ArrayList<>();
    private final CountryFragment.OnListFragmentInteractionListener mListener;

    public MyCountryRecyclerViewAdapter(CountryFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void addItems(List<Country> items) {
        mValues.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Country viewModel = mValues.get(position);
        holder.mItem = viewModel;
        holder.mIdView.setText("Country: " + viewModel.getName());
        holder.mContentView.setText("Region: " + viewModel.getRegion().getValue());

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
        public Country mItem;

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
