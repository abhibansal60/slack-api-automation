package com.slack.api.world;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIWorld {
    @Value("${bearer.token}")
    private String bearerToken;
    @Value("${api.base.url}")
    private String baseURL;
    private String channelName;
    private String updatedChannelName;
    private String channelId;

    public String getUpdatedChannelName() {
        return updatedChannelName;
    }

    public void setUpdatedChannelName(String updatedChannelName) {
        this.updatedChannelName = updatedChannelName;
    }

    public String getChannelId() {
        if (this.channelId==null) throw new IllegalArgumentException("Channel ID cannot be null!, Please provide a valid channel name");
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
