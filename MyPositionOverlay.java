package com.paad.whereami;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyPositionOverlay extends Overlay {

  private final int mRadius = 5;

  Location location;
 
  public Location getLocation() {
    return location;
  }
  public void setLocation(Location location) {
    this.location = location;
  }
  
  @Override
  public boolean onTap(GeoPoint point, MapView mapView) {
    return false;
  }
  
  @Override
  public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    Projection projection = mapView.getProjection();

    if (shadow == false) {
      // Get the current location    
      Double latitude = location.getLatitude()*1E6;
      Double longitude = location.getLongitude()*1E6;
      GeoPoint geoPoint; 
      geoPoint = new 
        GeoPoint(latitude.intValue(),longitude.intValue());
        
