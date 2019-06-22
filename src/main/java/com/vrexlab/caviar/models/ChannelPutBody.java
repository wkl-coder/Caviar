package com.vrexlab.caviar.models;

public class ChannelPutBody {

    /**
     * channel : {"status":"The Finalest of Fantasies","game":"Final Fantasy XV","channel_feed_enabled":true}
     */

    private ChannelBean channel;

    public ChannelBean getChannel() {
        return channel;
    }

    public void setChannel(ChannelBean channel) {
        this.channel = channel;
    }

    public static class ChannelBean {
        /**
         * status : The Finalest of Fantasies
         * game : Final Fantasy XV
         * channel_feed_enabled : true
         */

        private String status;
        private String game;
        private boolean channel_feed_enabled;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public boolean isChannel_feed_enabled() {
            return channel_feed_enabled;
        }

        public void setChannel_feed_enabled(boolean channel_feed_enabled) {
            this.channel_feed_enabled = channel_feed_enabled;
        }
    }
}
