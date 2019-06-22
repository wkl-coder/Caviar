package com.vrexlab.caviar.activitys;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.fragments.LoginFragment;
import com.vrexlab.caviar.fragments.ShowVideoFragment;
import com.vrexlab.caviar.utils.MarshmallowPermission;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MarshmallowPermission marshmallowPermission = new MarshmallowPermission(this);


        marshmallowPermission.requestPermissionForCamera();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_login,new LoginFragment(),"login").addToBackStack(null).commitAllowingStateLoss();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (getSupportFragmentManager().getBackStackEntryCount()==1){
                finish();
            }else {
                onBackPressed();
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
 /*   @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/
}
