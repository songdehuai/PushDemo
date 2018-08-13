package com.songdehuai.pushdemo.observer;

/**
 * 描述：观察者模式回调
 *
 * @author songdehuai
 * @ClassName: com.jlgoldenbay.idoctor.utils.observer.ObserverCallBack
 * {@link ObserverCallBack}
 * @date 2018/7/16
 */
public interface ObserverCallBack {

    void onCall(String name, Object obj);
}
