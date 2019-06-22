package com.vrexlab.caviar.fragments;


import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pedro.rtplibrary.rtmp.RtmpCamera1;
import com.vrexlab.caviar.R;
import com.vrexlab.caviar.adapters.StreamAdapter;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.models.StreamInfoModel;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogListviewFragment extends android.support.v4.app.Fragment {


    @Bind(R.id.save_text)
    RobotoTextView saveText;
    @Bind(R.id.listView_stream_info)
    ListView listViewStreamInfo;

    public DialogListviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_listview, container, false);
        ButterKnife.bind(this, view);
        assert getArguments() != null;
        String type = getArguments().getString("type", "");
        List<StreamInfoModel> list = new ArrayList<>();
        ChooseStreamModel streamInfo = SharedPreferencesKey.getStreamInfo(getActivity());

        if (type.equals("fps")) {

            list.add(new StreamInfoModel("15", false));
            list.add(new StreamInfoModel("20", false));
            list.add(new StreamInfoModel("24", false));
            list.add(new StreamInfoModel("30", false));
            list.add(new StreamInfoModel("60", false));
            for (int i = 0; i < list.size(); i++) {
                if (streamInfo.getFps() == Integer.valueOf(list.get(i).getInfo())) {
                    list.get(i).setChoose(true);
                }
            }
        }else if (type.equals("pixel")) {
            LiveFragment liveFragment = (LiveFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("live");
            assert liveFragment != null;
            RtmpCamera1 rtmpCamera = liveFragment.getRtmpCamera();
            if (rtmpCamera != null){
                for (Camera.Size size : rtmpCamera.getResolutionsBack()) {
                    double with = size.width;
                    double height = size.height;
                    if (with/height>1.6&&with/height<2.25){
                        list.add(new StreamInfoModel(String.valueOf(size.width), false));
                    }

                }
        }else {
            list.add(new StreamInfoModel("640", false));
            list.add(new StreamInfoModel("1080", false));
            list.add(new StreamInfoModel("1920", false));
        }
            for (int i = 0; i < list.size(); i++) {
                if (streamInfo.getPxiel() == Integer.valueOf(list.get(i).getInfo())) {
                    list.get(i).setChoose(true);
                }
            }
        }else {
            list.add(new StreamInfoModel("700", false));
            list.add(new StreamInfoModel("1000", false));
            list.add(new StreamInfoModel("2500", false));
            list.add(new StreamInfoModel("4000", false));
            list.add(new StreamInfoModel("8000", false));
            list.add(new StreamInfoModel("12000", false));
            for (int i = 0; i < list.size(); i++) {
                if (streamInfo.getBits() == Integer.valueOf(list.get(i).getInfo())) {
                    list.get(i).setChoose(true);
                }
            }
        }
        StreamAdapter streamAdapter = new StreamAdapter(list, getActivity(), type);
        listViewStreamInfo.setAdapter(streamAdapter);
        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<StreamInfoModel> streamAdapterList = streamAdapter.getList();
                for (int i =0;i<streamAdapterList.size();i++){
                    if (streamAdapterList.get(i).isChoose()){
                        if (type.equals("fps")){

                            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(Objects.requireNonNull(getActivity()));
                            chooseStreamModel.setFps(Integer.parseInt(streamAdapterList.get(i).getInfo()));
                            SharedPreferencesKey.saveStreamInfo(Objects.requireNonNull(getActivity()),chooseStreamModel);
                        }else if (type.equals("pixel")){

                            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(Objects.requireNonNull(getActivity()));
                            chooseStreamModel.setPxiel(Integer.parseInt(streamAdapterList.get(i).getInfo()));
                            SharedPreferencesKey.saveStreamInfo(Objects.requireNonNull(getActivity()),chooseStreamModel);
                        }else {
                            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(Objects.requireNonNull(getActivity()));
                            chooseStreamModel.setBits(Integer.parseInt(streamAdapterList.get(i).getInfo()));
                            SharedPreferencesKey.saveStreamInfo(Objects.requireNonNull(getActivity()),chooseStreamModel);
                        }
                    }
                }
                StreamSettingFragment stream_setting = (StreamSettingFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("stream_setting");
                assert stream_setting != null;
                stream_setting.init();
                LiveFragment liveFragment = (LiveFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentByTag("live");
                assert liveFragment != null;
                if (liveFragment.getRtmpCamera().isStreaming()) {
                    liveFragment.getRtmpCamera().stopStream();
                    liveFragment.recordButton.performClick();
                }
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
