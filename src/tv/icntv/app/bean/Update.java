package tv.icntv.app.bean;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import tv.icntv.app.AppException;
import tv.icntv.app.common.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by tian on 13-6-8.
 */
public class Update implements Serializable {
    private int versionCode;
    private String versionName;
    private String downloadUrl;
    private String updateLog;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public static Update parse(InputStream inputStream) throws IOException, AppException {
        Update update = null;
        //获得XmlPullParser解析器
        XmlPullParser xmlParser = Xml.newPullParser();
        try {
            xmlParser.setInput(inputStream, "UTF-8");
            //获得解析到的事件类别，这里有开始文档，结束文档，开始标签，结束标签，文本等等事件。
            int evtType = xmlParser.getEventType();
            //一直循环，直到文档结束
            while (evtType != XmlPullParser.END_DOCUMENT) {
                String tag = xmlParser.getName();
                switch (evtType) {
                    case XmlPullParser.START_TAG:
                        //通知信息
                        if (tag.equalsIgnoreCase("android")) {
                            update = new Update();
                        } else if (update != null) {
                            if (tag.equalsIgnoreCase("versionCode")) {
                                update.setVersionCode(StringUtils.toInt(xmlParser.nextText(), 0));
                            } else if (tag.equalsIgnoreCase("versionName")) {
                                update.setVersionName(xmlParser.nextText());
                            } else if (tag.equalsIgnoreCase("downloadUrl")) {
                                update.setDownloadUrl(xmlParser.nextText());
                            } else if (tag.equalsIgnoreCase("updateLog")) {
                                update.setUpdateLog(xmlParser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                //如果xml没有结束，则导航到下一个节点
                evtType = xmlParser.next();
            }
        } catch (XmlPullParserException e) {
            throw AppException.xml(e);
        } finally {
            inputStream.close();
        }
        return update;
    }
}
