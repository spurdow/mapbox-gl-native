package com.mapbox.mapboxsdk.testapp.model.annotations;

import android.graphics.Bitmap;
import android.os.Parcel;

import com.mapbox.mapboxsdk.annotations.BaseMarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class CircleMarkerViewOptions extends BaseMarkerViewOptions<CircleMarkerView, CircleMarkerViewOptions> {

    private int color;
    private float radius;

    public CircleMarkerViewOptions() {
    }

    protected CircleMarkerViewOptions(Parcel in) {
        position((LatLng) in.readParcelable(LatLng.class.getClassLoader()));
        snippet(in.readString());
        title(in.readString());
        flat(in.readByte() != 0);
        anchor(in.readFloat(), in.readFloat());
        infoWindowAnchor(in.readFloat(), in.readFloat());
        rotation(in.readFloat());
        visible(in.readByte() != 0);
        alpha(in.readFloat());
        if (in.readByte() != 0) {
            // this means we have an icon
            String iconId = in.readString();
            Bitmap iconBitmap = in.readParcelable(Bitmap.class.getClassLoader());
            Icon icon = IconFactory.recreate(iconId, iconBitmap);
            icon(icon);
        }
        color(in.readInt());
        radius(in.readFloat());
    }

    @Override
    public CircleMarkerViewOptions getThis() {
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(getPosition(), flags);
        out.writeString(getSnippet());
        out.writeString(getTitle());
        out.writeByte((byte) (isFlat() ? 1 : 0));
        out.writeFloat(getAnchorU());
        out.writeFloat(getAnchorV());
        out.writeFloat(getInfoWindowAnchorU());
        out.writeFloat(getInfoWindowAnchorV());
        out.writeFloat(getRotation());
        out.writeByte((byte) (isVisible() ? 1 : 0));
        out.writeFloat(alpha);
        Icon icon = getIcon();
        out.writeByte((byte) (icon != null ? 1 : 0));
        if (icon != null) {
            out.writeString(getIcon().getId());
            out.writeParcelable(getIcon().getBitmap(), flags);
        }
        out.writeInt(color);
        out.writeFloat(radius);
    }

    @Override
    public CircleMarkerView getMarker() {
        return new CircleMarkerView(this, color , radius);
    }

    public CircleMarkerViewOptions color(int color) {
        this.color = color;
        return getThis();
    }

    public CircleMarkerViewOptions radius(float radius){
        this.radius = radius;
        return getThis();
    }

    public static final Creator<CountryMarkerViewOptions> CREATOR
            = new Creator<CountryMarkerViewOptions>() {
        public CountryMarkerViewOptions createFromParcel(Parcel in) {
            return new CountryMarkerViewOptions(in);
        }

        public CountryMarkerViewOptions[] newArray(int size) {
            return new CountryMarkerViewOptions[size];
        }
    };
}
