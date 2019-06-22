package com.vrexlab.caviar;

import android.app.Application;

public class CaviarApplication extends Application {
    static {
        System.loadLibrary("libhyprface");//引入你的so库文件，不要把前面的lib添加进来
    }
}
