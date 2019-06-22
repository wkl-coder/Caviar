package com.vrexlab.caviar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vrexlab.caviar.R;
import com.vrexlab.caviar.api.SharedPreferencesKey;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.models.StreamInfoModel;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StreamAdapter extends BaseAdapter {
    private List<StreamInfoModel> list;
    private Context context;
    private String type;

    public StreamAdapter(List<StreamInfoModel> list, Context context,String type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void addAll(List<StreamInfoModel> lists) {
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }
    public List<StreamInfoModel> getList() {
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_stream_info, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.streamInfoText.setText(list.get(position).getInfo());
        if (list.get(position).isChoose()){
            holder.streamCheckImage.setImageResource(R.drawable.check_button);
        }
        else {
            holder.streamCheckImage.setImageDrawable(null);
        }
        if (type.equals("fps")){
            holder.streamInfoText.setText(list.get(position).getInfo()+"fps");

            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(context);
            chooseStreamModel.setFps(Integer.parseInt(list.get(position).getInfo()));
            SharedPreferencesKey.saveStreamInfo(context,chooseStreamModel);
        }else if (type.equals("pixel")){
            holder.streamInfoText.setText(list.get(position).getInfo()+"p");

            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(context);
            chooseStreamModel.setPxiel(Integer.parseInt(list.get(position).getInfo()));
            SharedPreferencesKey.saveStreamInfo(context,chooseStreamModel);
        }else {
            holder.streamInfoText.setText(list.get(position).getInfo()+"kbps");

            ChooseStreamModel chooseStreamModel = SharedPreferencesKey.getStreamInfo(context);
            chooseStreamModel.setBits(Integer.parseInt(list.get(position).getInfo()));
            SharedPreferencesKey.saveStreamInfo(context,chooseStreamModel);
        }
        holder.frameStreamItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<list.size();i++){
                    if (i==position){
                        list.get(position).setChoose(true);

                    }else {
                        list.get(i).setChoose(false);
                    }
                }
                notifyDataSetChanged();

            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.stream_info_text)
        RobotoTextView streamInfoText;
        @Bind(R.id.stream_check_image)
        ImageView streamCheckImage;
        @Bind(R.id.frame_stream_item)
        FrameLayout frameStreamItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
