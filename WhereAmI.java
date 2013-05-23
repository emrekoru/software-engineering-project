package com.paad.whereami;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class WhereAmI extends MapActivity {
  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }
    
  MapController mapController;
  MyPositionOverlay positionOverlay;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    MapView myMapView = (MapView)findViewById(R.id.myMapView);
    mapController = myMapView.getController();
    
    myMapView.setSatellite(true);
    myMapView.setStreetView(true);
    myMapView.displayZoomControls(false);

    mapController.setZoom(17);

    // Add the MyPositionOverlay
    positionOverlay = new MyPositionOverlay();
    List<Overlay> overlays = myMapView.getOverlays();
    overlays.add(positionOverlay);

    LocationManager locationManager;
    String context = Context.LOCATION_SERVICE;
    locationManager = (LocationManager)getSystemService(context);

    Criteria criteria = new Criteria();
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setAltitudeRequired(false);
    criteria.setBearingRequired(false);
    criteria.setCostAllowed(true);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
    String provider = locationManager.getBestProvider(criteria, true);

    Location location = locationManager.getLastKnownLocation(provider);

    updateWithNewLocation(location);

    locationManager.requestLocationUpdates(provider, 2000, 10,   
                                           locationListener);
  }
