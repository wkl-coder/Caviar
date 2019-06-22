package com.vrexlab.caviar.fragments;


import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.utils.RobotoTextView;
import com.vrexlab.caviar.utils.ToastUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowVideoFragment extends android.support.v4.app.Fragment {


    @Bind(R.id.video_view)
    SurfaceView videoView;
    @Bind(R.id.play_video)
    ImageView playVideo;
    public MediaPlayer mediaPlayer_video;
    @Bind(R.id.text_save)
    RobotoTextView textSave;
    @Bind(R.id.text_share)
    RobotoTextView textShare;
    Uri uri;
    public ShowVideoFragment() {
        // Required empty public constructor
    }
    public  boolean isSave;
    public String path;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_video, container, false);
        ButterKnife.bind(this, view);
        assert getArguments() != null;
        path = getArguments().getString("path");
        start_video(path);
        textShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("video/*");

                if (uri == null) {
                    ContentValues contentValues = new ContentValues(1);
                    if (!TextUtils.isEmpty(path)) {
                        contentValues.put(MediaStore.Images.Media.DATA, new File(path).getAbsolutePath());
                    }
                    uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                }

                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                getActivity().startActivity(Intent.createChooser(shareIntent, "Caviar"));
            }
        });
        textSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getActivity(),"已存储");
                isSave = true;
            }
        });
        return view;
    }

    private void start_video(final String outPath) {

        if (mediaPlayer_video == null) {
            mediaPlayer_video = new MediaPlayer();
        }
        videoView.setVisibility(View.VISIBLE);
        videoView.setKeepScreenOn(true);
        SurfaceHolder holder = videoView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer_video.setDisplay(holder);

                mediaPlayer_video.seekTo(0);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        final Uri uri = Uri.fromFile(new File(outPath));
        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo.setVisibility(View.GONE);
                mediaPlayer_video.start();

            }
        });
        mediaPlayer_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // mediaPlayer_video.start();

            }
        });
        mediaPlayer_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    mediaPlayer_video.seekTo(0);

                    playVideo.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        try {
            mediaPlayer_video.setDataSource(getActivity(), uri);
            mediaPlayer_video.prepare();
        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        TranslateAnimation animation = null;
        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            if (enter) {
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            } else {
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            }
        } else if (FragmentTransaction.TRANSIT_FRAGMENT_CLOSE == transit) {
            if (enter) {
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            } else {
                animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
            }
        }
        if (animation == null) {
            animation = new TranslateAnimation(0, 0, 0, 0);
        }
        animation.setDuration(300);
        return animation;
    }
}
