// This file is generated. Do not edit.
package com.mapbox.mapboxsdk.style.properties;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LayoutProperty<T> {

    //line-cap
    public static final String LINE_CAP_BUTT = "butt";
    public static final String LINE_CAP_ROUND = "round";
    public static final String LINE_CAP_SQUARE = "square";

    @StringDef({
            LINE_CAP_BUTT,
            LINE_CAP_ROUND,
            LINE_CAP_SQUARE,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface LINE_CAP {}

    //line-join
    public static final String LINE_JOIN_BEVEL = "bevel";
    public static final String LINE_JOIN_ROUND = "round";
    public static final String LINE_JOIN_MITER = "miter";

    @StringDef({
            LINE_JOIN_BEVEL,
            LINE_JOIN_ROUND,
            LINE_JOIN_MITER,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface LINE_JOIN {}

    //symbol-placement
    public static final String SYMBOL_PLACEMENT_POINT = "point";
    public static final String SYMBOL_PLACEMENT_LINE = "line";

    @StringDef({
            SYMBOL_PLACEMENT_POINT,
            SYMBOL_PLACEMENT_LINE,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SYMBOL_PLACEMENT {}

    //icon-rotation-alignment
    public static final String ICON_ROTATION_ALIGNMENT_MAP = "map";
    public static final String ICON_ROTATION_ALIGNMENT_VIEWPORT = "viewport";

    @StringDef({
            ICON_ROTATION_ALIGNMENT_MAP,
            ICON_ROTATION_ALIGNMENT_VIEWPORT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ICON_ROTATION_ALIGNMENT {}

    //icon-text-fit
    public static final String ICON_TEXT_FIT_NONE = "none";
    public static final String ICON_TEXT_FIT_BOTH = "both";
    public static final String ICON_TEXT_FIT_WIDTH = "width";
    public static final String ICON_TEXT_FIT_HEIGHT = "height";

    @StringDef({
            ICON_TEXT_FIT_NONE,
            ICON_TEXT_FIT_BOTH,
            ICON_TEXT_FIT_WIDTH,
            ICON_TEXT_FIT_HEIGHT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ICON_TEXT_FIT {}

    //text-pitch-alignment
    public static final String TEXT_PITCH_ALIGNMENT_MAP = "map";
    public static final String TEXT_PITCH_ALIGNMENT_VIEWPORT = "viewport";

    @StringDef({
            TEXT_PITCH_ALIGNMENT_MAP,
            TEXT_PITCH_ALIGNMENT_VIEWPORT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEXT_PITCH_ALIGNMENT {}

    //text-rotation-alignment
    public static final String TEXT_ROTATION_ALIGNMENT_MAP = "map";
    public static final String TEXT_ROTATION_ALIGNMENT_VIEWPORT = "viewport";

    @StringDef({
            TEXT_ROTATION_ALIGNMENT_MAP,
            TEXT_ROTATION_ALIGNMENT_VIEWPORT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEXT_ROTATION_ALIGNMENT {}

    //text-justify
    public static final String TEXT_JUSTIFY_LEFT = "left";
    public static final String TEXT_JUSTIFY_CENTER = "center";
    public static final String TEXT_JUSTIFY_RIGHT = "right";

    @StringDef({
            TEXT_JUSTIFY_LEFT,
            TEXT_JUSTIFY_CENTER,
            TEXT_JUSTIFY_RIGHT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEXT_JUSTIFY {}

    //text-anchor
    public static final String TEXT_ANCHOR_CENTER = "center";
    public static final String TEXT_ANCHOR_LEFT = "left";
    public static final String TEXT_ANCHOR_RIGHT = "right";
    public static final String TEXT_ANCHOR_TOP = "top";
    public static final String TEXT_ANCHOR_BOTTOM = "bottom";
    public static final String TEXT_ANCHOR_TOP_LEFT = "top-left";
    public static final String TEXT_ANCHOR_TOP_RIGHT = "top-right";
    public static final String TEXT_ANCHOR_BOTTOM_LEFT = "bottom-left";
    public static final String TEXT_ANCHOR_BOTTOM_RIGHT = "bottom-right";

    @StringDef({
            TEXT_ANCHOR_CENTER,
            TEXT_ANCHOR_LEFT,
            TEXT_ANCHOR_RIGHT,
            TEXT_ANCHOR_TOP,
            TEXT_ANCHOR_BOTTOM,
            TEXT_ANCHOR_TOP_LEFT,
            TEXT_ANCHOR_TOP_RIGHT,
            TEXT_ANCHOR_BOTTOM_LEFT,
            TEXT_ANCHOR_BOTTOM_RIGHT,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEXT_ANCHOR {}

    //text-transform
    public static final String TEXT_TRANSFORM_NONE = "none";
    public static final String TEXT_TRANSFORM_UPPERCASE = "uppercase";
    public static final String TEXT_TRANSFORM_LOWERCASE = "lowercase";

    @StringDef({
            TEXT_TRANSFORM_NONE,
            TEXT_TRANSFORM_UPPERCASE,
            TEXT_TRANSFORM_LOWERCASE,
        })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEXT_TRANSFORM {}


    public final String name;
    public final T value;

    /* package */ LayoutProperty(String name, T value) {
        this.name = name;
        this.value = value;
    }
}
