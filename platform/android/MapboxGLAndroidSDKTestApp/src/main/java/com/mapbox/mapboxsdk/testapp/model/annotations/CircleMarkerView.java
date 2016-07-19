package com.mapbox.mapboxsdk.testapp.model.annotations;

import com.mapbox.mapboxsdk.annotations.BaseMarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.MarkerView;

public class CircleMarkerView extends MarkerView {

    private int color;
    private float radius;


    public CircleMarkerView(BaseMarkerViewOptions baseMarkerViewOptions, int color , float radius) {
        super(baseMarkerViewOptions);
        this.color = color;
        this.radius = radius;

    }

    public int getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }
}