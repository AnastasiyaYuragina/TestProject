package com.anastasiyayuragina.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.screen.country_list.CountryFragment;
import java.util.ArrayList;
import java.util.List;

public class MyCountryRecyclerViewAdapter extends RecyclerView.Adapter<MyCountryRecyclerViewAdapter.ViewHolder> {

    private final List<Country> mValues = new ArrayList<>();
    private final CountryFragment.OnListFragmentInteractionListener mListener;
    private boolean loading;

    public MyCountryRecyclerViewAdapter(CountryFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void addItems(List<Country> items) {
        mValues.addAll(items);
        loading = false;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_country, parent, false);
        }else if(viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_item, parent, false);
        }else {
            throw new IllegalArgumentException("Wrong view type");
        }


        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mValues.size()){
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position) == 1) {
            return;
        }

        Country viewModel = mValues.get(position);


        if (viewModel.getComment() != null && !viewModel.getComment().isEmpty()) {
            holder.mComment.setText("Comment: " + viewModel.getComment().toString());
        }

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
        int size = mValues.size();
        if(loading){
            return size + 1;
        }
        return size;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Country mItem;
        public final TextView mComment;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.county_name);
            mContentView = (TextView) view.findViewById(R.id.country_region);
            mComment = (TextView) view.findViewById(R.id.show_comment);
        }

        @Override
        public java.lang.String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
