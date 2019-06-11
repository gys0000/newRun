package com.gystry.common.net.interceptor;

import android.annotation.SuppressLint;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.JsonObject;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
    @SuppressLint("DefaultLocale")
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        String method = request.method();
        JsonObject jsonObject = new JsonObject();
        if ("POST".equals(method) || "PUT".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                if (body != null) {
                    for (int i = 0; i < body.size(); i++) {
                        jsonObject.addProperty(body.name(i), body.encodedValue(i));
                    }
                }
                LogUtils.e(String.format("发送请求%s on %s %n RequestParams:%s%nMethod:%s",
                        request.url(), chain.connection(), jsonObject.toString(), request.method()));
            } else {
                Buffer buffer = new Buffer();
                RequestBody requestBody = request.body();
                if (requestBody != null) {
                    request.body().writeTo(buffer);
                    String body = buffer.readUtf8();
                    LogUtils.e( String.format("发送请求%s on %s %n RequestParams:%s%nMethod:%s",
                            request.url(),
                            chain.connection(),
                            body,
                            request.method()));
                }
            }
        } else {
            LogUtils.e(String.format("发送请求 %s on %s%nMethod:%s", request.url(), chain.connection(), request.method()));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到相应的时间
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        LogUtils.e( String.format("Retrofit接收响应：%s %n返回json:[%s] %n耗时：%.1fms",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d
        ));
        return response;
    }
}
