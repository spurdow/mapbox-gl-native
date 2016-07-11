package com.mapbox.mapboxsdk.style.layers;

/**
 * Base class for the different Layer types
 */
public class Layer {

    private final long nativeMapPtr;

    public Layer(long nativeMapId) {
        this.nativeMapPtr = nativeMapId;
    }

    public void setProperty(Property<?> property) {
        nativeSetProperty(nativeMapPtr, property);
    }


    private native void nativeSetProperty(long nativeMapPtr, Property<?> property);

}
