package com.example.smartrealitymodules.models.eventbus;

/**
 * Created by user on 27/2/17.
 */
public class Interest {
    String value, id, from;
    int position;

    public Interest(String value, String id, String from, int position) {
        this.value = value;
        this.id = id;
        this.from = from;
        this.position = position;
    }

    //    public Interest(String value, String id, int position) {
//        this.value = value;
//        this.id = id;
//        this.position = position;
//    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
