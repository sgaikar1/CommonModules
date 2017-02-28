package com.example.smartrealitymodules.api;


import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.ProjectDetailsReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.models.response.ProjectListingRes;
import com.example.smartrealitymodules.models.share.CheckForShareRes;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {


    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GetOffers)
    Observable<GetOffersRes> getOffersList(@Body CommonReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GetAllJumbleNotifications)
    Observable<GetAllJumbleNotificationsRes> getNotificationList(@Body CommonReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.SaveReferForRewardsPost)
    Observable<CommonRes> apiSaveReferForRewardsPost(@Body SaveReferForRewardsPostReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @GET("/CheckForShare/{sAppUserName}/{sAppPassword}/{sUserID}/{sUserType}/{sProjectCode}/{sCheckForShare}")
    Observable<CheckForShareRes> getCheckForShare(@Path("sAppUserName") String appUser, @Path("sAppPassword") String appPw, @Path("sUserID") String uId, @Path("sUserType") String utype, @Path("sProjectCode") String projCode, @Path("sCheckForShare") String mobile);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.ProjectListing)
    Observable<ProjectListingRes> getProjectList(@Body CommonReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.ProjectDetails)
    Observable<ProjectDetailsRes> getProjectDetails(@Body ProjectDetailsReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.ProjectInterestedIn)
    Observable<CommonRes> apiProjectInterestedIn(@Body ProjectInterestedInReq obj);
}
