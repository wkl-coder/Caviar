package com.vrexlab.caviar.fragments;


import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pedro.encoder.input.video.CameraHelper;
import com.pedro.rtplibrary.rtmp.RtmpCamera1;
import com.pedro.rtplibrary.view.OpenGlView;
import com.vrexlab.caviar.R;
import com.vrexlab.caviar.Service;
import com.vrexlab.caviar.adapters.ChatAdapter;
import com.vrexlab.caviar.api.ServiceConfigs;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.chat.ChatManager;
import com.vrexlab.caviar.chat.SendMessageTask;
import com.vrexlab.caviar.models.ChannelPutBody;
import com.vrexlab.caviar.models.Channels;
import com.vrexlab.caviar.models.ChatMessage;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.models.Emote;
import com.vrexlab.caviar.models.StreamsModel;
import com.vrexlab.caviar.utils.OkHttpUtils;
import com.vrexlab.caviar.utils.RobotoTextView;

import net.ossrs.rtmp.ConnectCheckerRtmp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment implements SurfaceHolder.Callback,ConnectCheckerRtmp {


    @Bind(R.id.menu_button)
    ImageView menuButton;
    @Bind(R.id.record_button)
    public ImageView recordButton;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.surface_live)
    OpenGlView surfaceLive;
    @Bind(R.id.switch_linear)
    LinearLayout switchLinear;
    @Bind(R.id.frame_button)
    FrameLayout frameButton;

    @Bind(R.id.text_fps)
    RobotoTextView textFps;
    @Bind(R.id.fps_linear)
    LinearLayout fpsLinear;
    @Bind(R.id.stream_info)
    LinearLayout streamInfo;
    @Bind(R.id.text_viewer)
    RobotoTextView textViewer;
    @Bind(R.id.username_edit)
    TextView usernameEdit;
    @Bind(R.id.display_linear)
    LinearLayout displayLinear;
    @Bind(R.id.setting_linear)
    LinearLayout settingLinear;
    @Bind(R.id.support_linear)
    LinearLayout supportLinear;
    @Bind(R.id.edit_send_message)
    EditText editSendMessage;
    @Bind(R.id.send_text)
    RobotoTextView sendText;
    @Bind(R.id.chat_listView)
    ListView chatListView;
    @Bind(R.id.edit_title)
    EditText editTitle;
    @Bind(R.id.title_linear)
    LinearLayout titleLinear;
    private Timer timer;
    private TimerTask timerTask;
    private String path;
    private ChatAdapter chatAdapter;

    public LiveFragment() {
        // Required empty public constructor
    }

    private final int REQUEST_CODE_STREAM = 179; //random num
    private final int REQUEST_CODE_RECORD = 180; //random num
    private String currentDateAndTime = "";

    private boolean isRecord;
    private RtmpCamera1 rtmpCamera1;
    private File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/vrex_caviar");

    public RtmpCamera1 getRtmpCamera() {
        if (rtmpCamera1 != null) {
            return rtmpCamera1;
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getActivity()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        ButterKnife.bind(this, view);

        initUnitySurface(surfaceLive);
        rtmpCamera1 = new RtmpCamera1(surfaceLive, this);
        init();
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        startTimer();
        supportLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, new FeedContentFragment(), "").addToBackStack(null).commitAllowingStateLoss();

            }
        });
        titleLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTitle.setVisibility(View.VISIBLE);
                editTitle.requestFocus();
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
             /*   editTitle.setVisibility(View.VISIBLE);
                editTitle.requestFocus();
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);*/
            }
        });
        switchLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, new ChooseModeFragment(), "").addToBackStack(null).commitAllowingStateLoss();
            }
        });
        settingLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, new SettingFragment(), "setting").addToBackStack(null).commitAllowingStateLoss();
            }
        });
        if (!SharedPreferencesKey.getType(getActivity()).equals("recording")) {
            recordButton.setImageDrawable(getResources().getDrawable(R.drawable.button_stremin));
            usernameEdit.setText(SharedPreferencesKey.getChannels(getActivity()).getDisplay_name());
        }
        editSendMessage.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage(editSendMessage.getText().toString());
                return true;
            }
            return false;
        });
        editTitle.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                changeTitle(editTitle.getText().toString());
                return true;
            }
            return false;
        });
        displayLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, new DisplayFragment(), "display").addToBackStack(null).commitAllowingStateLoss();
            }
        });
        if (TextUtils.isEmpty(SharedPreferencesKey.getToken(getActivity()))) {

            frameButton.setVisibility(View.GONE);
        } else {
            frameButton.setVisibility(View.VISIBLE);
        }
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesKey.getType(Objects.requireNonNull(getActivity())).equals("recording")) {

                    if (!rtmpCamera1.isRecording()) {
                        try {
                            if (!folder.exists()) {
                                folder.mkdir();
                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                            currentDateAndTime = sdf.format(new Date());
                            if (!rtmpCamera1.isStreaming()) {
                                int rotation = CameraHelper.getCameraOrientation(getActivity());
                                ChooseStreamModel streamInfo = SharedPreferencesKey.getStreamInfo(getActivity());

                                int width;
                                int height = getHeight(streamInfo);
                                if (height == 0) {
                                    width = 640;
                                    height = 360;
                                } else {
                                    width = streamInfo.getPxiel();
                                }

                                if (rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo(width, height, streamInfo.getFps(), 2000 * 1024, false, rotation)) {
                                    path = folder.getAbsolutePath() + "/" + currentDateAndTime + ".mp4";

                                    rtmpCamera1.startRecord(path);
                                    recordButton.setImageResource(R.drawable.button_record);
                                } else {
                                    Toast.makeText(getActivity(), "Error preparing stream, This device cant do it",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                path = folder.getAbsolutePath() + "/" + currentDateAndTime + ".mp4";
                                rtmpCamera1.startRecord(path);
                                recordButton.setImageResource(R.drawable.button_record);

                            }
                        } catch (IOException e) {
                            rtmpCamera1.stopRecord();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        rtmpCamera1.stopRecord();
                        recordButton.setImageResource(R.drawable.button_record_mode);
                        ShowVideoFragment showVideoFragment = new ShowVideoFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("path", path);
                        showVideoFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_live, showVideoFragment, "showVideo").addToBackStack(null).commitAllowingStateLoss();
                        currentDateAndTime = "";
                    }


                } else {
                    if (!rtmpCamera1.isStreaming()) {
                        int rotation = CameraHelper.getCameraOrientation(getActivity());
                        ChooseStreamModel streamInfo = SharedPreferencesKey.getStreamInfo(getActivity());

                        int width;
                        int height = getHeight(streamInfo);
                        if (height == 0) {
                            width = 640;
                            height = 360;
                        } else {
                            width = streamInfo.getPxiel();
                        }
                        if (rtmpCamera1.isRecording()
                                || rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo(width, height, streamInfo.getFps(), 2000 * 1024, false, rotation)) {
                            Channels channels = SharedPreferencesKey.getChannels(getActivity());
                            rtmpCamera1.startStream("rtmp://live-sfo.twitch.tv/app/" + channels.getStream_key());
                        } else {
                            Toast.makeText(getActivity(), "Error preparing stream, This device cant do it",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        rtmpCamera1.stopStream();
                    }
                }
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // Quit Unity
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // Pause Unity
    @Override
    public void onPause() {
        super.onPause();
    }

    void initUnitySurface(SurfaceView unitySurface) {

        unitySurface.getHolder().addCallback(this);

    }

    // Resume Unity
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        ChooseStreamModel streamInfo = SharedPreferencesKey.getStreamInfo(getActivity());

        int width;
        int height = getHeight(streamInfo);
        if (height == 0) {
            width = 640;
            height = 360;
        } else {
            width = streamInfo.getPxiel();
        }
        rtmpCamera1.startPreview(CameraHelper.Facing.FRONT, width, height);

    }

    ChatManager chatManager;

    private void sendMessage(String message) {
        if (message.isEmpty()) {
            Service.hideKeyboard(Objects.requireNonNull(getActivity()));
            editSendMessage.setVisibility(View.GONE);
            return;
        }
        Service.hideKeyboard(Objects.requireNonNull(getActivity()));
        editSendMessage.setVisibility(View.GONE);
        SendMessageTask sendMessageTask = new SendMessageTask(chatManager, message, this);
        sendMessageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void addMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            ChatMessage chatMessage = new ChatMessage(message, SharedPreferencesKey.getChannels(Objects.requireNonNull(getActivity())).getName());
            if (chatAdapter != null) {
                chatAdapter.addMessage(chatMessage);
            }
        }


    }

    private void connect() {
        final LiveFragment instance = this;
        chatManager = new
                ChatManager(getContext(), SharedPreferencesKey.getChannels(Objects.requireNonNull(getActivity())).getName(), Integer.valueOf(SharedPreferencesKey.getChannels(getActivity()).get_id()), new ChatManager.ChatCallback() {
            private boolean connected = false;

            private boolean isFragmentActive() {
                return instance != null && !instance.isDetached() && instance.isAdded();
            }

            @Override
            public void onMessage(ChatMessage message) {
                if (isFragmentActive()) {
                    chatAdapter.addMessage(message);
                    Log.i("asdasd", message.getName() + ": " + message.getMessage());
                }
            }

            @Override
            public void onConnecting() {
                if (isFragmentActive()) {
                    Log.i("asdasd", "okay");
                }
            }

            @Override
            public void onReconnecting() {
                if (isFragmentActive()) {
                    Log.i("asdasd", "recon");
                }
            }

            @Override
            public void onConnected() {
                if (isFragmentActive()) {
                    ArrayList<ChatMessage> chatMessages = new ArrayList<>();
                    chatAdapter = new ChatAdapter(chatMessages, getActivity());
                    chatListView.setAdapter(chatAdapter);
                    sendText.setVisibility(View.VISIBLE);
                    sendText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editSendMessage.setVisibility(View.VISIBLE);
                            editSendMessage.requestFocus();
                            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    });
                    Log.i("asdasd", "connected");
                }
            }

            @Override
            public void onConnectionFailed() {
                if (isFragmentActive()) {
                    Log.i("asdasd", "fail");
                }
            }

            @Override
            public void onRoomstateChange(boolean isR9K, boolean isSlow, boolean isSubsOnly) {

            }


            @Override
            public void onBttvEmoteIdFetched(List<Emote> bttvChannel, List<Emote> bttvGlobal) {

            }

            private void roomStateIconChange(boolean isOn, ImageView icon) {
                if (isFragmentActive()) {
                    if (!isOn) {
                        icon.setVisibility(View.GONE);
                    } else {
                        icon.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && rtmpCamera1.isRecording()) {
            rtmpCamera1.stopRecord();
            Toast.makeText(getActivity(),
                    "file " + currentDateAndTime + ".mp4 saved in " + folder.getAbsolutePath(),
                    Toast.LENGTH_SHORT).show();
            currentDateAndTime = "";
        }
        if (rtmpCamera1.isStreaming()) {
            rtmpCamera1.stopStream();
        }
        rtmpCamera1.stopPreview();
    }

    public void init() {
        if (SharedPreferencesKey.getType(Objects.requireNonNull(getActivity())).equals("recording")) {
            recordButton.setImageResource(R.drawable.button_record_mode);
        } else {

            recordButton.setImageResource(R.drawable.button_stremin);

        }
        if (SharedPreferencesKey.getFps(getActivity())) {
            fpsLinear.setVisibility(View.VISIBLE);
        } else {
            fpsLinear.setVisibility(View.GONE);
        }

    }

    @Override
    public void onConnectionSuccessRtmp() {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recordButton.setImageResource(R.drawable.button_record);
                connect();
                Toast.makeText(getActivity(), "Streaming Start", Toast.LENGTH_SHORT).show();
                if (timer != null) {
                    timer.schedule(timerTask, 5000, 10000);
                } else {
                    startTimer();
                }
            }
        });

    }

    @Override
    public void onConnectionFailedRtmp(String reason) {

        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), reason, Toast.LENGTH_SHORT).show();
                rtmpCamera1.stopStream();
            }
        });
    }

    @Override
    public void onDisconnectRtmp() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                if (chatManager != null) {
                    chatManager.stop();
                }
                chatListView.setVisibility(View.GONE);
                sendText.setVisibility(View.GONE);
                recordButton.setImageResource(R.drawable.button_stremin);
                frameButton.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Streaming Stop", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onAuthErrorRtmp() {

    }

    @Override
    public void onAuthSuccessRtmp() {

    }

    // Notify Unity of the focus change.
    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (rtmpCamera1.isStreaming()) {
                    OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(getActivity());
                    Map<String, String> map = new HashMap<>();
                    map.put("Accept", "application/vnd.twitchtv.v5+json");
                    map.put("Client-ID", ServiceConfigs.clienId);
                    String url = ServiceConfigs.getStreams + SharedPreferencesKey.getChannels(Objects.requireNonNull(getActivity())).get_id();
                    okHttpUtils.reuqestGet(url, map, null, new OkHttpUtils.URlCallBack() {
                        @Override
                        public void onRequestComplete(Call call, Response response) {
                            if (response.code() == 200) {
                                try {
                                    assert response.body() != null;
                                    String string = response.body().string();
                                    StreamsModel streamsModel = new Gson().fromJson(string, StreamsModel.class);
                                    if (streamsModel != null && streamsModel.getStream() != null) {
                                        int average_fps = streamsModel.getStream().getAverage_fps();
                                        if (average_fps != 0) {
                                            textFps.setText(average_fps + " fps");
                                        }
                                        editTitle.setText(streamsModel.getStream().getChannel().getStatus()+"");
                                        textViewer.setText(streamsModel.getStream().getViewers() + "");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFail(Call call, IOException e) {

                        }
                    });
                } else {
                    timer.cancel();
                    timer = null;
                }
            }
        };
    }

    private int getHeight(ChooseStreamModel streamInfo) {
        for (Camera.Size size : rtmpCamera1.getResolutionsBack()) {
            double with = size.width;
            double height = size.height;
            if (with / height > 1.7 && with / height < 1.85) {
                if (size.width == streamInfo.getPxiel()) {
                    return size.height;
                }

            }
        }
        return 0;

    }

    private void changeTitle(String game) {
        OkHttpUtils okHttpUtils = OkHttpUtils.getInstance(getActivity());
        Map<String, String> map = new HashMap<>();
        String token = SharedPreferencesKey.getToken(Objects.requireNonNull(getActivity()));
        map.put("Accept", "application/vnd.twitchtv.v5+json");
        map.put("Client-ID", ServiceConfigs.clienId);
        map.put("Authorization", "OAuth " + token);
        String url = "https://api.twitch.tv/kraken/channels/" + SharedPreferencesKey.getChannels(Objects.requireNonNull(getActivity())).get_id();
        ChannelPutBody channelPutBody = new ChannelPutBody();
        ChannelPutBody.ChannelBean channelBean = new ChannelPutBody.ChannelBean();
        channelBean.setStatus(game);
        channelPutBody.setChannel(channelBean);
        Log.i("asdasd",url);

        okHttpUtils.reuqestPut(url, map, new Gson().toJson(channelPutBody), new OkHttpUtils.URlCallBack() {
            @Override
            public void onRequestComplete(Call call, Response response) {
                if (response.code() == 200) {
                    try {
                        assert response.body() != null;
                        String string = response.body().string();

                        StreamsModel streamsModel = new Gson().fromJson(string, StreamsModel.class);
                        if (streamsModel != null && streamsModel.getStream() != null) {
                            int average_fps = streamsModel.getStream().getAverage_fps();
                            if (average_fps != 0) {
                                textFps.setText(average_fps + " fps");
                            }
                            textViewer.setText(streamsModel.getStream().getViewers() + "");
                            Service.hideKeyboard(Objects.requireNonNull(getActivity()));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(Call call, IOException e) {
                Log.i("asdasd",e.getMessage());
            }
        });
    }
}
