package com.binaryvr.HyprfaceUnityExample.opengl.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import com.binaryvr.HyprfaceUnityExample.opengl.egl.WLEGLSurfaceView;


public class WlCameraView extends WLEGLSurfaceView {

    private WlCameraRender wlCameraRender;
    private WlCameraRender.OnSurfaceCreateListener onSurfaceCreateListener;

    public void setOnSurfaceCreateListener(WlCameraRender.OnSurfaceCreateListener onSurfaceCreateListener) {
        this.onSurfaceCreateListener = onSurfaceCreateListener;
    }

    public WlCameraView(Context context) {
        this(context, null);
    }

    public WlCameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WlCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        wlCameraRender = new WlCameraRender(context);
        setRender(wlCameraRender);
        wlCameraRender.setOnSurfaceCreateListener(new WlCameraRender.OnSurfaceCreateListener() {
            @Override
            public void onSurfaceCreate(Surface surfaceTexture) {
                if(onSurfaceCreateListener != null)
                {
                    onSurfaceCreateListener.onSurfaceCreate(surfaceTexture);
                }
            }
        });
    }
}
