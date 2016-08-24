package com.anastasiyayuragina.testproject.screen.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.anastasiyayuragina.testproject.ItemForMap;
import com.anastasiyayuragina.testproject.R;
import com.anastasiyayuragina.testproject.ourDataBase.CountryComment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Locale;

/**
 * Created by anastasiya yuragina on 8/5/16.
 *
 */
public class MapFragment extends Fragment implements MapMvp.ViewMap{
    private static final String COUNTRY_NAME = "country_name";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ID_COUNTRY = "id_country";
    private  MapView mapView;
    private String countryName;
    private double latitude;
    private double longitude;
    private String idCountry;
    private TextView infoAboutCountry;
    private EditText comment;



    public static MapFragment newInstance(String countryName, String latitude, String longitude, String id) {

        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        args.putString(COUNTRY_NAME, countryName);
        args.putString(LATITUDE, latitude);
        args.putString(LONGITUDE, longitude);
        args.putString(ID_COUNTRY, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countryName = getArguments().getString(COUNTRY_NAME);
        idCountry = getArguments().getString(ID_COUNTRY);

        if (getArguments().getString(LATITUDE) != null && getArguments().getString(LONGITUDE) != null) {
            latitude = Double.parseDouble(getArguments().getString(LATITUDE));
            longitude = Double.parseDouble(getArguments().getString(LONGITUDE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        MapMvp.ModelMap modelMap = new MapModel();
        MapMvp.PresenterMap presenterMap;
        mapView = (MapView) view.findViewById(R.id.map);
        presenterMap = new MapPresenter(modelMap, this);
        infoAboutCountry = (TextView) view.findViewById(R.id.about_country);
        comment = (EditText) view.findViewById(R.id.editComment);

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
        CountryComment countryComment = new CountryComment();
        countryComment.setId_country(idCountry);
        countryComment.setComment(comment.getText().toString());
        countryComment.save();
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
            Locale locale;
            for (int i = 0; i < itemForMap.getInfoForMap().getLanguages().size(); i++) {

                if (i == 0) {
                    locale = new Locale(itemForMap.getInfoForMap().getLanguages().get(i));
                    builderLang.append(locale.getDisplayLanguage());
                } else if (i <= itemForMap.getInfoForMap().getLanguages().size()) {
                    locale = new Locale(itemForMap.getInfoForMap().getLanguages().get(i));
                    builderLang.append(", ").append(locale.getDisplayLanguage());
                }
            }

            final StringBuilder builder = new StringBuilder();
            builder.append(itemForMap.getInfoForMap().getName()).append(", ")
                    .append(itemForMap.getInfoForMap().getRegion()).append(", ")
                    .append(itemForMap.getInfoForMap().getSubregion()).append(", ")
                    .append("population: ").append(itemForMap.getInfoForMap().getPopulation()).append(", ")
                    .append("area: ").append(itemForMap.getInfoForMap().getArea()).append(", ")
                    .append("languages: ").append(builderLang.toString());

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(4));

                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            marker.setTitle(itemForMap.getInfoForMap().getCapital());

                            if (infoAboutCountry.getVisibility() == View.INVISIBLE) {
                                infoAboutCountry.setVisibility(View.VISIBLE);
                                infoAboutCountry.setText(builder.toString());

                            } else {
                                infoAboutCountry.setVisibility(View.INVISIBLE);
                                infoAboutCountry.setText("");
                            }

                            return false;
                        }
                    });
                }
            });
        }
    }
}