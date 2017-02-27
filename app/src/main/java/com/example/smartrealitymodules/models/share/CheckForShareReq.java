package com.example.smartrealitymodules.models.share;

/**
 * Created by user on 23/2/17.
 */

public class CheckForShareReq {

    private String value;

    public CheckForShareReq(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
