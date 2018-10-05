package com.theQuake.quakeInfo;

import java.util.ArrayList;
import java.util.List;

public final class Utils {
    public static List<Earthquake> filterByRegion(List<Earthquake> earthquakes, String region) {
        List<Earthquake> filteredByRegionEarthquake = new ArrayList<>();

        for(int i = 0; i < earthquakes.size(); i++){
            if(earthquakes.get(i).getLocation().contains(region)){
                filteredByRegionEarthquake.add(earthquakes.get(i));
            }
        }

        return filteredByRegionEarthquake;
    }
}
