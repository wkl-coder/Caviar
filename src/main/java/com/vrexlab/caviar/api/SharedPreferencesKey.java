package com.vrexlab.caviar.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.vrexlab.caviar.models.Channels;
import com.vrexlab.caviar.models.ChooseStreamModel;
import com.vrexlab.caviar.models.StreamInfoModel;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class SharedPreferencesKey {


    /**
     * Created by Administrator on 2016/11/8 0008.
     */

        private static final String BGM = "bgm";
    private static final String Pixel = "bgm";
    private static final String Fps = "bgm";
    private static final String BIts = "bgm";
    private static final String SOUND = "sound";
        private static final String USER = "user";
        private static final String TOTALPOINT="total_point";
        public static final String Caviar = "Caviar";
        private static final String Type="Type";
        private static final  String Token= "token";
        private static final  String Channel = "channels";
        private static final String StreamInfo = "stream_info";
        public static void clear(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(BGM,false).putBoolean(SOUND,false).putString(Channel,"").putString(Token,"").putString(StreamInfo,"").putString(Type,"").apply();

        }
        public static void opneBGM(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(BGM,true).apply();
        }

    public static void SavePxiel(Context context,boolean isSave) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(Pixel,isSave).apply();
    }
    public static boolean getPxiel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Pixel,true);
    }
    public static void saveuser(Context context, String user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USER,user).apply();
    }
        public static void opneSound(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(SOUND,true).apply();

        }
        public static String getUser(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            return sharedPreferences.getString(USER, "");
        }
        public static void closeBGM(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(BGM,false).apply();
        }
        public static void closeSound(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(SOUND,false).apply();

        }
        public static boolean getSound(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(SOUND, true);
        }
        public static boolean getBGM(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(BGM, true);
        }
        public static void savePoint(Context context, int point) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt(TOTALPOINT,point).apply();

        }
        public static int getPoint(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
            return sharedPreferences.getInt(TOTALPOINT, 0);
        }
    public static void saveType(Context context, String type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Type,type).apply();

    }
    public static String getType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Type, "recording");
    }
    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Token,token).apply();

    }
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Token, "");
    }
    public static void saveChannels(Context context, Channels channels) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Channel,new Gson().toJson(channels)).apply();

    }
    public static Channels getChannels(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return new Gson().fromJson(sharedPreferences.getString(Channel, ""), Channels.class);
    }
    public static void saveStreamInfo(Context context, ChooseStreamModel chooseStreamModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(StreamInfo,new Gson().toJson(chooseStreamModel)).apply();

    }
    public static ChooseStreamModel getStreamInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        String info = sharedPreferences.getString(StreamInfo, "");
        if(TextUtils.isEmpty(info)){
            return new ChooseStreamModel(30, 480, 16000);
        }
        return new Gson().fromJson(info, ChooseStreamModel.class);
    }

    public static void SaveFps(Context context,boolean isSave) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(Fps,isSave).apply();
    }
    public static boolean getFps(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Fps,true);
    }
    public static void SaveBits(Context context,boolean isSave) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(BIts,isSave).apply();
    }
    public static boolean getBits(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Caviar, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(BIts,true);
    }

}
