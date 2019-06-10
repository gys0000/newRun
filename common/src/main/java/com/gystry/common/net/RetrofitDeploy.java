package com.gystry.common.net;

import android.util.Log;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitDeploy {

    //volatile
    //一个对象没有初始化完成的时候就会是初始化的对象不为空，那么在双检查的时候，线程一在内部初始化，如果这个时候线程二在外部的判断，那么这时候
    //对象就不为空了，这样就直接跳过返回，那么就会出现错误。
    //volatile就是为了避免这个问题，对象在没有初始化完成的时候，其他的东西是不能访问这个对象的引用的。
    private static volatile RetrofitDeploy retrofitDeploy;
    private Retrofit retrofit;

    private RetrofitDeploy() {

    }

    public static RetrofitDeploy getRetrofitManager() {
        //synchronized很重，消耗性能，每次都synchronized不太好，所以外层价格判断，如果这个对象已经存在，那么就不需要再经过synchronized
        //双检查：内部检查的原因???
        if (retrofitDeploy == null) {
            synchronized (RetrofitDeploy.class) {
                if (retrofitDeploy == null) {
                    retrofitDeploy = new RetrofitDeploy();
                }
            }
        }
        return retrofitDeploy;
    }

    /**
     * 配置retrofit okhttp
     */
    public Retrofit doRequestByRetrofit() {
        if (retrofit==null) {
            synchronized (this){
                if (retrofit==null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
//                            .addInterceptor()//头部添加拦截器
//                            .addInterceptor()//头部添加拦截器
//                            .addInterceptor()//缓存添加拦截器
//                            .connectTimeout()
//                            .writeTimeout()
//                            .readTimeout()
//                            //添加https证书，如果有srca.cer证书，则可以通过sslSocketFactory()配置
//                            .sslSocketFactory()
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Api.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())//设置json转换器
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjava适配器
                            .client(httpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
