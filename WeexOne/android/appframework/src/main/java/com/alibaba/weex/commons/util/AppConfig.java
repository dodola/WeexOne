package com.alibaba.weex.commons.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by budao on 2016/10/12.
 */
public class AppConfig {
  private static final String TAG = "AppConfig";
  private static final boolean DEBUG = false;
  private static String mLocalUrl = "file://assets/index.js";
  private static String mLaunchUrl = "http://127.0.0.1:8080/dist/index.js";
  private static Boolean mIsLaunchLocally = false;
  private static HashMap<String, String> sComponents = new HashMap<>();
  private static HashMap<String, String> sModules = new HashMap<>();

  public static void init(Context context) {
    loadAppSettings(context);
  }

  public static String getLaunchUrl() {
    return mLaunchUrl;
  }

  public static String getLocalUrl() {
    return mLocalUrl;
  }

  public static Boolean isLaunchLocally() {
    return mIsLaunchLocally;
  }

  private static void loadAppSettings(Context context) {
    int id = context.getResources().getIdentifier("app_config", "xml", context.getClass().getPackage().getName());
    if (id == 0) {
      // If we couldn't find config.xml there, we'll look in the namespace from AndroidManifest.xml
      id = context.getResources().getIdentifier("app_config", "xml", context.getPackageName());
      if (id == 0) {
        Log.e(TAG, "res/xml/config.xml is missing!");
        return;
      }
    }
    XmlResourceParser parser = context
        .getResources()
        .getXml(id);
    try {
      XmlUtils.beginDocument(parser, "app_config");
      while (true) {
        XmlUtils.nextElement(parser);
        String tag = parser.getName();
        if (tag == null) {
          break;
        }
        String name = parser.getAttributeName(0);
        String value = parser.getAttributeValue(0);
        String text = null;
        if (parser.next() == XmlPullParser.TEXT) {
          text = parser.getText();
        }
        if (DEBUG) {
          Log.v(TAG, "tag: " + tag + " value: " + value);
        }
        if ("name".equalsIgnoreCase(name)) {
          if ("bool".equals(tag)) {
            if ("launch_locally".equalsIgnoreCase(value)) {
              mIsLaunchLocally = Boolean.parseBoolean(text);
            }
          } else if ("int".equals(tag)) {
//            if ("tag B".equalsIgnoreCase(value)) {
//               mMaxMessageSize = Integer.parseInt(text);
//            }
          } else if ("string".equals(tag)) {
            if ("launch_url".equalsIgnoreCase(value)) {
              mLaunchUrl = text;
            } else if ("local_url".equalsIgnoreCase(value)) {
              mLocalUrl = text;
            }
          } else if ("component".equals(tag)) {
            sComponents.put(value, text);
          } else if ("module".equals(tag)) {
            sModules.put(value, text);
          }
        }
      }
    } catch (XmlPullParserException e) {
      Log.e(TAG, "loadAppSettings caught ", e);
    } catch (NumberFormatException e) {
      Log.e(TAG, "loadAppSettings caught ", e);
    } catch (IOException e) {
      Log.e(TAG, "loadAppSettings caught ", e);
    } finally {
      parser.close();
    }
  }

  public static HashMap<String, String> getComponents() {
    return sComponents;
  }

  public static void setComponents(HashMap<String, String> sComponents) {
    AppConfig.sComponents = sComponents;
  }

  public static HashMap<String, String> getModules() {
    return sModules;
  }

  public static void setModules(HashMap<String, String> sModules) {
    AppConfig.sModules = sModules;
  }
}
