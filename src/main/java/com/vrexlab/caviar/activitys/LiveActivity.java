package com.vrexlab.caviar.activitys;

import android.content.ContentResolver;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.pedro.rtplibrary.rtmp.RtmpDisplay;
import com.vrexlab.caviar.R;
import com.vrexlab.caviar.fragments.LiveFragment;
import com.vrexlab.caviar.fragments.ShowVideoFragment;
import com.vrexlab.caviar.utils.MarshmallowPermission;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LiveActivity extends AppCompatActivity {

    @Bind(R.id.frame_live)
    FrameLayout frameLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        ButterKnife.bind(this);



        getSupportFragmentManager().beginTransaction().replace(R.id.frame_live, new LiveFragment(), "live").addToBackStack(null).commitAllowingStateLoss();

    }

    private void deleteFile(final File file) {

        if (file != null) {
            file.delete();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver = getContentResolver();
            String where = MediaStore.Images.Media.DATA + "='" + file.getAbsolutePath() + "'";
//删除图片
            try {
                mContentResolver.delete(uri, where, null);
                MediaScannerConnection.scanFile(this,
                        new String[]{file.getAbsolutePath()}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(final String path, final Uri uri) {


                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ShowVideoFragment showVideoFragment = (ShowVideoFragment) getSupportFragmentManager().findFragmentByTag("show");
            if (showVideoFragment != null) {
                if (!showVideoFragment.isSave) {
                    deleteFile(new File(showVideoFragment.path));

                }
            }
            if (getSupportFragmentManager().getBackStackEntryCount()==1){
                finish();
            }else {
                onBackPressed();
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
