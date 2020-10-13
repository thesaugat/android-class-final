package com.thesaugat.androidclass.dataClases;

public class MessageData {
    private CharSequence text;
    private long timeStamp;
    private CharSequence sender;

    public MessageData(CharSequence text, CharSequence sender) {
        this.text = text;
        this.sender = sender;
        this.timeStamp = System.currentTimeMillis();
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CharSequence getSender() {
        return sender;
    }

    public void setSender(CharSequence sender) {
        this.sender = sender;
    }
}
