package com.gystry.common.net;

import io.reactivex.Observable;
import retrofit2.http.*;


public interface Api {
    String BASE_URL = "http://api.suipaohealthy.com";
//    String BASE_URL="http://192.168.199.84:8080/api";//内网

    @GET("/a/liveBook/list")
    Observable<BaseCommonBean> getLiveList();

    @FormUrlEncoded
    @POST("/a/liveRoom/roomIn")
    Observable<BaseCommonBean> goLiveRoom(@Field("roomNum") String roomNum);
}
