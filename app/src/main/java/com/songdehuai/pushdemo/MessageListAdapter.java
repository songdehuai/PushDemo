package com.songdehuai.pushdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 描述：消息列表适配器
 *
 * @author songdehuai
 * @ClassName: com.songdehuai.pushdemo.MessageListAdapter
 * {@link MessageListAdapter}
 * @date 2018/8/11
 */
public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private List<PushMessage> pushMessageList;

    public MessageListAdapter(Context context, List<PushMessage> pushMessageList) {
        this.context = context;
        this.pushMessageList = pushMessageList;
    }


    public void addData(PushMessage pushMessage) {
        if (pushMessageList != null) {
            pushMessageList.add(pushMessage);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return pushMessageList.size();
    }

    @Override
    public Object getItem(int i) {
        return pushMessageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == viewHolder) {
            view = View.inflate(context, R.layout.item_meg, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(pushMessageList.get(i).getTitle());
        viewHolder.text.setText(pushMessageList.get(i).getContent());
        viewHolder.type.setText(pushMessageList.get(i).getTypeStr());
        return view;
    }

    static class ViewHolder {
        @ViewInject(R.id.item_title)
        TextView title;
        @ViewInject(R.id.item_text)
        TextView text;
        @ViewInject(R.id.item_type)
        TextView type;

        public ViewHolder(View itemView) {
            x.view().inject(this, itemView);
        }
    }
}
