package com.songdehuai.pushdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.songdehuai.pushdemo.observer.ObserverCallBack;
import com.songdehuai.pushdemo.observer.ObserverTools;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：消息列表
 *
 * @author songdehuai
 * @ClassName: com.songdehuai.pushdemo.MessageListActivity
 * {@link MessageListActivity}
 * @date 2018/8/11
 */
public class MessageListActivity extends AppCompatActivity {

    MessageListAdapter adapter;
    @ViewInject(R.id.message_lv)
    ListView msgLv;

    List<PushMessage> messageList;

    public static final String MESSAGE_FLAG = "MESSAGE_FLAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        x.view().inject(this);

        initViews();
    }

    private void initViews() {
        messageList = new ArrayList<>();
        adapter = new MessageListAdapter(getApplicationContext(), messageList);
        msgLv.setAdapter(adapter);


        ObserverTools.getInstance().addObserver(MESSAGE_FLAG, new ObserverCallBack() {
            @Override
            public void onCall(String name, Object obj) {
                adapter.addData((PushMessage) obj);
            }
        });
    }
}
