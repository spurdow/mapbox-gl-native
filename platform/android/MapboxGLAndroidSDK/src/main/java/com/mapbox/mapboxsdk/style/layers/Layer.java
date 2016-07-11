package com.mapbox.mapboxsdk.style.layers;

/**
 * Base class for the different Layer types
 */
public abstract class Layer {

    private final String nativeMapId;

    protected Layer(String nativeMapId) {
        this.nativeMapId = nativeMapId;
    }

    public void setProperty(Property<?> property) {
        nativeSetProperty(nativeMapId, property);
    }


    private native void nativeSetProperty(String nativeMapId, Property<?> property);

}
