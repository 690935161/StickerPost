package org.hfly.springbootdemo.entity;

import java.io.Serializable;

public class Sticker implements Serializable {

    private static final long serialVersionUID = 1L;

    private int stickerId;
    private int userId;
    private String userNickname;
    private long postTime;
    private String txt;

    public int getStickerId() {
        return stickerId;
    }

    public void setStickerId(int stickerId) {
        this.stickerId = stickerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getTxt() {
        return txt;
    }

    public void setText(String txt) {
        this.txt = txt;
    }
}
