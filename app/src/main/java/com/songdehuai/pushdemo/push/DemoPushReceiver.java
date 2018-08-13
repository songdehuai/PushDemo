package com.songdehuai.pushdemo.push;

import android.content.Context;
import android.util.Log;

import com.songdehuai.pushdemo.MessageListActivity;
import com.songdehuai.pushdemo.PushMessage;
import com.songdehuai.pushdemo.observer.ObserverTools;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * 描述 : 推送广播
 *
 * @author songdehuai
 * @ClassName: com.songdehuai.pushdemo.push.DemoPushReceiver
 * {@link DemoPushReceiver}
 * @date 2018/8/10
 */
public class DemoPushReceiver extends XGPushBaseReceiver {
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        Log.i("设备Token", xgPushRegisterResult.getToken());
    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        Log.i("推送消息", xgPushTextMessage.getContent());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setTitle(xgPushTextMessage.getTitle());
        pushMessage.setContent(xgPushTextMessage.getContent());
        pushMessage.setType(0);
        ObserverTools.getInstance().postNotification(MessageListActivity.MESSAGE_FLAG, pushMessage);
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {

    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
        Log.i("推送消息", "onNotifactionClickedResult:" + xgPushShowedResult.getContent());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setTitle(xgPushShowedResult.getTitle());
        pushMessage.setContent(xgPushShowedResult.getContent());
        pushMessage.setType(1);
        ObserverTools.getInstance().postNotification(MessageListActivity.MESSAGE_FLAG, pushMessage);
    }
}
