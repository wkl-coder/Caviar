package com.vrexlab.caviar.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.vrexlab.caviar.R;
import com.vrexlab.caviar.activitys.LiveActivity;
import com.vrexlab.caviar.api.ServiceConfigs;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.models.Channels;
import com.vrexlab.caviar.utils.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwichWebVIewFragment extends android.support.v4.app.Fragment {


    @Bind(R.id.webView)
    WebView webView;

    public TwichWebVIewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_twich_web_view, container, false);
        ButterKnife.bind(this, view);
        WebSettings ws = webView.getSettings();

        ws.setDomStorageEnabled(true);
        ws.setJavaScriptEnabled(true);
        ws.setBlockNetworkImage(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(getActivity());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://dyver.se")){
                    int i = url.indexOf("=");
                    int j = url.indexOf("&");
                    String token = url.substring(i + 1, j);
                    SharedPreferencesKey.saveToken(Objects.requireNonNull(getActivity()),token);
                    Map<String, String> map = new HashMap<>();
                    map.put("Accept","application/vnd.twitchtv.v5+json");
                    map.put("Client-ID",ServiceConfigs.clienId);
                    map.put("Authorization","OAuth " +token);
                    okHttpUtils.reuqestGet(ServiceConfigs.getChannerl, map, null, new OkHttpUtils.URlCallBack() {
                        @Override
                        public void onRequestComplete(Call call, Response response) {
                            if (response.code()==200){
                                assert response.body() != null;

                                Channels channels = null;
                                try {
                                    channels = new Gson().fromJson(response.body().string(), Channels.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                SharedPreferencesKey.saveChannels(getActivity(),channels);
                                getActivity().startActivity(new Intent(getActivity(), LiveActivity.class));
                                getActivity().finish();
                            }

                        }

                        @Override
                        public void onFail(Call call, IOException e) {
                            Log.i("asdasd",e.getMessage());

                        }
                    });
                }
                    return false;
            }
        });
        webView.loadUrl(ServiceConfigs.twitch_webview);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
