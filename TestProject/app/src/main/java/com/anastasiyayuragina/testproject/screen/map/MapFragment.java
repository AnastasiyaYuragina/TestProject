package com.anastasiyayuragina.testproject.screen.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by anastasiyayuragina on 8/5/16.
 */
public class MapFragment extends Fragment{


    public static MapFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
}
