package com.songdehuai.pushdemo.observer;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 描述：全局观察者模式
 * 注意，name唯一，否则全部name相同的位置都会收到通知
 *
 * @author songdehuai
 * @ClassName: com.jlgoldenbay.idoctor.utils.observer.ObserverTools
 * {@link ObserverTools}
 * @date 2018/7/16
 */
public class ObserverTools {

    private static ObserverTools observerUtils = null;

    private ObserverTools() {
    }

    private static LinkedHashMap<String, List<ObserverCallBack>> observerCallBaclList = null;

    /**
     * 初始化观察者
     *
     * @return
     */
    public static ObserverTools getInstance() {
        if (observerUtils == null) {
            observerUtils = new ObserverTools();
            observerCallBaclList = new LinkedHashMap<>();
        }
        return observerUtils;
    }

    /**
     * 添加订阅
     *
     * @param name
     * @param observerCallBack
     */
    private void addObserver2(String name, ObserverCallBack observerCallBack) {
        LogUtil.i("添加一个通知:" + name);
        if (!observerCallBaclList.containsValue(name)) {
            List<ObserverCallBack> observerCallBacks = new ArrayList<>();
            observerCallBacks.add(observerCallBack);
            observerCallBaclList.put(name, observerCallBacks);
        }
    }

    /**
     * 订阅消息，当post时可以获取到回调信息
     *
     * @param name 对应的post名称，当发送一致的名称时可以获取到回调
     */
    public void addObserver(String name, ObserverCallBack observerCallBack) {
        boolean isHas = false;
        //如果存在直接添加
        for (String key : observerCallBaclList.keySet()) {
            if (key.equals(name)) {
                observerCallBaclList.get(key).add(observerCallBack);
                isHas = true;
            }
        }
        //不存在创建添加
        if (!isHas) {
            List<ObserverCallBack> selList = new ArrayList<ObserverCallBack>();
            selList.add(observerCallBack);
            observerCallBaclList.put(name, selList);
        }
    }

    /**
     * 取消订阅,指定name
     *
     * @param name
     */
    public void removeObserver(String name) {
        for (String key : observerCallBaclList.keySet()) {
            if (key.equals(name)) {
                observerCallBaclList.remove(name);
            }
        }
    }


    /**
     * 带参数的发布消息
     *
     * @param name   发布通知的名称
     * @param object 带的参数
     */
    public void postNotification(String name, Object object) {
        //找到MAP中存的对应的回调接口名
        for (String key : observerCallBaclList.keySet()) {
            if (name.equals(key)) {
                List<ObserverCallBack> observerCallBacks = observerCallBaclList.get(key);
                for (int i = 0; i < observerCallBacks.size(); ++i) {
                    observerCallBacks.get(i).onCall(name, object);
                }
            }
        }
    }

    /**
     * 不带参数的发布消息
     *
     * @param name
     */
    public void postNotification(String name) {
        postNotification(name, null);
    }


}
