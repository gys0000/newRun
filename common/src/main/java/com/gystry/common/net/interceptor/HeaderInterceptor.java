package com.gystry.common.net.interceptor;

import com.gystry.common.net.TokenManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HeaderInterceptor implements Interceptor {

    public static final String HEAD_CONNECTION = "Keep-Alive";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestBuilder = request.newBuilder()
                .addHeader("Connection", HEAD_CONNECTION)
                .addHeader("Authorization", TokenManager.getInstance().getToken())
                .method(request.method(), request.body())
                .build();
        return chain.proceed(requestBuilder);
    }
}
