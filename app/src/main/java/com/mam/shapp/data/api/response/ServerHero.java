package com.mam.shapp.data.api.response;

import com.google.gson.annotations.SerializedName;

public class ServerHero {
    @SerializedName("name")
    private String name;
    @SerializedName("photo")
    private String photo;
    @SerializedName("realName")
    private String realName;
    @SerializedName("height")
    private String height;
    @SerializedName("power")
    private String power;
    @SerializedName("abilities")
    private String abilities;
    @SerializedName("groups")
    private String groups;

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeight() {
        return height;
    }

    public String getPower() {
        return power;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getGroups() {
        return groups;
    }
}
