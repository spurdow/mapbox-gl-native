package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by ivo on 14/07/16.
 */
public class Value<T> {

    private long nativePtr;
    private final String type;
    private final T value;

    public Value(@NonNull T value) {
        this.type = value.getClass().getName();
        this.value = value;
        initialize();
    }

    public boolean isNull() {
        //XXX
        Log.i(Value.class.getSimpleName(), "isNull()");
        return value != null;
    }

    protected native void initialize();

    @Override
    protected native void finalize() throws Throwable;

}
