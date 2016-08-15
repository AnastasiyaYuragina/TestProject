package com.anastasiyayuragina.testproject;

import android.net.Uri;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.screen.country_list.CountryFragment;
import com.anastasiyayuragina.testproject.screen.map.MapFragment;
import com.anastasiyayuragina.testproject.screen.map.MapMvp;
import com.anastasiyayuragina.testproject.screen.map.MapPresenter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private FragmentManager manager;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(FragmentType.COUNTRY_LIST);

        getApplication();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.anastasiyayuragina.testproject/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.anastasiyayuragina.testproject/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    enum FragmentType {
        COUNTRY_LIST,
        MAP
    }

    void replaceFragment(FragmentType type) {
        Fragment fragment;
        manager = getSupportFragmentManager();
        fragment = manager.findFragmentByTag(type.name());

        if (fragment == null) {
//            fragment = createFragment(type);
            fragment = CountryFragment.newInstance(1);
        }

        FragmentTransaction transaction = manager.beginTransaction();
        if (type.equals(FragmentType.COUNTRY_LIST)) {
            transaction.replace(R.id.container, fragment, type.name()).commit();
        }
//        else {
//            transaction.replace(R.id.container, fragment, type.name()).addToBackStack(null).commit();
//        }
    }

//    private Fragment createFragment(FragmentType type) {
//        switch (type) {
//            case COUNTRY_LIST:
//                return CountryFragment.newInstance(1);
//            case MAP:
//                return MapFragment.newInstance();
//            default:
//                throw new IllegalArgumentException("Wrong fragment type");
//        }
//    }

    void showCountryMap(String countryName, String latitude, String longitude){
        Fragment mapFragment = MapFragment.newInstance(countryName, latitude, longitude);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, mapFragment, FragmentType.MAP.name()).addToBackStack(null).commit();
    }

    @Override
    public void onListFragmentInteraction(Country item) {
//        Toast.makeText(this, "Click item:" + item.getName() + " " + item.getRegion().getValue(), Toast.LENGTH_SHORT).show();
        showCountryMap(item.getName(), item.getLatitude(), item.getLongitude());
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
