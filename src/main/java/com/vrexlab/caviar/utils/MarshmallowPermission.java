package com.vrexlab.caviar.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by mymac on 9/5/16.
 */
public class MarshmallowPermission {

    public static final int RECORD_PERMISSION_REQUEST_CODE = 1;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int CONTAST = 4;

    private Activity activity;

    public MarshmallowPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForRecord() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public boolean checkPermissionForNotification() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public boolean checkPermissionReadConasts() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        return PackageManager.PERMISSION_GRANTED == result;
    }


    public boolean checkPermissionForExternalStorage() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public boolean checkSms() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public boolean checkPermissionForCamera() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return PackageManager.PERMISSION_GRANTED == result;
    }

    public boolean checkPermissionsForLocation() {
        int p1 = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        int p2 = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        return PackageManager.PERMISSION_GRANTED == p1 && PackageManager.PERMISSION_GRANTED == p2;
    }

    public void requestPermissionNotification() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_NOTIFICATION_POLICY)) {
            Toast.makeText(activity, "Microphone permission needed for Notification. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY}, RECORD_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionReadContast() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
            if (Locale.getDefault().getLanguage().equals("zh")) {
                Toast.makeText(activity, "读取联系人需要开启联系人权限，请前往Kydy设置页面开启该权限", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(activity, "Contacts permission needed for read contacts. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            }
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, CONTAST);
        }
    }
    public void requestPermissionSend() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, CONTAST);
        }
    }

    public void requestPermissionForRecord() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            if (Locale.getDefault().getLanguage().equals("zh")) {
                Toast.makeText(activity, "录音需要开启麦克风权限，请前往Kydy设置页面开启该权限", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(activity, "Microphone permission needed for recording. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            }
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "Microphone permission needed for recording. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RECORD_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForExternalStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (Locale.getDefault().getLanguage().equals("zh")) {
                Toast.makeText(activity, "读取文件需要开启外部存储权限，请前往Kydy设置页面开启该权限", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            }
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {

           // Toast.makeText(activity, "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestSmS() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionsForLocation() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(activity, "Location permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, CONTAST);
        }
    }

}
