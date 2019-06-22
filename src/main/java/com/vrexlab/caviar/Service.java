package com.vrexlab.caviar;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.vrexlab.caviar.api.ServiceConfigs;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.models.ChannelInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



/**
 * Created by Sebastian Rask on 12-02-2015.
 * Class made purely for adding utility methods for other classes
 */
// TODO: Split this service out to multiple more cohesive service classes
public class Service {

    // always verify the host - dont check for certificate
    public final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @SuppressLint("BadHostnameVerifier")
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Returns the Twitch Client ID
     *
     * @return The ID
     */
    public static String getApplicationClientID() {
        return ServiceConfigs.clienId;
    }

    /**
     * Creates and returns an intent that navigates the user to the Google Play landing page for the app
     *
     * @return The intent
     */
    public static Intent getPlayStoreIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=net.nrask.notifyme"));
        return intent;
    }

    public static String getErrorEmote() {
        String[] emotes = {"('.')", "('x')", "(>_<)", "(>.<)", "(;-;)", "\\(o_o)/", "(O_o)", "(o_0)", "(≥o≤)", "(≥o≤)", "(·.·)", "(·_·)"};
        Random rnd = new Random();
        return emotes[rnd.nextInt(emotes.length - 1)];
    }

    /**
     * Checks if two calendar objects have the same day of the year
     *
     * @param one I think it's pretty obvious
     * @param two what these two objects are for
     * @return True if the day is the same, otherwise false
     */
    public static boolean isCalendarSameDay(Calendar one, Calendar two) {
        return one.get(Calendar.YEAR) == two.get(Calendar.YEAR) && one.get(Calendar.DAY_OF_YEAR) == two.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Makes a timestamp from a length in seconds.
     *
     * @param videoLengthInSeconds Length in seconds
     * @return
     */
    public static String calculateTwitchVideoLength(int videoLengthInSeconds) {
        String result = "";
        double hours = videoLengthInSeconds / 60.0 / 60.0,
                minutes,
                seconds;

        double minutesAsDecimalHours = hours - Math.floor(hours);
        minutes = 60.0 * minutesAsDecimalHours;
        double secondsAsDecimalMinutes = minutes - Math.floor(minutes);
        seconds = 60.0 * secondsAsDecimalMinutes;

        if (hours >= 1) {
            result = ((int) Math.floor(hours)) + ":";
        }
        if (minutes >= 1 || hours >= 1) {
            result += numberToTime(minutes) + ":";
        }
        result += numberToTime(Math.round(seconds));

        return result;
    }

    /**
     * Converts Double to time. f.eks. 4.5 becomes "04"
     */
    public static String numberToTime(double time) {
        int timeInt = ((int) Math.floor(time));

        if (timeInt < 10) {
            return "0" + timeInt;
        } else {
            return "" + timeInt;
        }
    }

    public static Bitmap removeBlackBars(Bitmap bitmap) {
        final int BLACKBARS_SIZE_PX = 30;
        return Bitmap.createBitmap(bitmap, 0, BLACKBARS_SIZE_PX, bitmap.getWidth(), bitmap.getHeight() - BLACKBARS_SIZE_PX * 2);
    }

    /**
     * Creates a bitmap with rounded corners.
     *
     * @param bitmap The bitmap
     * @param i      the corner radius in pixels
     * @return The bitmap with rounded corners
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        }

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) i, (float) i, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    /**
     * Returns an intent with the right destination activity for when the user is logged in.
     *
     * @param context The context from which the method is called
     * @return The intent
     */


    /**
     * Returns an intent with the right destination activity for when the user is NOT logged in.
     *
     * @param context The context from which the method is called
     * @return The intent
     */

    /**
     * Animates the background color of a view from one color to another color.
     *
     * @param v         The view to animate
     * @param toColor   The To Color
     * @param fromColor The From Color
     * @param duration  The Duration of the animation
     * @return the animator
     */
    public static Animator animateBackgroundColorChange(View v, int toColor, int fromColor, int duration) {
        ObjectAnimator colorFade = ObjectAnimator.ofObject(v, "backgroundColor", new ArgbEvaluator(), fromColor, toColor);
        colorFade.setDuration(duration);
        colorFade.start();
        return colorFade;
    }

    /**
     * Finds and returns an attribute color. If it was not found the method returns the default color
     */


    /**
     * @param view         The view to get the color from
     * @param defaultColor The color to return if the view's background isn't a ColorDrawable
     * @return The color
     */
    public static int getBackgroundColorFromView(View view, int defaultColor) {
        int color = defaultColor;
        Drawable background = view.getBackground();
        if (background instanceof ColorDrawable) {
            color = ((ColorDrawable) background).getColor();
        }

        return color;
    }

    /**
     * Decodes a byte array to a bitmap and returns it.
     */
    public static Bitmap getBitmapFromByteArray(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Creates a byte-array for a drawable and returns it.
     * This is useful for sending images with intents.
     */
    public static byte[] getDrawableByteArray(Drawable aDrawable) {
        Bitmap bitmap = drawableToBitmap(aDrawable);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * Converts a drawable to a bitmap and returns it.
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Creates a string with a unicode emoticon.
     *
     * @param unicode
     * @return
     */
    public static String getEmijoByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    /**
     * Hides the onscreen keyboard if it is visisble
     */
    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }
    }

    /**
     * Returns whether the device is a tablet or not.
     */

    /**
     * Gets the accent color from the current theme
     */
    public static int getAccentColor(Context mContext) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    /**
     * Returns a resized bitmap with a spcified factor to change the width and height with.
     */
    public static Bitmap getResizedBitmap(Bitmap bm, float factorchange) {
        return getResizedBitmap(bm, (int) (bm.getWidth() * factorchange), (int) (bm.getHeight() * factorchange));
    }


    /**
     * Creates a new resized bitmap with a specified width and height.
     */
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        //bm.recycle();
        return resizedBitmap;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int dpHeight, Context context) {
        try {
            Bitmap.Config mConfig = bm.getConfig() == null ? Bitmap.Config.ARGB_8888 : bm.getConfig();

            Bitmap resizedBitmap = bm.copy(mConfig, true);
            int heightPx = Service.dpToPixels(context, dpHeight);
            int widthPx = (int) ((1.0 * resizedBitmap.getWidth() / resizedBitmap.getHeight()) * (heightPx * 1.0));
            return getResizedBitmap(resizedBitmap, widthPx, heightPx);
        } catch (Exception e) {
            return null;
        }
    }




    /**
     * Checks if the device is connected to a valid network
     * Can be called on the UI thread
     */

    public static int NOTIFICATION_ALARM_ID = 754641782;




    /**
     * Returns the height of the device screen
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static String urlToJSONString(String urlToRead) {

        URL url;
        HttpURLConnection conn = null;
        Scanner in = null;
        String result = "";

        try {
            url = new URL(urlToRead);

            conn = openConnection(url);

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Client-ID", ServiceConfigs.clienId);
            conn.setRequestProperty("Accept", "application/vnd.twitchtv.v5+json");
            conn.setRequestMethod("GET");
            in = new Scanner(new InputStreamReader(conn.getInputStream()));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                result += line;
            }

            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                in.close();
            if (conn != null)
                conn.disconnect();
        }

        if (result.length() == 0 || (result.length() >= 1 && result.charAt(0) != '{')) {
            Log.v("URL TO JSON STRING", urlToRead + " did not successfully get read");
            Log.v("URL TO JSON STRING", "Result of reading - " + result);
        }

        return result;
    }

    public static HttpURLConnection openConnection(URL url) throws IOException {
//        HttpURLConnection conn = null;
//
//        if (url.getProtocol().toLowerCase().equals("https")) {
//            trustAllHosts();
//            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
//            https.setHostnameVerifier(DO_NOT_VERIFY);
//            conn = https;
//        } else {
//            conn = (HttpURLConnection) url.openConnection();
//        }
//
//        return conn;

        return (HttpURLConnection) url.openConnection();
    }

    /**
     * Trust every server - dont check for any certificate
     */
    public static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {

                    }

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ChannelInfo getStreamerInfoFromUserId(int streamerId) throws NullPointerException {

        ChannelInfo channelInfo = null;
        try {
            JSONObject JSONString = new JSONObject(urlToJSONString("https://api.twitch.tv/kraken/channels/" + streamerId));

            int userId = JSONString.getInt("_id");
            String displayName = JSONString.getString("display_name");
            String name = JSONString.getString("name");
            int followers = JSONString.getInt("followers");
            int views = JSONString.getInt("views");
            URL logoURL = null;
            URL videoBannerURL = null;
            URL profileBannerURL = null;

            // Make sure streamer has actually set the pictures
            if (!JSONString.isNull("logo")) {
                logoURL = new URL(JSONString.getString("logo"));
            }
            if (!JSONString.isNull("video_banner")) {
                videoBannerURL = new URL(JSONString.getString("video_banner"));
            }
            if (!JSONString.isNull("profile_banner")) {
                profileBannerURL = new URL(JSONString.getString("profile_banner"));
            }

            JSONObject JSONStringTwo = new JSONObject(urlToJSONString("https://api.twitch.tv/kraken/users/" + streamerId));
            String description = JSONStringTwo.getString("bio");

            channelInfo = new ChannelInfo(userId, name, displayName, description, followers, views, logoURL, videoBannerURL, profileBannerURL, false);

        } catch (JSONException e) {
            Log.v("Service: ", e.getMessage());
        } catch (MalformedURLException ef) {
            Log.v("Service : ", ef.getMessage());
        }

        return channelInfo;
    }





    public static boolean isVertical(Context aContext) {
        int orientation = aContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return true;
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        }
        return true;
    }

    public static void setTopRounded(Bitmap workingBitmap, ImageView v, Context context, float cornerRadius) {
        int w = workingBitmap.getWidth();
        int h = workingBitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        Shader shader = new BitmapShader(workingBitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);

        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        RectF rec = new RectF(0, 0, w, h - (h / 3));
        c.drawRect(new RectF(0, (h / 3), w, h), paint);
        c.drawRoundRect(rec, cornerRadius, cornerRadius, paint);
        //v.setImageDrawable(new BitmapDrawable(context.getResources(), bmp));
        //v.setImageBitmap(new BitmapDrawable(context.getResources(), bmp).getBitmap());
    }

    // Convert transparentColor to be transparent in a Bitmap.
    public static Bitmap makeTransparent(Bitmap bit, int transparentColor) {
        int width = bit.getWidth();
        int height = bit.getHeight();
        Bitmap myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] allpixels = new int[myBitmap.getHeight() * myBitmap.getWidth()];
        bit.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        myBitmap.setPixels(allpixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < myBitmap.getHeight() * myBitmap.getWidth(); i++) {
            if (allpixels[i] == transparentColor)
                allpixels[i] = Color.alpha(Color.TRANSPARENT);
        }

        myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        return myBitmap;
    }

    public static double getDataReceived() {
        return (double) TrafficStats.getUidRxBytes(android.os.Process
                .myUid()) / (1024 * 1024);
    }

    public static int dpToPixels(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * Gets Bitmap from the specified URL
     * Must not be called on Main UI Thread
     */
    public static Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap = null;

        try {
            HttpURLConnection connection = openConnection(new URL(url));
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            //e.printStackTrace();

            if (url.contains("https")) {
                return getBitmapFromUrl(url.replace("https", "http"));
            }
        }

        return bitmap;
    }

}
