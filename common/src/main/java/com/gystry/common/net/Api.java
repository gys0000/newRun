package com.gystry.common.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface Api {
    String BASE_URL="http://wthrcdn.etouch.cn/";

    @GET("weather_mini")
    Observable<WeatherEntity> getMessage(@Query("city") String city);
}
