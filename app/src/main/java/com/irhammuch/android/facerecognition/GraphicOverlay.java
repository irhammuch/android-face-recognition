package com.irhammuch.android.facerecognition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class GraphicOverlay extends View {
    private static final String TAG = "MainActivity";
    private final Paint rectPaint = new Paint();
    private RectF rectF;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;

    public GraphicOverlay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectPaint.setStrokeWidth(8.0f);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.CYAN);

        if(rectF != null) {
            float cornerRadius = 10.0f;
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, rectPaint);
        }
    }

    public void draw(Rect rect, float scaleX, float scaleY) {
        RectF adjustedRect = adjustBoundingRect(rect);
        this.rectF = adjustedRect;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        postInvalidate();
        requestLayout();
        logInfo(rect, scaleX, scaleY, adjustedRect);
    }

    private float translateX(float x){
        return  x * scaleX;
    }

    private float translateY(float y) {
        return y * scaleY;
    }

    private RectF adjustBoundingRect(Rect rect) {
        float padding = 10.0f;
        return new RectF(
                translateX((float) rect.left) - padding,
                translateY((float) rect.top) - padding,
                translateX((float) rect.right) + padding,
                translateY((float)rect.bottom) + padding
        );
    }

    private void logInfo(Rect rect, float scaleX, float scaleY, RectF rectF) {
        Log.i(TAG, "Boundingbox : " +  rect);
        Log.i(TAG, "RectF : " +  rectF);
        Log.i(TAG, "scaleX : "  + scaleX);
        Log.i(TAG, "scaleY : "  + scaleY);

    }

}
