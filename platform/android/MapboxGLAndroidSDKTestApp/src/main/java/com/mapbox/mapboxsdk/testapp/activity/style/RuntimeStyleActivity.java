package com.mapbox.mapboxsdk.testapp.activity.style;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.testapp.R;

import static com.mapbox.mapboxsdk.style.layers.Property.*;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.*;

/**
 * Sample Activity to show a typical location picker use case
 */
public class RuntimeStyleActivity extends AppCompatActivity {
    private static final String TAG = RuntimeStyleActivity.class.getSimpleName();

    private MapView mapView;
    private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_style);

        setupActionBar();

        //Initialize map as normal
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap map) {
                //Store for later
                mapboxMap = map;

                //Center and Zoom (Amsterdam, zoomed to streets)
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.379189, 4.899431), 14));

                //TODO: Add layer
                Layer custom = new Layer();
                Log.i(TAG, "Created the layer");
                custom = null;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_runtime_style, menu);
        return true;
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_water_color:
                setWaterColor();
                return true;
            case R.id.action_background_opacity:
                setBackgroundOpacity();
                return true;
            case R.id.action_road_avoid_edges:
                setRoadSymbolPlacement();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setRoadSymbolPlacement() {
        String[] roadLayers = new String[]{"road-label-small", "road-label-medium", "road-label-large"};
        for (String roadLayer : roadLayers) {
            Layer layer = mapboxMap.getLayer(roadLayer);
            if (layer != null) {
                layer.setLayoutProperty(symbolPlacement(SYMBOL_PLACEMENT_POINT));
            }
        }
    }

    private void setBackgroundOpacity() {
        //Get layers to manipulate
        Layer background = mapboxMap.getLayer("background");
        background.setPaintProperty(backgroundOpacity(0f));
    }

    private void setWaterColor() {
        Layer water = mapboxMap.getLayer("water");
        if (water != null) {
            water.setPaintProperty(fillColor(Color.RED));
        } else {
            Toast.makeText(RuntimeStyleActivity.this, "No water layer in this style", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
