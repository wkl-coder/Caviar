package com.vrexlab.caviar.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    private Activity context;
    private static OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;
    private Call call;

    public OkHttpUtils(Context context) {
        this.context = (Activity) context;
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

    }

    public static OkHttpUtils getInstance(Context context) {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils(context);
        }
        return okHttpUtils;
    }
    public interface URlCallBack {
        void onRequestComplete(Call call, Response response);

        void onFail(Call call, IOException e);
    }

    public   void reuqestJsonPost(String url, Map<String,String> hearder, String body_json, Dialog dialog,URlCallBack uRlCallBack) {
        MediaType JSON
                = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, body_json);
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);
        if (hearder != null && hearder.size() > 0) {
            for (String key : hearder.keySet()) {
                String value = hearder.get(key);
                builder.addHeader(key, value);
            }
        }
        Request request = builder.build();
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onFail(call,e);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onRequestComplete(call,response);
                    }
                });
            }
        });
    }
    public   void reuqestGet(String url, Map<String,String> hearder, Dialog dialog,URlCallBack uRlCallBack) {

        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        if (hearder != null && hearder.size() > 0) {
            for (String key : hearder.keySet()) {
                String value = hearder.get(key);
                builder.addHeader(key, value);
            }
        }
        Request request = builder.build();
        call = okHttpClient.newCall(request);
        Log.i("Asdasd",request.headers().toString());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onFail(call,e);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onRequestComplete(call,response);
                    }
                });
            }
        });
    }
    public   void reuqestPut(String url, Map<String,String> hearder,String json,URlCallBack uRlCallBack) {
        MediaType JSON
                = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder()
                .url(url)
                .put(body);
        if (hearder != null && hearder.size() > 0) {
            for (String key : hearder.keySet()) {
                String value = hearder.get(key);
                builder.addHeader(key, value);
            }
        }
        Request request = builder.build();
        call = okHttpClient.newCall(request);
        Log.i("Asdasd",request.headers().toString());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onFail(call,e);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uRlCallBack.onRequestComplete(call,response);
                    }
                });
            }
        });
    }
}
