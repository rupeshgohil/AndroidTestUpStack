package aru.androidtestupstack.Modal;

public final class GiphyImageInfo {
   private String url;
   private String gifurl;
  public String getUrl() {
    return url;
  }
  public void setUrl(final String url) {
    this.url = url;
  }
  public GiphyImageInfo withUrl(String url,String gif) {
    this.url = url;
    this.gifurl = gif;
    return this;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final GiphyImageInfo that = (GiphyImageInfo) o;

    return url != null ? url.equals(that.url) : that.url == null;

  }

  @Override public int hashCode() {
    return url != null ? url.hashCode() : 0;
  }

  public String getGifurl() {
    return gifurl;
  }

  public void setGifurl(String gifurl) {
    this.gifurl = gifurl;
  }
}
