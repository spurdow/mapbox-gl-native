package com.mapbox.mapboxsdk.style.layers;

import android.util.Log;

/**
 * Base class for the different Layer types
 */
public class Layer {

    private long nativePtr;
    private boolean attached;
//    private final long nativeMapPtr;

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

    public void setProperty(Property<?> property) {
        if (attached) {
            nativeSetProperty(0l /*XXX*/, property);
        } else {
            //TODO Add property to list to support addLayer();
        }
    }

    //TODO: remove map pointer
    protected native void nativeSetProperty(long nativeMapPtr, Property property);

}
