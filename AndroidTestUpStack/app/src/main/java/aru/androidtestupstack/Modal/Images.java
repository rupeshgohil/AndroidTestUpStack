package aru.androidtestupstack.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public final class Images {
  @SerializedName("fixed_height")
  @Expose private FixedHeight fixedHeight;
  @SerializedName("fixed_width")
  @Expose private FixedWidth fixedwidth;


  public Images() {
  }

  public Images(final FixedHeight fixedHeight) {
    this.fixedHeight = fixedHeight;
  }

  public FixedHeight getFixedHeight() {
    return fixedHeight;
  }

  public void setFixedHeight(final FixedHeight fixedHeight) {
    this.fixedHeight = fixedHeight;
  }

  public FixedWidth getFixedwidth() {
    return fixedwidth;
  }

  public void setFixedwidth(FixedWidth fixedwidth) {
    this.fixedwidth = fixedwidth;
  }
}
