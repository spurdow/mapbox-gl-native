package com.mapbox.mapboxsdk.style.layers;

import android.util.Log;

/**
 * Base class for the different Layer types
 */
public class Layer {

    private long nativePtr;
//    private final long nativeMapPtr;

    public Layer(long nativePtr) {
        Log.i(Layer.class.getSimpleName(), "Native pointer constructor: " + nativePtr);
        this.nativePtr = nativePtr;
    }

    public Layer() {
        Log.i(Layer.class.getSimpleName(), "Default constructor");
        initialize();
    }

    protected native void initialize();

    @Override
    protected native void finalize() throws Throwable;

    public void setProperty(Property<?> property) {
//        nativeSetProperty(nativeMapPtr, property);
    }

    protected native void nativeSetProperty(long nativeMapPtr, Object property);

}
