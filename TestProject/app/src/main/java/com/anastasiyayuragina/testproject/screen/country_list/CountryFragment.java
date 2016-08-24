package com.anastasiyayuragina.testproject.screen.country_list;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anastasiyayuragina.testproject.EndlessRecyclerOnScrollListener;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.MyCountryRecyclerViewAdapter;
import com.anastasiyayuragina.testproject.R;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CountryFragment extends Fragment implements CountriesMvp.View {

    // TODO: Customize parameter argument names
    private static final java.lang.String ARG_COLUMN_COUNT = "column-count";

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private MyCountryRecyclerViewAdapter adapter;
    private CountriesMvp.Presenter presenter;
    private ProgressDialog progressDialog;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CountryFragment() {
    }

    // TODO: Customize parameter initialization
    public static CountryFragment newInstance(int columnCount) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        final CountriesMvp.Model model = new CountriesModel();
        presenter = new CountriesPresenter(model, this);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyCountryRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);

            progressDialog = new ProgressDialog(context);
            if (!presenter.isDataLoaded()) {
                progressDialog.setProgressStyle(R.layout.progress_bar_item);
                progressDialog.show();
                presenter.loadData();
            }

            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
                @Override
                public void onLoadMore() {
                    if (presenter.isDataLoaded()) {
                        progressDialog.dismiss();
                    }
                    presenter.loadData();
                }
            });
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setData(List<Country> countryList) {
        adapter.addItems(countryList);
    }

    @Override
    public void showLoadMore() {
        adapter.setLoading(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Country item);
    }
}
