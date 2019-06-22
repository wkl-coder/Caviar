package com.vrexlab.caviar.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.activitys.LoginActivity;
import com.vrexlab.caviar.api.SharedPreferencesKey;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    @Bind(R.id.left_icon)
    ImageView leftIcon;
    @Bind(R.id.myAccount_frame)
    FrameLayout myAccountFrame;
    @Bind(R.id.twitch_ingest)
    FrameLayout twitchIngest;
    @Bind(R.id.broadcast_frame)
    FrameLayout broadcastFrame;


    @Bind(R.id.logout_frame)
    FrameLayout logoutFrame;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.left_icon, R.id.myAccount_frame, R.id.twitch_ingest, R.id.broadcast_frame,R.id.logout_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            case R.id.myAccount_frame:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live,new MyAccountFragment(),"my_account").addToBackStack(null).commitAllowingStateLoss();
                break;
            case R.id.twitch_ingest:
                break;
            case R.id.broadcast_frame:
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_live,new StreamSettingFragment(),"stream_setting").addToBackStack(null).commitAllowingStateLoss();
                break;
            case R.id.logout_frame:
                SharedPreferencesKey.clear(Objects.requireNonNull(getActivity()));
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
