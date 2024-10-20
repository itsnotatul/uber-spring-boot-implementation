package com.example.UberDemoProject.utils;


// this util class will create the boundary of given radius around given longitude and latitude and then give the min,max longitude and latitude pairs of this boundary
public class LocationUtil {

    private static final double EARTH_RADIUS_KM = 6371.0; // Radius of Earth in kilometers

    public static double[] calculateBoundary(Double latitude, Double longitude, double radius) {
        double latChange = radius / EARTH_RADIUS_KM; // Radius in radians
        double lonChange = radius / (EARTH_RADIUS_KM * Math.cos(Math.toRadians(latitude))); // Adjusted for longitude

        double minLat = latitude - Math.toDegrees(latChange);
        double maxLat = latitude + Math.toDegrees(latChange);
        double minLng = longitude - Math.toDegrees(lonChange);
        double maxLng = longitude + Math.toDegrees(lonChange);

        return new double[]{minLat, maxLat, minLng, maxLng};
    }
}

