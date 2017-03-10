package com.example.smartrealitymodules.models.request;

/**
 * Created by user on 10/3/17.
 */
public class GetResourceRequest {
    public String cnfu;
    public String access_token;

    public GetResourceRequest(String cnfu, String access_token) {
        this.cnfu = cnfu;
        this.access_token = access_token;
    }
}
