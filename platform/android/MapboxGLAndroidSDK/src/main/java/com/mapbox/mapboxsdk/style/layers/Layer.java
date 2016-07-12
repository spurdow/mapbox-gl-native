package com.mapbox.mapboxsdk.style.layers;

import android.util.Log;

/**
 * Base class for the different Layer types
 */
public class Layer {

    private long nativePtr;
//    private final long nativeMapPtr;

    private Layer(long nativePtr /*, long nativeMapPtr*/) {
        Log.i(Layer.class.getSimpleName(), "CONSTRUCTED: " + nativePtr);
        this.nativePtr = nativePtr;
//        this.nativeMapPtr = nativeMapPtr;
    }

//    public Layer(long nativeMapId) {
//        this.nativeMapPtr = nativeMapId;
//    }

    public void setProperty(Property<?> property) {
//        nativeSetProperty(nativeMapPtr, property);
    }

    private native void nativeSetProperty(long nativeMapPtr, Object property);

}
