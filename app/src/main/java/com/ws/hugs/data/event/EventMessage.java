package com.ws.hugs.data.event;

public class EventMessage {


    public static final int START_FTP =005;

    private int type;

    private String message;


    public EventMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
