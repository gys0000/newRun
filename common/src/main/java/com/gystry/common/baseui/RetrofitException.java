package com.gystry.common.baseui;

import com.google.gson.JsonParseException;
import org.json.JSONException;
import retrofit2.HttpException;

import javax.net.ssl.SSLHandshakeException;
import java.net.ConnectException;
import java.text.ParseException;

/**
 * 网络错误码的统一处理类
 */
public class RetrofitException {
    /**
     * Http code
     */
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 自定义的code
     */

    //未知错误
    public static final int UNKNOWN = 1000;
    //解析错误
    public static final int PARSE_ERROR = 1001;
    //网络错误
    public static final int NETWORD_ERROR = 1002;
    //协议出错
    public static final int HTTP_ERROR = 1003;
    //证书出错
    public static final int SSL_ERROR = 1005;

    public static ResponseThrowable getResponseThrowable(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable();
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    ex.code = HTTP_ERROR;
                    ex.message = "请检查权限";
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.code = HTTP_ERROR;
                    ex.message = "请检查你的网络连接";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException serverException = (ServerException) e;
            ex = new ResponseThrowable();
            ex.code = serverException.code;
            ex.message = serverException.message;
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ResponseThrowable();
            ex.code = PARSE_ERROR;
            ex.message = "数据解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable();
            ex.code = NETWORD_ERROR;
            ex.message = "网络连接失败";
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new ResponseThrowable();
            ex.code = SSL_ERROR;
            ex.message = "证书验证失败";
            return ex;
        } else {
            ex = new ResponseThrowable();
            ex.code = NETWORD_ERROR;
            ex.message = "请检查你的网络连接";
            return ex;
        }
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;
    }

    public class ServerException extends RuntimeException {
        public int code;
        public String message;
    }
}
