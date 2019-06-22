package com.vrexlab.caviar.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamSettingFragment extends android.support.v4.app.Fragment {


    @Bind(R.id.left_icon)
    ImageView leftIcon;
    @Bind(R.id.fps_frame)
    FrameLayout fpsFrame;
    @Bind(R.id.pixel_frame)
    FrameLayout pixelFrame;
    @Bind(R.id.bit_frame)
    FrameLayout bitFrame;
    @Bind(R.id.fps_title)
    RobotoTextView fpsTitle;
    @Bind(R.id.fps_text)
    RobotoTextView fpsText;
    @Bind(R.id.pixel_title)
    RobotoTextView pixelTitle;
    @Bind(R.id.pixel_text)
    RobotoTextView pixelText;
    @Bind(R.id.kbps_title)
    RobotoTextView kbpsTitle;
    @Bind(R.id.kbps_text)
    RobotoTextView kbpsText;

    public StreamSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stream_setting, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }
    public void init(){
        ChooseStreamModel streamInfo = SharedPreferencesKey.getStreamInfo(Objects.requireNonNull(getActivity()));
        fpsText.setText(streamInfo.getFps()+"fps");
        pixelText.setText(streamInfo.getPxiel()+"p");
        kbpsText.setText(streamInfo.getBits()+"kbps");

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.left_icon, R.id.fps_frame, R.id.pixel_frame, R.id.bit_frame})
    public void onViewClicked(View view) {
        DialogListviewFragment dialogListviewFragment = new DialogListviewFragment();
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.left_icon:
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            case R.id.fps_frame:
                bundle.putString("type", "fps");
                dialogListviewFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, dialogListviewFragment, "").addToBackStack(null).commitAllowingStateLoss();
                break;

            case R.id.pixel_frame:
                bundle.putString("type", "pixel");
                dialogListviewFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, dialogListviewFragment, "").addToBackStack(null).commitAllowingStateLoss();
                break;

            case R.id.bit_frame:
                bundle.putString("type", "kbps");
                dialogListviewFragment.setArguments(bundle);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live, dialogListviewFragment, "").addToBackStack(null).commitAllowingStateLoss();
                break;


        }
    }
}
