package com.example.smartrealitymodules.mvp.model;


import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.api.NetworkService;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.request.SaveReferForRewardsPostReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.models.response.GetAllJumbleNotificationsRes;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.models.share.CheckForShareReq;
import com.example.smartrealitymodules.models.share.CheckForShareRes;
import com.example.smartrealitymodules.utils.Constants;
import com.example.smartrealitymodules.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class MainModel {
    private final NetworkService networkService;
    private Utils mUtils;
    private Gson gson;

    public MainModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getOffersList(CommonReq obj, final GetOffersListCallback callback) {

        return networkService.getOffersList(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetOffersRes>>() {
                    @Override
                    public Observable<? extends GetOffersRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GetOffersRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(GetOffersRes getOffersRes) {
                        callback.onSuccess(getOffersRes);

                    }
                });
    }

    public Subscription getNotificationList(CommonReq obj, final String apiname, final GetNotificationListCallback callback) {

        gson = new Gson();
        mUtils = new Utils();

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getNotificationList(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetAllJumbleNotificationsRes>>() {
                    @Override
                    public Observable<? extends GetAllJumbleNotificationsRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GetAllJumbleNotificationsRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes) {
                        mUtils.getWebserviceLog(apiname + "_response", getAllJumbleNotificationsRes);
                        if (getAllJumbleNotificationsRes.getStatus().equalsIgnoreCase("true")) {
                            callback.onSuccess(getAllJumbleNotificationsRes);
                        } else {
                            callback.onFailure(getAllJumbleNotificationsRes);
                        }
                    }
                });
    }

    public Subscription GetCheckForShare(ArrayList<CheckForShareReq> mySoapArr, final String apiname, final GetCheckForShareCallback callback) {

        gson = new Gson();
        mUtils = new Utils();

        mUtils.logMe(apiname + "_request",mySoapArr.get(0).getValue()+"," +mySoapArr.get(1).getValue()+"," +mySoapArr.get(2).getValue()+"," + mySoapArr.get(3).getValue()+"," + mySoapArr.get(4).getValue()+"," +mySoapArr.get(5).getValue());
        return networkService.getCheckForShare(mySoapArr.get(0).getValue(), mySoapArr.get(1).getValue(), mySoapArr.get(2).getValue(), mySoapArr.get(3).getValue(), mySoapArr.get(4).getValue(), mySoapArr.get(5).getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CheckForShareRes>>() {
                    @Override
                    public Observable<? extends CheckForShareRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CheckForShareRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CheckForShareRes checkForShareRes) {
                        mUtils.getWebserviceLog(apiname + "_response", checkForShareRes);
                        if (checkForShareRes.getStatus().equalsIgnoreCase("true")) {
                            callback.onSuccess(checkForShareRes);
                        } else {
                            callback.onFailure(checkForShareRes);
                        }
                    }
                });
    }


    public void getReferFriendValidation(String name, String mobileno, String altMobileno, String email, String project, String dob, String address, String pincode, GetReferFriendValidationCallback callback) {
        int status = Constants.DEFAULT_STATUS;
        boolean flag = false;
        if (name.length() == 0) {
            status = Constants.NAME_EMPTY_STATUS;
        } else if (mobileno.length() == 0 || mobileno.length() < 10) {
            status = Constants.MOBILENO_EMPTY_STATUS;
        } else if (altMobileno.length() > 0 && altMobileno.length() < 10) {
            status = Constants.MOBILENO_ALTERNATE_EMPTY_STATUS;
        } else if (email.length() > 0 && !mUtils.isEmail(email)) {
            status = Constants.EMAIL_EMPTY_STATUS;
        } else if (pincode.length() > 0 && pincode.length() < 6) {
            status = Constants.PINCODE_EMPTY_STATUS;
        } else {
            //validation successful
            flag = true;
        }

        if (flag) {
            callback.onSuccess(flag);
        } else {
            callback.onError(status);
        }
    }

    public Subscription apiSaveReferForRewardsPost(SaveReferForRewardsPostReq obj, final String apiname, final SaveReferForRewardsPostCallback callback) {
        gson = new Gson();
        mUtils = new Utils();

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiSaveReferForRewardsPost(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CommonRes>>() {
                    @Override
                    public Observable<? extends CommonRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CommonRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CommonRes commonRes) {
                        mUtils.getWebserviceLog(apiname + "_response", commonRes);
                        if (commonRes.getStatus().equalsIgnoreCase("true")) {
                            if (commonRes.getMessage() != null) {
                                callback.onSuccess(commonRes);
                            }
                        } else {
                            if (commonRes.getMessage() != null) {
                                callback.onFailure(commonRes, true);
                            } else {
                                callback.onFailure(commonRes, false);
                            }
                        }
                    }
                });
    }



    public interface GetOffersListCallback {
        void onSuccess(GetOffersRes getOffersRes);

        void onError(NetworkError networkError);
    }

    public interface GetNotificationListCallback {
        void onSuccess(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes);

        void onError(NetworkError networkError);

        void onFailure(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes);
    }

    public interface GetReferFriendValidationCallback {
        void onSuccess(boolean flag);

        void onError(int status);
    }

    public interface SaveReferForRewardsPostCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetCheckForShareCallback {
        void onSuccess(CheckForShareRes checkForShareRes);

        void onError(NetworkError networkError);

        void onFailure(CheckForShareRes checkForShareRes);
    }
}
