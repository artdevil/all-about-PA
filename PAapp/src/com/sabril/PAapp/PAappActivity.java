package com.sabril.PAapp;

import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class PAappActivity extends MapActivity {
	MapView mapView; //inisialisasi map awal
	GeoPoint pointLocation; //inisialisasi titik lokasi
	MapController mapController; //inisialisasi controller google maps
	boolean status;
	private LocationManager locationManager;
	private LocationListener locationListener;

	class MapOverlay extends com.google.android.maps.Overlay{
		@Override
		public boolean draw(Canvas canvas,MapView mapView, boolean shadow, long when){
			super.draw(canvas, mapView, shadow);
				Point screenPts = new Point();
				mapView.getProjection().toPixels(pointLocation, screenPts);
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.user);
				canvas.drawBitmap(bmp, screenPts.x,screenPts.y-50,null);
				return true;
		}
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addPuskesmas(new Puskesmas("bojongsoang","67.111","101"));
        setIntoMap();  
        lokasiAwal();
        
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.search_user:
			locationDetection();
			return false;
		}
		return false;
	}
    
    public void setIntoMap() {
		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		mapView.setStreetView(true);
		mapView.setSatellite(false);
		mapView.setTraffic(true);
	}
    
    public void locationDetection() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener(){
			public void onLocationChanged(Location loc){
	    		if(loc != null){
	    			Toast.makeText(getBaseContext(), "location changed : lat:"+ loc.getLatitude() + " Lng:"+ loc.getLongitude(), Toast.LENGTH_SHORT).show();
	    		}
	    		pointLocation = new GeoPoint(
	        			(int) (loc.getLatitude() * 1E6),
	        			(int) (loc.getLongitude() * 1E6)
	        			);
	        	mapControllerSetting(pointLocation,true);
	    	}
	    	
	    	public void onProviderDisabled(String provicer){
	    		
	    	}
	    	
	    	public void onProviderEnabled(String provider){
	    		
	    	}
	    	
	    	public void onStatusChanged(String provider, int status, Bundle extras){
	    		
	    	}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300000, 100, locationListener);
	}
    
    
    public void lokasiAwal(){
    	String coordinates[] = {"-6.914051","107.609505"};
    	double latitude = Double.parseDouble(coordinates[0]);
    	double longnitude = Double.parseDouble(coordinates[1]);
    	pointLocation = new GeoPoint(
    			(int) (latitude * 1E6),
    			(int) (longnitude * 1E6)
    			);
    	mapControllerSetting(pointLocation,false);
    }
    
    public void mapControllerSetting(GeoPoint pointposisi,boolean status) {		
    	mapController = mapView.getController();
    	mapController.setZoom(16);
    	mapController.animateTo(pointposisi);
    	if(status){
    		MapOverlay mapOverlay = new MapOverlay();
        	List<Overlay> listOfOverlays = mapView.getOverlays();
        	listOfOverlays.clear();
        	listOfOverlays.add(mapOverlay);
        	mapView.invalidate();
    	}
    	
	}
       
    @Override
    protected boolean isRouteDisplayed(){
    	return false;
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
		mapControllerSetting(pointLocation,status);
    }
}