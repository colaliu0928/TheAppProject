package demo.example.com.myapplication;

import android.graphics.drawable.Drawable;

/**
 * 类的作用：根据app所需要展示的项目封装JavaBean
 * lenovo 刘珂珂
 * 2018/7/21
 * 14:21
 */

public class AppBean {

    private Drawable appIcon;
    private String appName;
    private String appVersion;
    private String lastUpdateTime;

    public AppBean(Drawable appIcon, String appName, String appVersion, String lastUpdateTime) {
        this.appIcon = appIcon;
        this.appName = appName;
        this.appVersion = appVersion;
        this.lastUpdateTime = lastUpdateTime;
    }

    public AppBean() {
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "appIcon=" + appIcon +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
