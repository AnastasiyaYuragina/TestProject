package com.anastasiyayuragina.testproject.screen.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anastasiyayuragina.testproject.ItemForMap;
import com.anastasiyayuragina.testproject.R;
import com.anastasiyayuragina.testproject.jsonCountriesClasses.Country;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;

/**
 * Created by anastasiyayuragina on 8/5/16.
 */
public class MapFragment extends Fragment implements MapMvp.ViewMap{
    private static final String COUNTRY_NAME = "country_name";
    private static final String LANTITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private  MapView mapView;
    private MapMvp.PresenterMap presenterMap;
    private String countryName;
    private double latitude;
    private double longitude;
    private TextView textView;

    public static MapFragment newInstance(String countryName, String latitude, String longitude) {
        
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        args.putString(COUNTRY_NAME, countryName);
        args.putString(LANTITUDE, latitude);
        args.putString(LONGITUDE, longitude);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countryName = getArguments().getString(COUNTRY_NAME);
        if (getArguments().getString(LANTITUDE).isEmpty() || getArguments().getString(LONGITUDE).isEmpty()) {

        } else {
            latitude = Double.parseDouble(getArguments().getString(LANTITUDE));
            longitude = Double.parseDouble(getArguments().getString(LONGITUDE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        MapMvp.ModelMap modelMap = new MapModel();
        mapView = (MapView) view.findViewById(R.id.map);
        presenterMap = new MapPresenter(modelMap, this);
        textView = (TextView) view.findViewById(R.id.about_country);

        presenterMap.setCountryName(countryName);
        presenterMap.loadData();

        mapView.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void setMapMarker(final ItemForMap itemForMap) {
        if (itemForMap == null) {
            Toast.makeText(mapView.getContext(), "Unknown coordinates", Toast.LENGTH_SHORT).show();

        } else {
            if (latitude == 0 || longitude == 0) {
                latitude = itemForMap.getInfoForMap().getLatlng().get(0);
                longitude = itemForMap.getInfoForMap().getLatlng().get(1);
            }

            StringBuilder builderLang = new StringBuilder();
            for (int i = 0; i < itemForMap.getInfoForMap().getLanguages().size(); i++) {

                if (i == itemForMap.getInfoForMap().getLanguages().size()) {
                    builderLang.append(itemForMap.getInfoForMap().getLanguages().get(i));
                } else if (i >= 0 && i < itemForMap.getInfoForMap().getLanguages().size()) {
                    builderLang.append(itemForMap.getInfoForMap().getLanguages().get(i) + ", ");
                }

            }

            final StringBuilder builder = new StringBuilder();
            builder.append(itemForMap.getInfoForMap().getName() + ", ").append(itemForMap.getInfoForMap().getRegion() + ", ")
                    .append(itemForMap.getInfoForMap().getSubregion() + ", ")
                    .append("population: " + itemForMap.getInfoForMap().getPopulation() + ", ")
                    .append("area: " + itemForMap.getInfoForMap().getArea() + ", ").append("languages: " + builderLang.toString());

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));

                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            marker.setTitle(itemForMap.getInfoForMap().getCapital());

                            if (textView.getVisibility() == View.INVISIBLE) {
                                textView.setVisibility(View.VISIBLE);
                                textView.setText(builder.toString());
                            } else {
                                textView.setVisibility(View.INVISIBLE);
                                textView.setText("");
                            }

                            return false;
                        }
                    });
                }
            });
        }
    }
}