package com.anastasiyayuragina.testproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CountryFragment extends Fragment  {

    // TODO: Customize parameter argument names
    private static final java.lang.String ARG_COLUMN_COUNT = "column-count";

    private static final java.lang.String TAG = "MyLogs";
    private static final int PAGE_INFO = 0;
    private static final int COUNTRIES_ARRAY = 1;

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private List<CountryViewModel> countryList = new ArrayList<>();
    private MyCountryRecyclerViewAdapter adapter;

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

        new Parse().execute();

        adapter = new MyCountryRecyclerViewAdapter(countryList, mListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            recyclerView.setAdapter(new MyCountryRecyclerViewAdapter(countryList, mListener));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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
        void onListFragmentInteraction(CountryViewModel item);
    }

    private class Parse extends AsyncTask<Void, Void, java.lang.String> {

        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        java.lang.String resultJson = "";

        @Override
        protected java.lang.String doInBackground(Void... voids) {

            try {
                URL url = new URL("http://api.worldbank.org/country?per_page=10&format=json&page=1");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                java.lang.String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        @Override
        protected void onPostExecute(java.lang.String s) {
            super.onPostExecute(s);

//            Log.d(TAG, s);

            ObjectMapper mapper = new ObjectMapper();
            JSONArray jsonArray;
            Country country;

            try {
                jsonArray = new JSONArray(s);
                JSONArray countries = jsonArray.getJSONArray(COUNTRIES_ARRAY);
                JSONObject countryObj;

                for (int i = 0; i < countries.length(); i++) {
                    countryObj = countries.getJSONObject(i);

                    country = mapper.readValue(countryObj.toString(), Country.class);

                    CountryViewModel viewModel = new CountryViewModel();

                    viewModel.name = country.getName();
                    viewModel.region = country.getRegion().getValue();

                    countryList.add(viewModel);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, country.toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


//            JSONArray jsonArray = null;
//
//            try {
//                jsonArray = new JSONArray(s);
//                JSONArray countries = jsonArray.getJSONArray(COUNTRIES_ARRAY);
//
//                for (int i = 0; i < countries.length(); i++) {
//                    JSONObject country = countries.getJSONObject(i);
//                    JSONObject region = country.getJSONObject("region");
//                    CountryViewModel viewModel = new CountryViewModel();
//
//                    viewModel.name = country.getString("name");
//                    viewModel.region = region.getString("value");
//
//                    countryList.add(viewModel);
//                }
//            }catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }
}
