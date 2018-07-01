package aru.androidtestupstack;

import aru.androidtestupstack.Api.ServiceUtil;
import aru.androidtestupstack.Modal.GiphyResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

public final class GiphyService {

  private static final String BASE_URL = "http://api.giphy.com/";
  public static final int DEFAULT_RESULTS_COUNT = 24;

  public static final String PUBLIC_API_KEY = "7LGlkW8QC1NEG4xXYRXdzLbdZ1wb8Wm7";
  private static GiphyApi api;
  private static volatile GiphyService instance;
  private GiphyService(final String endPoint) {
    api = ServiceUtil.createService(GiphyApi.class, endPoint);
  }

  public static GiphyService getInstance() {
    return getInstance(BASE_URL);
  }
  static GiphyService getInstance(final String endPoint) {
    if (instance == null) {
      synchronized (GiphyService.class) {
        if (instance == null) {
          instance = new GiphyService(endPoint);
        }
      }
    }

    return instance;
  }

  public Observable<GiphyResponse> getSearchResults(final String searchString) {
    return getSearchResults(searchString, PUBLIC_API_KEY);
  }

  Observable<GiphyResponse> getSearchResults(final String searchString, final String apiKey) {
    return getSearchResults(searchString, DEFAULT_RESULTS_COUNT, apiKey);
  }

  Observable<GiphyResponse> getSearchResults(final String searchString, final int limit, final String apiKey) {
    return api.getSearchResults(searchString, limit, apiKey);
  }
  public interface GiphyApi {
     @GET("/v1/gifs/search") Observable<GiphyResponse> getSearchResults(@Query("q") final String searchString,
                                                                 @Query("limit") final int limit,
                                                                       @Query("api_key") final String apiKey);
  }
}
