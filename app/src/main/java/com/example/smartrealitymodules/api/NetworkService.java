package com.example.smartrealitymodules.api;


import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.DeleteComplaintReq;
import com.example.smartrealitymodules.models.request.DeleteFeedbackReq;
import com.example.smartrealitymodules.models.request.EditProfileReq;
import com.example.smartrealitymodules.models.request.GetLayoutDetailsReq;
import com.example.smartrealitymodules.models.request.GetResourceRequest;
import com.example.smartrealitymodules.models.request.InsertUpdateComplaintRequestReq;
import com.example.smartrealitymodules.models.request.InsertUpdateFeedbackReq;
import com.example.smartrealitymodules.models.request.MyProfileReq;
import com.example.smartrealitymodules.models.request.ProjectDetailsReq;
import com.example.smartrealitymodules.models.request.ProjectInterestedInReq;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.models.request.ScheduleSiteVisitReq;
import com.example.smartrealitymodules.models.request.SendMISCountReq;
import com.example.smartrealitymodules.models.request.VerifyMobileNumberRequest;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.DisplayFeedbackRes;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.models.response.GetComplaintsHistoryRes;
import com.example.smartrealitymodules.models.response.GetLayoutDetailsRes;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.models.response.GetResourcesResponse;
import com.example.smartrealitymodules.models.response.MyProfileRes;
import com.example.smartrealitymodules.models.response.ProjectDetailsRes;
import com.example.smartrealitymodules.models.response.ProjectListingRes;
import com.example.smartrealitymodules.models.response.VerifyMobileNumberRes;
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

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.ScheduleSiteVisit)
    Observable<CommonRes> apiGetScheduleSiteVisit(@Body ScheduleSiteVisitReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GetLayoutDetails)
    Observable<GetLayoutDetailsRes> getLayoutDetails(@Body GetLayoutDetailsReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.MyProfile)
    Observable<MyProfileRes> getMyProfile(@Body MyProfileReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.EditProfile)
    Observable<CommonRes> getEditMyProfile(@Body EditProfileReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.RequestOTP)
    Observable<VerifyMobileNumberRes> getVerifyMobileNumber(@Body VerifyMobileNumberRequest obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.DisplayFeedback)
    Observable<DisplayFeedbackRes> getDisplayFeedback(@Body CommonReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.DeleteFeedback)
    Observable<CommonRes> apiGetDeleteFeedback(@Body DeleteFeedbackReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.InsertUpdateFeedback)
    Observable<CommonRes>apiGetInsertUpdateFeedback(@Body InsertUpdateFeedbackReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GetComplaintRequest)
    Observable<GetComplaintsHistoryRes> getDisplayComplaint(@Body CommonReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.DeleteComplaintRequest)
    Observable<CommonRes> apiGetDeleteComplaint(@Body DeleteComplaintReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.InsertUpdateComplaintRequest)
    Observable<CommonRes> apiGetInsertUpdateComplaint(@Body InsertUpdateComplaintRequestReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.SendMISCount)
    Observable<CommonRes>  apiGetSendMISCount(@Body SendMISCountReq obj);

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GET_RESOURCES)
    Observable<GetResourcesResponse> apiGetResources(@Body GetResourceRequest obj);
}
