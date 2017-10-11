package com.pcjr.pcjr_oa.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.pcjr.pcjr_oa.App;

/**
 * Created by mario on 2017/9/29.
 */
public class DeviceUtils {

    public static String getMacAddress() {

        String macAddress =null;
        WifiManager wifiManager =
                (WifiManager) App.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null== wifiManager ?null: wifiManager.getConnectionInfo());

        if(!wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if(null!= info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }
}
