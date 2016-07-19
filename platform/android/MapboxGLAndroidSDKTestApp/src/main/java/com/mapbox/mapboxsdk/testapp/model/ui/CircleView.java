package com.mapbox.mapboxsdk.testapp.model.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Projection;

/**
 * Created by android on 7/19/16.
 */
public class CircleView extends View {
    private int mColor;
    private int mRadius = 25;
    private float mProjectedRadius;
    private LatLng mPosition = new LatLng(0 , 0);
    private Paint mCirclePaint;
    private MapboxMap mMap;
    private PointF mCoords = new PointF();

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        this.mCirclePaint = new Paint();
        this.mCirclePaint.setColor(mColor);
        this.mCirclePaint.setStrokeWidth(3f);
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMap != null){
            Projection projection = mMap.getProjection();
            mCoords = projection.toScreenLocation(mPosition);
            mProjectedRadius = (float) (mRadius / projection.getMetersPerPixelAtLatitude(mPosition.getLatitude()));
            mCirclePaint.setAlpha(120);
            canvas.drawCircle(mCoords.x, mCoords.y, mProjectedRadius, mCirclePaint);
        }
    }

    public void setColor(int color){
        if(mCirclePaint != null){
            mCirclePaint.setColor(color);
        }

    }
    public void setPosition(LatLng newPosition) {
        mPosition = newPosition;
    }

    public void setRadius(int radius) {
        this.mRadius = radius;
    }

    public void setProjectedRadius(float projectedRadius) {
        this.mProjectedRadius = projectedRadius;
    }

    public void setMap(MapboxMap map){
        this.mMap = map;
        invalidate();
    }
}
