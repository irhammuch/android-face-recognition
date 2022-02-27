package com.irhammuch.android.facerecognition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;


public class GraphicOverlay extends View {
    private static final String TAG = "MainActivity";
    private final Paint rectPaint = new Paint();
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private final Paint textPaint = new Paint();
    private String name = null;
    private RectF rectF = null;
    private final Paint labelPaint = new Paint();

    public GraphicOverlay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(name != null && !name.trim().isEmpty() && rectF != null && !name.equals("unknown")) {
            labelPaint.setColor(Color.BLUE);
            labelPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(
                    rectF.left,
                    rectF.bottom - 40.0f,
                    (float) (rectF.left + (rectF.right - rectF.left) * 0.75),
                    rectF.bottom,
                    labelPaint
            );
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(30.0f);
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(name, rectF.left + 15.0f , rectF.bottom - 15.0f, textPaint);
            rectPaint.setColor(Color.BLUE);
        } else {
            rectPaint.setColor(Color.RED);
        }

        if(rectF != null) {
            rectPaint.setStrokeWidth(8.0f);
            rectPaint.setStyle(Paint.Style.STROKE);
            float cornerRadius = 10.0f;
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, rectPaint);
        }
    }

    public void draw(Rect rect, float scaleX, float scaleY, String name) {
        RectF adjustedRect = adjustBoundingRect(rect);
        this.rectF = adjustedRect;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.name = name;
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
        if(rect != null) {
            float padding = 10.0f;
            return new RectF(
                    translateX((float) rect.left) - padding,
                    translateY((float) rect.top) - padding,
                    translateX((float) rect.right) + padding,
                    translateY((float)rect.bottom) + padding
            );
        }
        return null;
    }

    private void logInfo(Rect rect, float scaleX, float scaleY, RectF rectF) {
        Log.i(TAG, "Boundingbox : " +  rect);
        Log.i(TAG, "RectF : " +  rectF);
        Log.i(TAG, "scaleX : "  + scaleX);
        Log.i(TAG, "scaleY : "  + scaleY);
    }

}
