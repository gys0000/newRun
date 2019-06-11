package com.gystry.common;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.gystry.common.net.*;
import com.gystry.common.net.listener.BaseObserrverListener;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.gystry.common.test", appContext.getPackageName());
    }

    @Test
    public void getMessageTest() {
        TokenManager.getInstance().setToken("Bearer 20b4f5b55c3fc30f98120b6bdbda546d");
        RetrofitDeploy.getRetrofitManager().doRequest(RetrofitDeploy.getRetrofitManager().getRequestService().getLiveList(), new BaseObserrverListener() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {

            }
        });

    }
}
