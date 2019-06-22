package com.vrexlab.caviar.models;

public class Channels {

    /**
     * mature : true
     * status : Struggle Bus 5: The Fight to Stay Alive
     * broadcaster_language : en
     * display_name : dallas
     * game : Nioh
     * language : en
     * _id : 44322889
     * name : dallas
     * created_at : 2013-06-03T19:12:02Z
     * updated_at : 2017-04-24T10:03:34Z
     * partner : false
     * logo : https://static-cdn.jtvnw.net/jtv_user_pictures/dallas-profile_image-1a2c906ee2c35f12-300x300.png
     * video_banner : https://static-cdn.jtvnw.net/jtv_user_pictures/dallas-channel_offline_image-2e82c1df2a464df7-1920x1080.jpeg
     * profile_banner : null
     * profile_banner_background_color : null
     * url : https://www.twitch.tv/dallas
     * views : 2000
     * followers : 79
     * broadcaster_type : affiliate
     * stream_key : live_44322889_a34ub37c8ajv98a0
     * email : email@provider.com
     */

    private boolean mature;
    private String status;
    private String broadcaster_language;
    private String display_name;
    private String game;
    private String language;
    private String _id;
    private String name;
    private String created_at;
    private String updated_at;
    private boolean partner;
    private String logo;
    private String video_banner;
    private Object profile_banner;
    private Object profile_banner_background_color;
    private String url;
    private int views;
    private int followers;
    private String broadcaster_type;
    private String stream_key;
    private String email;

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBroadcaster_language() {
        return broadcaster_language;
    }

    public void setBroadcaster_language(String broadcaster_language) {
        this.broadcaster_language = broadcaster_language;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isPartner() {
        return partner;
    }

    public void setPartner(boolean partner) {
        this.partner = partner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getVideo_banner() {
        return video_banner;
    }

    public void setVideo_banner(String video_banner) {
        this.video_banner = video_banner;
    }

    public Object getProfile_banner() {
        return profile_banner;
    }

    public void setProfile_banner(Object profile_banner) {
        this.profile_banner = profile_banner;
    }

    public Object getProfile_banner_background_color() {
        return profile_banner_background_color;
    }

    public void setProfile_banner_background_color(Object profile_banner_background_color) {
        this.profile_banner_background_color = profile_banner_background_color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getBroadcaster_type() {
        return broadcaster_type;
    }

    public void setBroadcaster_type(String broadcaster_type) {
        this.broadcaster_type = broadcaster_type;
    }

    public String getStream_key() {
        return stream_key;
    }

    public void setStream_key(String stream_key) {
        this.stream_key = stream_key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
