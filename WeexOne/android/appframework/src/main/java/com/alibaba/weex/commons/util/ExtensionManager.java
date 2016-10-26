package com.alibaba.weex.commons.util;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.bridge.WXModuleManager;
import com.taobao.weex.common.WXException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by budao on 2016/10/25.
 */
public class ExtensionManager {
  public static void registerComponent(String name, String className) {
    try {
      Class clazz = Class.forName(className);
      WXSDKEngine.registerComponent(name, clazz);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (WXException e) {
      e.printStackTrace();
    }

  }

  public static void registerModule(String name, String className) {
    try {
      Class clazz = Class.forName(className);
      WXSDKEngine.registerModule(name, clazz);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (WXException e) {
      e.printStackTrace();
    }
  }

  public static void registerComponents(HashMap<String, String> components) {
    for (Map.Entry<String, String> component : components.entrySet()) {
      registerComponent(component.getKey(), component.getValue());
    }
  }

  public static void registerModules(HashMap<String, String> modules) {
    for (Map.Entry<String, String> module : modules.entrySet()) {
      registerModule(module.getKey(), module.getValue());
    }
  }
}
