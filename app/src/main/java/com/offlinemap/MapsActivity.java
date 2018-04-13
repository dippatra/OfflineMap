package com.offlinemap;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> latLngs=new ArrayList<>();
    private Marker selectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        populateLatlng();
        Button start=(Button)findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                loadOfflineMap(mMap);
                LatLng pheonixmall = new LatLng(12.990591256359695,80.21688938140869);

                // Add a marker in Sydney and move the camera
                //LatLng sydney = new LatLng(18.994926, 151);
                mMap.addMarker(new MarkerOptions().position(pheonixmall).title("Marker in pheonix"));
                //animator.startAnimation(true);
                pathPreview();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
       mMap.setBuildingsEnabled(false);
       mMap.setIndoorEnabled(false);

        LatLng pheonixmall = new LatLng(12.990591256359695,80.21688938140869);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(18.994926, 151);
        mMap.addMarker(new MarkerOptions().position(pheonixmall).title("Marker in pheonix"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pheonixmall, 19.01f));
        loadOfflineMap(mMap);









    }

    private void loadOfflineMap(GoogleMap map){
        try{
            TileOverlayOptions opts = new TileOverlayOptions();
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "phoenix chennai_Level_1.mbtiles";
            String path  = baseDir + "/mbtile/" + fileName;
            MapBoxOfflineTileProvider provider = new MapBoxOfflineTileProvider(path);
            opts.tileProvider(provider);
            TileOverlay overlay = map.addTileOverlay(opts);
            overlay.setZIndex(-1);
            //provider.close();
            //openSettingScreen();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void openSettingScreen(){
        try{
            final Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivityForResult(i,150);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void populateLatlng(){
        try {
            latLngs.add(new LatLng(12.990591256359695,80.21688938140869));
            latLngs.add(new LatLng(12.990593869922897,80.21696448326111));
            latLngs.add(new LatLng(12.99079250064588,80.2170181274414));
            latLngs.add(new LatLng(12.990928405785878,80.21704763174056));
            latLngs.add(new LatLng(12.991009426122396,80.21700739860533));
            latLngs.add(new LatLng(12.991066924409717,80.21692961454391));
            latLngs.add(new LatLng(12.991249873417129,80.21685987710953));
            latLngs.add(new LatLng(12.991435435844137,80.21681696176529));
            latLngs.add(new LatLng(12.991602703265148,80.21680623292923));
            latLngs.add(new LatLng(12.991759516370026,80.21682500839232));
            latLngs.add(new LatLng(12.991965986807092,80.21690279245377));
            latLngs.add(new LatLng(12.992080982925463,80.21696448326111));
            latLngs.add(new LatLng(12.992224727998531,80.21706908941269));
            latLngs.add(new LatLng(12.992313588547539,80.21718174219131));
            latLngs.add(new LatLng(12.992378927166238,80.21719247102737));



        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void changeCameraPosition(CameraPosition cameraPosition, boolean animate) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        if (animate) {
            mMap.animateCamera(cameraUpdate);
        } else {
            mMap.moveCamera(cameraUpdate);
        }

    }
    private Handler mHandler=new Handler();
    private Animator animator = new Animator();

    private void navigateToPoint(LatLng latLng, boolean animate) {
        CameraPosition position = new CameraPosition.Builder().target(latLng).build();
        changeCameraPosition(position, animate);
    }
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(15);
    //
// Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
    public class Animator implements Runnable {

        private static final int ANIMATE_SPEEED = 1100;
        private static final int ANIMATE_SPEEED_TURN = 1000;
        private static final int BEARING_OFFSET = 20;

        private final Interpolator interpolator = new LinearInterpolator();

        int currentIndex = 0;

        float tilt = 55;
        float zoom = 15.5f;
        boolean upward=true;

        long start = SystemClock.uptimeMillis();

        LatLng endLatLng = null;
        LatLng beginLatLng = null;

        boolean showPolyline = false;

        private Marker trackingMarker;

        public void reset() {
            start = SystemClock.uptimeMillis();
            currentIndex = 0;
            endLatLng = getEndLatLng();
            beginLatLng = getBeginLatLng();


        }

        public void stop() {
            trackingMarker.remove();
            mHandler.removeCallbacks(animator);

        }

        private float bearingFinal;

        public void initialize(boolean showPolyLine) {
            reset();
            this.showPolyline = showPolyLine;

           // highLightMarker(0);

            if (showPolyLine) {
                polyLine = initializePolyLine();
            }

            // We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
            LatLng markerPos = latLngs.get(0);
            //LatLng secondPos = latLngs.get(1);
            LatLng secondPos = latLngs.get(latLngs.size()-1);

            setupCameraPositionForMovement(markerPos, secondPos);

        }

        private void setupCameraPositionForMovement(LatLng markerPos,
                                                    LatLng secondPos) {

            //float bearing = bearingBetweenLatLngs(markerPos,latLngs.get(latLngs.size()-1));
            //float bearing = bearingBetweenLatLngs(markerPos,secondPos);
             bearingFinal = bearingBetweenLatLngs(markerPos,secondPos);

            trackingMarker = mMap.addMarker(new MarkerOptions().position(markerPos)
                    .title("title")
                    .snippet("snippet"));

            CameraPosition cameraPosition =
                    new CameraPosition.Builder()
                            .target(markerPos)
                            .bearing(bearingFinal )
                            .tilt(90)
                            .zoom(mMap.getCameraPosition().zoom >=22 ? mMap.getCameraPosition().zoom : 22)
                            .build();

            mMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    ANIMATE_SPEEED_TURN,
                    new GoogleMap.CancelableCallback() {

                        @Override
                        public void onFinish() {
                            System.out.println("finished camera");
                            animator.reset();
                            Handler handler = new Handler();
                            handler.post(animator);
                        }

                        @Override
                        public void onCancel() {
                            System.out.println("cancelling camera");
                        }
                    }
            );
        }

        private Polyline polyLine;
        private PolylineOptions rectOptions = new PolylineOptions();


        private Polyline initializePolyLine() {
            //polyLinePoints = new ArrayList<LatLng>();
            rectOptions.add(latLngs.get(0));
            rectOptions.width(25);
            rectOptions.jointType(JointType.ROUND);
            return mMap.addPolyline(rectOptions);
        }

        /**
         * Add the marker to the polyline.
         */
        private void updatePolyLine(LatLng latLng) {
            List<LatLng> points = polyLine.getPoints();
            points.add(latLng);
            polyLine.setPattern(PATTERN_POLYLINE_DOTTED);
            polyLine.setJointType(JointType.ROUND);
            polyLine.setStartCap(new RoundCap());
            polyLine.setPoints(points);

        }


        public void stopAnimation() {
            animator.stop();
        }

        public void startAnimation(boolean showPolyLine) {
            if (latLngs.size()>2) {
                animator.initialize(showPolyLine);
            }
        }


        @Override
        public void run() {

            long elapsed = SystemClock.uptimeMillis() - start;
            double t = interpolator.getInterpolation((float)elapsed/ANIMATE_SPEEED);

//			LatLng endLatLng = getEndLatLng();
//			LatLng beginLatLng = getBeginLatLng();

            double lat = t * endLatLng.latitude + (1-t) * beginLatLng.latitude;
            double lng = t * endLatLng.longitude + (1-t) * beginLatLng.longitude;
            LatLng newPosition = new LatLng(lat, lng);

            trackingMarker.setPosition(newPosition);

            if (showPolyline) {
                updatePolyLine(newPosition);
            }

            // It's not possible to move the marker + center it through a cameraposition update while another camerapostioning was already happening.
            //navigateToPoint(newPosition,tilt,bearing,currentZoom,false);
            //navigateToPoint(newPosition,false);

            if (t< 1) {
                mHandler.post(this);
            } else {

                System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + latLngs.size());
                // imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
                if (currentIndex<latLngs.size()-2) {

                    currentIndex++;

                    endLatLng = getEndLatLng();
                    beginLatLng = getBeginLatLng();


                    start = SystemClock.uptimeMillis();

                    LatLng begin = getBeginLatLng();
                    LatLng end = getEndLatLng();

                    float bearingL = bearingBetweenLatLngs(begin, end);

                    //highLightMarker(currentIndex);

                    CameraPosition cameraPosition =
                            new CameraPosition.Builder()
                                    .target(begin) // changed this...
                                    .bearing(bearingFinal )
                                    .tilt(tilt)
                                    .zoom(mMap.getCameraPosition().zoom)
                                    .build();


                    mMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(cameraPosition),
                            ANIMATE_SPEEED_TURN,
                            null
                    );

                    start = SystemClock.uptimeMillis();
                    mHandler.post(animator);

                } else {
                    currentIndex++;
                    //highLightMarker(currentIndex);
                    stopAnimation();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            /*CameraPosition cameraPosition =
                                    new CameraPosition.Builder()
                                            .target(latLngBounds.getCenter())
                                            .tilt(mMap.getCameraPosition().tilt)
                                            .zoom( getBoundsZoomLevel(northEast,southWest,getScreenWidth(),getScreenHeight()))
                                            .build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngBounds.getCenter(), getBoundsZoomLevel(northEast,southWest,getScreenWidth(),getScreenHeight())));
                        }
                    },250);



                }

            }
        }




        private LatLng getEndLatLng() {
            return latLngs.get(currentIndex+1);
        }

        private LatLng getBeginLatLng() {
            return latLngs.get(currentIndex);
        }


    };



    private Location convertLatLngToLocation(LatLng latLng) {
        Location loc = new Location("someLoc");
        loc.setLatitude(latLng.latitude);
        loc.setLongitude(latLng.longitude);
        return loc;
    }

    private float bearingBetweenLatLngs(LatLng begin,LatLng end) {
        Location beginL= convertLatLngToLocation(begin);
        Location endL= convertLatLngToLocation(end);

        return beginL.bearingTo(endL);
    }

    private void pathPreview(){
        LatLngBounds bounds;
        CameraUpdate cameraUpdate;
        try{
            bounds = new LatLngBounds(latLngs.get(0),latLngs.get(0));
            for (LatLng latLng : latLngs) {
                bounds = bounds.including(latLng);
            }

            cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 200);
            mMap.animateCamera(cameraUpdate, 500, new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    final CameraPosition cameraPosition;
                    CameraPosition.Builder builder;
                    Location start, end;
                    float bearing;
                    LatLngBounds bounds;
                    try{
                        bounds = new LatLngBounds(latLngs.get(0),latLngs.get(0));
                        for (LatLng latLng : latLngs) {
                            bounds = bounds.including(latLng);
                        }
                        start = convertLatLngToLocation(latLngs.get(0));
                        end = convertLatLngToLocation(latLngs.get(latLngs.size()-1));
                        bearing = start.bearingTo(end);
                        builder = new CameraPosition.Builder();
                        builder.target(bounds.getCenter());
                        builder.bearing(bearing);
                        builder.zoom(mMap.getCameraPosition().zoom);
                        cameraPosition = builder.build();

                        final Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, new GoogleMap.CancelableCallback() {
                                    @Override
                                    public void onFinish() {
                                        southWest=mMap.getProjection().getVisibleRegion().latLngBounds.southwest;
                                        northEast=mMap.getProjection().getVisibleRegion().latLngBounds.northeast;
                                        latLngBounds=mMap.getProjection().getVisibleRegion().latLngBounds;
                                        //animator.startAnimation(true);
                                        MapAnimator.getInstance().animateRoute(mMap, latLngs);


                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                        },1000);

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onCancel() {

                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void endPreview(){
        LatLngBounds bounds;
        CameraUpdate cameraUpdate;
        try{
            bounds = new LatLngBounds(latLngs.get(0),latLngs.get(0));
            for (LatLng latLng : latLngs) {
                bounds = bounds.including(latLng);
            }

            cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 200);
            mMap.animateCamera(cameraUpdate, 500, new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    final CameraPosition cameraPosition;
                    CameraPosition.Builder builder;
                    Location start, end;
                    float bearing;
                    LatLngBounds bounds;
                    try{
                        bounds = new LatLngBounds(latLngs.get(0),latLngs.get(0));
                        for (LatLng latLng : latLngs) {
                            bounds = bounds.including(latLng);
                        }
                        start = convertLatLngToLocation(latLngs.get(0));
                        end = convertLatLngToLocation(latLngs.get(latLngs.size()-1));
                        bearing = start.bearingTo(end);
                        builder = new CameraPosition.Builder();
                        builder.target(bounds.getCenter());
                        builder.bearing(bearing);
                        builder.zoom(mMap.getCameraPosition().zoom);
                        cameraPosition = builder.build();
                        final Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1500, new GoogleMap.CancelableCallback() {
                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                        },250);

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onCancel() {

                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    final static int GLOBE_WIDTH = 256; // a constant in Google's map projection
    final static int ZOOM_MAX = 21;

    public static int getBoundsZoomLevel(LatLng northeast,LatLng southwest,
                                         int width, int height) {
        double latFraction = (latRad(northeast.latitude) - latRad(southwest.latitude)) / Math.PI;
        double lngDiff = northeast.longitude - southwest.longitude;
        double lngFraction = ((lngDiff < 0) ? (lngDiff + 360) : lngDiff) / 360;
        double latZoom = zoom(height, GLOBE_WIDTH, latFraction);
        double lngZoom = zoom(width, GLOBE_WIDTH, lngFraction);
        double zoom = Math.min(Math.min(latZoom, lngZoom),ZOOM_MAX);
        return (int)(zoom);
    }
    private static double latRad(double lat) {
        double sin = Math.sin(lat * Math.PI / 180);
        double radX2 = Math.log((1 + sin) / (1 - sin)) / 2;
        return Math.max(Math.min(radX2, Math.PI), -Math.PI) / 2;
    }
    private static double zoom(double mapPx, double worldPx, double fraction) {
        final double LN2 = .693147180559945309417;
        return (Math.log(mapPx / worldPx / fraction) / LN2);
    }
    private LatLng northEast,southWest;
    LatLngBounds  latLngBounds;
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
















}
