package com.vrexlab.caviar.models;

public class ChannelUpdate {

    /**
     * _id : 44322889
     * broadcaster_language : en
     * created_at : 2013-06-03T19:12:02Z
     * display_name : dallas
     * followers : 40
     * game : Final Fantasy XV
     * language : en
     * logo : https://static-cdn.jtvnw.net/jtv_user_pictures/dallas-profile_image-1a2c906ee2c35f12-300x300.png
     * mature : true
     * name : dallas
     * partner : false
     * profile_banner : null
     * profile_banner_background_color : null
     * status : The Finalest of Fantasies
     * updated_at : 2016-12-08T15:57:55Z
     * url : https://www.twitch.tv/dallas
     * video_banner : null
     * views : 248
     */

    private int _id;
    private String broadcaster_language;
    private String created_at;
    private String display_name;
    private int followers;
    private String game;
    private String language;
    private String logo;
    private boolean mature;
    private String name;
    private boolean partner;
    private Object profile_banner;
    private Object profile_banner_background_color;
    private String status;
    private String updated_at;
    private String url;
    private Object video_banner;
    private int views;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBroadcaster_language() {
        return broadcaster_language;
    }

    public void setBroadcaster_language(String broadcaster_language) {
        this.broadcaster_language = broadcaster_language;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isMature() {
        return mature;
    }

    public void setMature(boolean mature) {
        this.mature = mature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPartner() {
        return partner;
    }

    public void setPartner(boolean partner) {
        this.partner = partner;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getVideo_banner() {
        return video_banner;
    }

    public void setVideo_banner(Object video_banner) {
        this.video_banner = video_banner;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
