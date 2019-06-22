package com.vrexlab.caviar.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseModeFragment extends android.support.v4.app.Fragment {


    @Bind(R.id.record_text)
    TextView recordText;
    @Bind(R.id.record_frame)
    FrameLayout recordFrame;
    @Bind(R.id.streaming_text)
    TextView streamingText;
    @Bind(R.id.streaming_frame)
    FrameLayout streamingFrame;
    @Bind(R.id.sure_choose)
    RobotoTextView sureChoose;

    public ChooseModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_mode, container, false);
        ButterKnife.bind(this, view);
        if (TextUtils.isEmpty(SharedPreferencesKey.getToken(Objects.requireNonNull(getActivity())))){
            streamingFrame.setVisibility(View.GONE);
            recordText.setText("√");
        }else {
            if (SharedPreferencesKey.getType(getActivity()).equals("recording")) {
                recordText.setText("√");
                streamingText.setText("");
            } else {
                recordText.setText("");
                streamingText.setText("√");
            }
        }

        recordFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordText.setText("√");
                streamingText.setText("");
                SharedPreferencesKey.saveType(Objects.requireNonNull(getActivity()), "recording");
            }
        });
        streamingFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordText.setText("");
                streamingText.setText("√");
                SharedPreferencesKey.saveType(Objects.requireNonNull(getActivity()), "streaming");
            }
        });
        sureChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveFragment live = (LiveFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("live");
                assert live != null;
                live.init();
                Objects.requireNonNull(getActivity()).onBackPressed();
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
