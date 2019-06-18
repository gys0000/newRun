package com.gystry.common.net;

import android.content.Context;
import com.gystry.common.net.interceptor.CacheInterceptor;
import com.gystry.common.net.interceptor.HeaderInterceptor;
import com.gystry.common.net.interceptor.LoggingInterceptor;
import com.gystry.common.net.listener.BaseObserrverListener;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

public class RetrofitDeploy {

    public static final long DEFAULT_TIMEOUT = 60L;

    //volatile
    //一个对象没有初始化完成的时候就会是初始化的对象不为空，那么在双检查的时候，线程一在内部初始化，如果这个时候线程二在外部的判断，那么这时候
    //对象就不为空了，这样就直接跳过返回，那么就会出现错误。
    //volatile就是为了避免这个问题，对象在没有初始化完成的时候，其他的东西是不能访问这个对象的引用的。
    private static volatile RetrofitDeploy retrofitDeploy;
    private Retrofit retrofit;
    private Certificate certificate;
    private Context context;

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
    private Retrofit doRequestByRetrofit(String baseUrl) {
        if (retrofit == null) {
            synchronized (this) {
                if (retrofit == null) {
                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addInterceptor(new HeaderInterceptor())//头部添加拦截器
                            .addInterceptor(new LoggingInterceptor())//logging添加拦截器
                            .addNetworkInterceptor(new CacheInterceptor())//缓存添加拦截器
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            //添加https证书，如果有srca.cer证书，则可以通过sslSocketFactory()配置
//                           .sslSocketFactory(getSslSocketFactory(context, "srca.cer"))
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())//设置json转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rxjava适配器
                            .client(httpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public <T> DisposableObserver<BaseCommonBean<T>> doRequest(Observable<BaseCommonBean<T>> observable, final BaseObserrverListener<T> obserrverListener) {

        return observable.compose(RxSchedulers.<BaseCommonBean<T>>io_main())
                .subscribeWith(new DisposableObserver<BaseCommonBean<T>>() {
                    @Override
                    public void onNext(BaseCommonBean<T> tBaseCommonBean) {
                        if (tBaseCommonBean.getCode() == 200) {
                            obserrverListener.onSuccess(tBaseCommonBean.getData());
                        } else {
                            ErrorBean errorBean = new ErrorBean();
                            errorBean.setCode(tBaseCommonBean.getCode() + "");
                            errorBean.setMsg(tBaseCommonBean.getError() + "");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        obserrverListener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        obserrverListener.onComplete();
                    }
                });
    }

    /**
     * 使用Https请求
     *
     * @param context
     * @param name
     * @return
     */
    private SSLSocketFactory getSslSocketFactory(Context context, String name) {
        if (context == null) {
            throw new NullPointerException("context==null");
        }
        //CertificateFactory 用来证书生成
        CertificateFactory certificateFactory;
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            certificate = certificateFactory.generateCertificate(inputStream);

            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry(name, certificate);

            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, instance.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Retrofit getRequestService(String basrUrl) {
        return doRequestByRetrofit(basrUrl);
    }
}
