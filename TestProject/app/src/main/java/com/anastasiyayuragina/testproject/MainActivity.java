package com.anastasiyayuragina.testproject;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anastasiyayuragina.testproject.JsonCountriesClasses.Country;
import com.anastasiyayuragina.testproject.screen.country_list.CountryFragment;
import com.anastasiyayuragina.testproject.screen.map.MapFragment;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
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
    }

    enum FragmentType{
        COUNTRY_LIST,
        MAP
    }
    void replaceFragment(FragmentType type){
        Fragment fragment;
        FragmentManager manager = getSupportFragmentManager();
        fragment = manager.findFragmentByTag(type.name());

        if(fragment == null){
            fragment = createFragment(type);
        }

        FragmentTransaction transaction = manager.beginTransaction();
        if (type.equals(FragmentType.COUNTRY_LIST)) {
            transaction.replace(R.id.container, fragment, type.name()).commit();
        } else {
            transaction.replace(R.id.container, fragment, type.name()).addToBackStack(null).commit();
        }
    }

    private Fragment createFragment(FragmentType type) {
        switch (type){
            case COUNTRY_LIST:
                return CountryFragment.newInstance(1);
            case MAP:
                return MapFragment.newInstance();
            default:
                throw new IllegalArgumentException("Wrong fragment type");
        }
    }

    @Override
    public void onListFragmentInteraction(Country item) {
        Toast.makeText(this, "Click item:" + item.getName() + " " + item.getRegion().getValue(), Toast.LENGTH_SHORT).show();
        replaceFragment(FragmentType.MAP);
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
