package com.mapbox.mapboxsdk.style.layers;

import android.util.Log;

/**
 * Base class for the different Layer types
 */
public class Layer {

    private long nativePtr;
    private boolean attached;

    public Layer(long nativePtr) {
        Log.i(Layer.class.getSimpleName(), "Native pointer constructor: " + nativePtr);
        this.nativePtr = nativePtr;
        this.attached = true;
    }

    public Layer() {
        Log.i(Layer.class.getSimpleName(), "Default constructor");
        this.attached = false;
        initialize();
    }

    protected native void initialize();

    @Override
    protected native void finalize() throws Throwable;

    public void setLayoutProperty(Property<?> property) {
        if (attached) {
            nativeSetLayoutProperty(property.name, property.value);
        } else {
            //TODO Add property to list to support addLayer();
        }
    }

    public void setPaintProperty(Property<?> property) {
        if (attached) {
            nativeSetPaintProperty(property.name, property.value);
        } else {
            //TODO Add property to list to support addLayer();
        }
    }

    public String getId() {
        return nativeGetId();
    }

    protected native String nativeGetId();

    protected native void nativeSetLayoutProperty(String name, Object value);

    protected native void nativeSetPaintProperty(String name, Object value);

    @Override
    public String toString() {
        return "Layer: " + getId();
    }
}
