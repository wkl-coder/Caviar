package com.vrexlab.caviar.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Created by KeKi on 2018-04-18.
 */

public class ScreenUtils {
    public static int getDpi(Activity context) {
        int dpi = 0;

        Display display = context.getWindowManager().getDefaultDisplay();

        DisplayMetrics dm = new DisplayMetrics();

        @SuppressWarnings("rawtypes")

        Class c;

        try {

            c = Class.forName("android.view.Display");

            @SuppressWarnings("unchecked")

            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);

            method.invoke(display, dm);

            dpi = dm.heightPixels;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return dpi;

    }
}
