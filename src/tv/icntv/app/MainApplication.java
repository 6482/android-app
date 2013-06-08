package tv.icntv.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Properties;

/**
 * Created by guanshiliang on 13-6-7.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 获取App安装包信息
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if(info == null) info = new PackageInfo();
        return info;
    }

    public String getAppId() {
        return "111";
    }

    public void setProperties(Properties ps){
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties(){
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key,String value){
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key){
        return AppConfig.getAppConfig(this).get(key);
    }
    public void removeProperty(String...key){
        AppConfig.getAppConfig(this).remove(key);
    }

}
