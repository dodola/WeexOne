package com.alibaba.weex.extend.module;

import android.provider.Settings;
import android.support.annotation.Nullable;

import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import java.util.TimeZone;

/**
 * Created by budao on 2016/10/26.
 */
public class DeviceModule extends WXModule{
  public static final String TAG = "Device";

  public static String platform;                            // Device OS
  public static String uuid;                                // Device UUID

  private static final String ANDROID_PLATFORM = "Android";
  private static final String AMAZON_PLATFORM = "amazon-fireos";
  private static final String AMAZON_DEVICE = "Amazon";

  //--------------------------------------------------------------------------
  // LOCAL METHODS
  //--------------------------------------------------------------------------

  /**
   * Get the OS name.
   *
   * @return
   */
  @WXModuleAnno
  public void getPlatform(@Nullable JSCallback callback) {
    String platform;
    if (isAmazonDevice()) {
      platform = AMAZON_PLATFORM;
    } else {
      platform = ANDROID_PLATFORM;
    }
    if(callback != null) {
      callback.invoke(platform);
    }
  }

  /**
   * Get the device's Universally Unique Identifier (UUID).
   *
   * @return
   */
  @WXModuleAnno
  public void getUuid(@Nullable JSCallback callback) {
    uuid = Settings.Secure.getString(mWXSDKInstance.getContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    if(callback != null) {
      callback.invoke(uuid);
    }
  }

  @WXModuleAnno
  public void getModel(@Nullable JSCallback callback) {
    String model = android.os.Build.MODEL;
    if(callback != null) {
      callback.invoke(model);
    }
  }

  @WXModuleAnno
  public void getProductName(@Nullable JSCallback callback) {
    String productname = android.os.Build.PRODUCT;
    if(callback != null) {
      callback.invoke(productname);
    }
  }

  @WXModuleAnno
  public void getManufacturer(@Nullable JSCallback callback) {
    String manufacturer = android.os.Build.MANUFACTURER;
    if(callback != null) {
      callback.invoke(manufacturer);
    }
  }

  @WXModuleAnno
  public void getSerialNumber(@Nullable JSCallback callback) {
    String serial = android.os.Build.SERIAL;
    if(callback != null) {
      callback.invoke(serial);
    }
  }

  /**
   * Get the OS version.
   *
   * @return
   */
  @WXModuleAnno
  public void getOSVersion(@Nullable JSCallback callback) {
    String osversion = android.os.Build.VERSION.RELEASE;
    if(callback != null) {
      callback.invoke(osversion);
    }
  }

  @WXModuleAnno
  public void getSDKVersion(@Nullable JSCallback callback) {
    @SuppressWarnings("deprecation")
    String sdkversion = android.os.Build.VERSION.SDK;
    if(callback != null) {
      callback.invoke(sdkversion);
    }
  }

  @WXModuleAnno
  public void getTimeZoneID(@Nullable JSCallback callback) {
    TimeZone tz = TimeZone.getDefault();
    if(callback != null) {
      callback.invoke(tz.getID());
    }
  }

  private boolean isAmazonDevice() {
    if (android.os.Build.MANUFACTURER.equals(AMAZON_DEVICE)) {
      return true;
    }
    return false;
  }
  /**
   * Function to check if the device is manufactured by Amazon
   *
   * @return
   */
  @WXModuleAnno
  public void isAmazonDevice(@Nullable JSCallback callback) {
    if(callback != null) {
      callback.invoke(isAmazonDevice());
    }
  }

  private boolean isVirtual() {
    return android.os.Build.FINGERPRINT.contains("generic") ||
        android.os.Build.PRODUCT.contains("sdk");
  }

  @WXModuleAnno
  public void isVirtual(@Nullable JSCallback callback) {
    if(callback != null) {
      callback.invoke(isVirtual());
    }
  }

}
