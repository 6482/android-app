package tv.icntv.app.api;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import tv.icntv.app.MainApplication;
import tv.icntv.app.bean.Update;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Created by tian on 13-6-7.
 */
public class ApiClient {
    public static final String UTF_8 = "UTF-8";
    public static final String DESC = "descend";
    public static final String ASC = "ascend";

    private final static int TIMEOUT_CONNECTION = 20000;
    private final static int TIMEOUT_SOCKET = 20000;
    private final static int RETRY_TIME = 3;

    private static String appCookie;
    private static String appUserAgent;

    public static void cleanCookie() {
        appCookie = "";
    }

    private static String getCookie(MainApplication appContext) {
        return "cookie";
    }

    public static Update checkUpdate() throws Exception {
        HttpURLConnection conn = null;
        InputStream inStream = null;
        List<String> retList = null;
        String path = "http://115.28.33.211/test.xml";

        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(3 * 1000);
            conn.setRequestMethod("GET");
            inStream = conn.getInputStream();
            return Update.parse(inStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;

    }

    public static Update checkVersion() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://115.28.33.211/test.xml");

        HttpResponse response = client.execute(get);
        System.out.println("aa=" + response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            InputStream is = response.getEntity().getContent();
            return Update.parse(is);
        }
        return null;
    }
}
