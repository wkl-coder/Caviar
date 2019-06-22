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
import com.vrexlab.caviar.models.ChatMessage;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.utils.RobotoTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatAdapter extends BaseAdapter {
    private List<ChatMessage> list;
    private Context context;

    public ChatAdapter(List<ChatMessage> list, Context context) {
        this.list = list;
        this.context = context;
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

    public void addAll(List<ChatMessage> lists) {
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }

    public void addMessage(ChatMessage chatMessage) {
        list.add(list.size(), chatMessage);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<ChatMessage> getList() {
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.chatName.setText("@"+list.get(position).getName());
        holder.chatMessage.setText(list.get(position).getMessage());
        return convertView;
    }





    static class ViewHolder {
        @Bind(R.id.chat_name)
        RobotoTextView chatName;
        @Bind(R.id.chat_message)
        RobotoTextView chatMessage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
