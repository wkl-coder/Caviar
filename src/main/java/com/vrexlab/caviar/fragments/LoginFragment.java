package com.vrexlab.caviar.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.activitys.LiveActivity;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.utils.MarshmallowPermission;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @Bind(R.id.start_twitch)
    LinearLayout startTwitch;
    @Bind(R.id.start_email)
    LinearLayout startEmail;
    @Bind(R.id.start_now)
    LinearLayout startNow;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        if (SharedPreferencesKey.getChannels(Objects.requireNonNull(getActivity())) != null) {
            getActivity().startActivity(new Intent(getActivity(), LiveActivity.class));
            getActivity().finish();
        }
        startTwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.frame_login, new TwichWebVIewFragment(), "").commitAllowingStateLoss();
            }
        });
        startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), LiveActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
