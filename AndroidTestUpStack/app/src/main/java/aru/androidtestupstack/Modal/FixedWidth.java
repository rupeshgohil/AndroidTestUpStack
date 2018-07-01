package aru.androidtestupstack.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixedWidth {
    @SerializedName("mp4")
    @Expose
    private String mp4;
    @SerializedName("url")
    @Expose
    private String gif;

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }
}
