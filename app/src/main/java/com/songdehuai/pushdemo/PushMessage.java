package com.songdehuai.pushdemo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "PUSH_MSG")
public class PushMessage {

    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "type")
    private int type;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    private String typeStr;

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        if (type == 0) {
            setTypeStr("透传消息");
        } else if (type == 1) {
            setTypeStr("通知栏消息");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
