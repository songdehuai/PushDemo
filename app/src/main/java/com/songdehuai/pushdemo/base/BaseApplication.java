package com.songdehuai.pushdemo.base;

import android.app.Application;

import com.songdehuai.pushdemo.tools.SPUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.xiaomi.mipush.sdk.MiPushClient;

import org.xutils.x;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

        registerPush();
    }

    void registerPush() {
        // 设置小米的Appid和Appkey
        XGPushConfig.setMiPushAppId(getApplicationContext(), "2882303761517847846");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "5841784713846");
        XGPushConfig.enableOtherPush(getApplicationContext(), true);

        //注册信鸽,并且绑定账号
        XGPushManager.registerPush(getApplicationContext(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                SPUtils.put(getApplicationContext(), "token", o.toString());
            }

            @Override
            public void onFail(Object o, int i, String s) {

            }
        });

    }

}
