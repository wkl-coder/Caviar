package com.vrexlab.caviar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {


    @Bind(R.id.mask_switch)
    Switch maskSwitch;
    @Bind(R.id.resolution_switch)
    Switch resolutionSwitch;
    @Bind(R.id.framerate_switch)
    Switch framerateSwitch;
    @Bind(R.id.bitrate_switch)
    Switch bitrateSwitch;
    @Bind(R.id.alert_switch)
    Switch alertSwitch;
    @Bind(R.id.save_text)
    RobotoTextView saveText;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, view);

        framerateSwitch.setChecked(SharedPreferencesKey.getFps(Objects.requireNonNull(getActivity())));
        bitrateSwitch.setChecked(SharedPreferencesKey.getBits(Objects.requireNonNull(getActivity())));
        resolutionSwitch.setChecked(SharedPreferencesKey.getPxiel(Objects.requireNonNull(getActivity())));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.mask_switch, R.id.resolution_switch, R.id.framerate_switch, R.id.bitrate_switch, R.id.alert_switch, R.id.save_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mask_switch:
                break;
            case R.id.resolution_switch:
                SharedPreferencesKey.SavePxiel(Objects.requireNonNull(getActivity()),resolutionSwitch.isChecked());
                break;
            case R.id.framerate_switch:
                SharedPreferencesKey.SaveFps(Objects.requireNonNull(getActivity()),framerateSwitch.isChecked());

                break;
            case R.id.bitrate_switch:
                SharedPreferencesKey.SaveBits(Objects.requireNonNull(getActivity()),bitrateSwitch.isChecked());
                break;
            case R.id.alert_switch:
                break;
            case R.id.save_text:
                LiveFragment live = (LiveFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("live");
                assert live != null;
                live.init();
                Objects.requireNonNull(getActivity()).onBackPressed();

                break;
        }
    }
}
