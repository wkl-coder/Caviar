package com.vrexlab.caviar.models;

public class StreamsModel {

    /**
     * stream : {"_id":23932774784,"game":"BATMAN - The Telltale Series","viewers":7254,"video_height":720,"average_fps":60,"delay":0,"created_at":"2016-12-14T22:49:56Z","is_playlist":false,"preview":{"small":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-80x45.jpg","medium":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-320x180.jpg","large":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-640x360.jpg","template":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-{width}x{height}.jpg"},"channel":{"mature":false,"status":"Dan is Batman? - Telltale's Batman","broadcaster_language":"en","display_name":"DansGaming","game":"BATMAN - The Telltale Series","language":"en","_id":7236692,"name":"dansgaming","created_at":"2009-07-15T03:02:41Z","updated_at":"2016-12-15T01:33:58Z","partner":true,"logo":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_image-76e4a4ab9388bc9c-300x300.png","video_banner":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-channel_offline_image-d3551503c24c08ad-1920x1080.png","profile_banner":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_banner-4c2b8ece8cd010b4-480.jpeg","profile_banner_background_color":null,"url":"https://www.twitch.tv/dansgaming","views":63906830,"followers":538598}}
     */

    private StreamBean stream;

    public StreamBean getStream() {
        return stream;
    }

    public void setStream(StreamBean stream) {
        this.stream = stream;
    }

    public static class StreamBean {
        /**
         * _id : 23932774784
         * game : BATMAN - The Telltale Series
         * viewers : 7254
         * video_height : 720
         * average_fps : 60
         * delay : 0
         * created_at : 2016-12-14T22:49:56Z
         * is_playlist : false
         * preview : {"small":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-80x45.jpg","medium":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-320x180.jpg","large":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-640x360.jpg","template":"https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-{width}x{height}.jpg"}
         * channel : {"mature":false,"status":"Dan is Batman? - Telltale's Batman","broadcaster_language":"en","display_name":"DansGaming","game":"BATMAN - The Telltale Series","language":"en","_id":7236692,"name":"dansgaming","created_at":"2009-07-15T03:02:41Z","updated_at":"2016-12-15T01:33:58Z","partner":true,"logo":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_image-76e4a4ab9388bc9c-300x300.png","video_banner":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-channel_offline_image-d3551503c24c08ad-1920x1080.png","profile_banner":"https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_banner-4c2b8ece8cd010b4-480.jpeg","profile_banner_background_color":null,"url":"https://www.twitch.tv/dansgaming","views":63906830,"followers":538598}
         */

        private long _id;
        private String game;
        private int viewers;
        private int video_height;
        private float average_fps;
        private int delay;
        private String created_at;
        private boolean is_playlist;
        private PreviewBean preview;
        private ChannelBean channel;

        public long get_id() {
            return _id;
        }

        public void set_id(long _id) {
            this._id = _id;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public int getViewers() {
            return viewers;
        }

        public void setViewers(int viewers) {
            this.viewers = viewers;
        }

        public int getVideo_height() {
            return video_height;
        }

        public void setVideo_height(int video_height) {
            this.video_height = video_height;
        }

        public int getAverage_fps() {

            return (int) average_fps;
        }

        public void setAverage_fps(int average_fps) {
            this.average_fps = average_fps;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public boolean isIs_playlist() {
            return is_playlist;
        }

        public void setIs_playlist(boolean is_playlist) {
            this.is_playlist = is_playlist;
        }

        public PreviewBean getPreview() {
            return preview;
        }

        public void setPreview(PreviewBean preview) {
            this.preview = preview;
        }

        public ChannelBean getChannel() {
            return channel;
        }

        public void setChannel(ChannelBean channel) {
            this.channel = channel;
        }

        public static class PreviewBean {
            /**
             * small : https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-80x45.jpg
             * medium : https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-320x180.jpg
             * large : https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-640x360.jpg
             * template : https://static-cdn.jtvnw.net/previews-ttv/live_user_dansgaming-{width}x{height}.jpg
             */

            private String small;
            private String medium;
            private String large;
            private String template;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }
        }

        public static class ChannelBean {
            /**
             * mature : false
             * status : Dan is Batman? - Telltale's Batman
             * broadcaster_language : en
             * display_name : DansGaming
             * game : BATMAN - The Telltale Series
             * language : en
             * _id : 7236692
             * name : dansgaming
             * created_at : 2009-07-15T03:02:41Z
             * updated_at : 2016-12-15T01:33:58Z
             * partner : true
             * logo : https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_image-76e4a4ab9388bc9c-300x300.png
             * video_banner : https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-channel_offline_image-d3551503c24c08ad-1920x1080.png
             * profile_banner : https://static-cdn.jtvnw.net/jtv_user_pictures/dansgaming-profile_banner-4c2b8ece8cd010b4-480.jpeg
             * profile_banner_background_color : null
             * url : https://www.twitch.tv/dansgaming
             * views : 63906830
             * followers : 538598
             */

            private boolean mature;
            private String status;
            private String broadcaster_language;
            private String display_name;
            private String game;
            private String language;
            private int _id;
            private String name;
            private String created_at;
            private String updated_at;
            private boolean partner;
            private String logo;
            private String video_banner;
            private String profile_banner;
            private Object profile_banner_background_color;
            private String url;
            private int views;
            private int followers;

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

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
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

            public String getProfile_banner() {
                return profile_banner;
            }

            public void setProfile_banner(String profile_banner) {
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
        }
    }
}
