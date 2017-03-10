package com.example.smartrealitymodules.mvp.model;


import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.api.NetworkService;
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
import com.example.smartrealitymodules.models.share.CheckForShareReq;
import com.example.smartrealitymodules.models.share.CheckForShareRes;
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
        gson = new Gson();
        mUtils = new Utils();
    }

    public Subscription getOffersList(CommonReq obj, final String apiname, final GetOffersListCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
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
                        mUtils.getWebserviceLog(apiname + "_response", getOffersRes);
                        callback.onSuccess(getOffersRes);

                    }
                });
    }

    public Subscription getProjectList(CommonReq obj, final String apiname, final GetProjectListCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getProjectList(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ProjectListingRes>>() {
                    @Override
                    public Observable<? extends ProjectListingRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ProjectListingRes>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   callback.onError(new NetworkError(e));

                               }

                               @Override
                               public void onNext(ProjectListingRes projectListingRes) {
                                   mUtils.getWebserviceLog(apiname + "_response", projectListingRes);
                                   if (projectListingRes.getStatus().equalsIgnoreCase("true")) {
                                       callback.onSuccess(projectListingRes);
                                   } else {
                                       callback.onFailure(projectListingRes);
                                   }

                               }

                           }
                );
    }


    public Subscription getProjectDetails(ProjectDetailsReq obj, final String apiname, final GetProjectDetailsCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getProjectDetails(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ProjectDetailsRes>>() {
                    @Override
                    public Observable<? extends ProjectDetailsRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ProjectDetailsRes>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   callback.onError(new NetworkError(e));

                               }

                               @Override
                               public void onNext(ProjectDetailsRes projectDetailsRes) {
                                   mUtils.getWebserviceLog(apiname + "_response", projectDetailsRes);
                                   if (projectDetailsRes.getStatus().equalsIgnoreCase("true")) {
                                       callback.onSuccess(projectDetailsRes);
                                   } else {
                                       callback.onFailure(projectDetailsRes);
                                   }

                               }

                           }
                );
    }


    public Subscription getNotificationList(CommonReq obj, final String apiname, final GetNotificationListCallback callback) {


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


        mUtils.logMe(apiname + "_request", mySoapArr.get(0).getValue() + "," + mySoapArr.get(1).getValue() + "," + mySoapArr.get(2).getValue() + "," + mySoapArr.get(3).getValue() + "," + mySoapArr.get(4).getValue() + "," + mySoapArr.get(5).getValue());
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


    public Subscription apiSaveReferForRewardsPost(SaveReferForRewardsPostReq obj, final String apiname, final SaveReferForRewardsPostCallback callback) {
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

    public Subscription getProjectInterestedIn(ProjectInterestedInReq obj, final String apiname, final GetProjectInterestedInCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiProjectInterestedIn(obj)
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

    public Subscription getScheduleSiteVisit(ScheduleSiteVisitReq obj, final String apiname, final GetScheduleSiteVisitCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetScheduleSiteVisit(obj)
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


    public Subscription getLayoutDetails(GetLayoutDetailsReq obj, final String apiname, final GetLayoutDetailsCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getLayoutDetails(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetLayoutDetailsRes>>() {
                    @Override
                    public Observable<? extends GetLayoutDetailsRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GetLayoutDetailsRes>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   callback.onError(new NetworkError(e));

                               }

                               @Override
                               public void onNext(GetLayoutDetailsRes getLayoutDetailsRes) {
                                   mUtils.getWebserviceLog(apiname + "_response", getLayoutDetailsRes);
                                   if (getLayoutDetailsRes.getStatus().equalsIgnoreCase("true")) {
                                       if (getLayoutDetailsRes.getResult() != null) {
                                           if (getLayoutDetailsRes.getResult().getMasterLayout() != null && !getLayoutDetailsRes.getResult().getMasterLayout().isEmpty()) {
                                               callback.onSuccess(getLayoutDetailsRes);
                                           } else {
                                               callback.onFailure(getLayoutDetailsRes);
                                           }
                                       }
                                   } else {
                                       if (getLayoutDetailsRes.getMessage() != null) {
                                           callback.onFailure(getLayoutDetailsRes, true);
                                       } else {
                                           callback.onFailure(getLayoutDetailsRes, false);
                                       }
                                   }

                               }

                           }
                );
    }


    public Subscription getMyProfile(MyProfileReq obj, final String apiname, final GetMyProfileCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getMyProfile(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MyProfileRes>>() {
                    @Override
                    public Observable<? extends MyProfileRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MyProfileRes>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   callback.onError(new NetworkError(e));

                               }

                               @Override
                               public void onNext(MyProfileRes myProfileRes) {
                                   mUtils.getWebserviceLog(apiname + "_response", myProfileRes);
                                   if (myProfileRes.getStatus().equalsIgnoreCase("true")) {
                                       if (myProfileRes.getResult() != null) {
                                           callback.onSuccess(myProfileRes);
                                       }
                                   } else {
                                       if (myProfileRes.getMessage() != null) {
                                           callback.onFailure(myProfileRes, true);
                                       } else {
                                           callback.onFailure(myProfileRes, false);
                                       }
                                   }

                               }

                           }
                );
    }

    public Subscription getEditMyProfile(EditProfileReq obj, final String apiname, final GetEditMyProfileCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getEditMyProfile(obj)
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

                           }
                );
    }

    public Subscription getVerifyMobileNumber(VerifyMobileNumberRequest obj, final String apiname, final GetVerifyMobileNumberCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getVerifyMobileNumber(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends VerifyMobileNumberRes>>() {
                    @Override
                    public Observable<? extends VerifyMobileNumberRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<VerifyMobileNumberRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(VerifyMobileNumberRes verifyMobileNumberRes) {
                        mUtils.getWebserviceLog(apiname + "_response", verifyMobileNumberRes);
                        if (verifyMobileNumberRes != null) {
                            if (verifyMobileNumberRes.getStatus().equalsIgnoreCase("true")) {
                                if (verifyMobileNumberRes.getResult() != null) {
                                    callback.onSuccess(verifyMobileNumberRes);
                                }
                            } else {
                                if (verifyMobileNumberRes.getMessage() != null) {
                                    callback.onFailure(verifyMobileNumberRes, true);
                                } else {
                                    callback.onFailure(verifyMobileNumberRes, false);
                                }
                            }
                        }

                    }
                });
    }

    public Subscription getDisplayFeedback(CommonReq obj, final String apiname, final GetDisplayFeedbackCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getDisplayFeedback(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends DisplayFeedbackRes>>() {
                    @Override
                    public Observable<? extends DisplayFeedbackRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<DisplayFeedbackRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(DisplayFeedbackRes displayFeedbackRes) {
                        mUtils.getWebserviceLog(apiname + "_response", displayFeedbackRes);
                        callback.onSuccess(displayFeedbackRes);

                        if (displayFeedbackRes.getStatus().equalsIgnoreCase("true")) {
                            if (displayFeedbackRes.getResult() != null) {
                                callback.onSuccess(displayFeedbackRes);
                            }
                        } else {
                            if (displayFeedbackRes.getMessage() != null) {
                                callback.onFailure(displayFeedbackRes, true);
                            } else {
                                callback.onFailure(displayFeedbackRes, false);
                            }
                        }

                    }
                });
    }


    public Subscription getDeleteFeedback(DeleteFeedbackReq obj, final String apiname, final GetDeleteFeedbackCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetDeleteFeedback(obj)
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

    public Subscription getInsertUpdateFeedback(InsertUpdateFeedbackReq obj, final String apiname, final GetInsertUpdateFeedbackCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetInsertUpdateFeedback(obj)
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

    public Subscription getDisplayComplaint(CommonReq obj, final String apiname, final GetDisplayComplaintCallback callback) {

        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.getDisplayComplaint(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetComplaintsHistoryRes>>() {
                    @Override
                    public Observable<? extends GetComplaintsHistoryRes> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GetComplaintsHistoryRes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(GetComplaintsHistoryRes getComplaintsHistoryRes) {
                        mUtils.getWebserviceLog(apiname + "_response", getComplaintsHistoryRes);
                        callback.onSuccess(getComplaintsHistoryRes);

                        if (getComplaintsHistoryRes.getStatus().equalsIgnoreCase("true")) {
                            if (getComplaintsHistoryRes.getFacility() != null) {
                                callback.onSuccess(getComplaintsHistoryRes);
                            }
                        } else {
                            if (getComplaintsHistoryRes.getMessage() != null) {
                                callback.onFailure(getComplaintsHistoryRes, true);
                            } else {
                                callback.onFailure(getComplaintsHistoryRes, false);
                            }
                        }

                    }
                });
    }

    public Subscription getDeleteComplaint(DeleteComplaintReq obj, final String apiname, final GetDeleteComplaintCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetDeleteComplaint(obj)
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

    public Subscription getInsertUpdateComplaint(InsertUpdateComplaintRequestReq obj, final String apiname, final GetInsertUpdateComplaintCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetInsertUpdateComplaint(obj)
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

    public Subscription getSendMISCount(SendMISCountReq obj, final String apiname, final GetSendMISCountCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetSendMISCount(obj)
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
                        }
                    }
                });
    }

    public Subscription getResources(GetResourceRequest obj, final String apiname, final GetResourcesCallback callback) {
        mUtils.getWebserviceLog(apiname + "_request", gson.toJson(obj));
        return networkService.apiGetResources(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetResourcesResponse>>() {
                    @Override
                    public Observable<? extends GetResourcesResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<GetResourcesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(GetResourcesResponse getResourcesResponse) {
                        mUtils.getWebserviceLog(apiname + "_response", getResourcesResponse);
                        if (getResourcesResponse.getData() != null) {
                            if (getResourcesResponse.isStatus()) {
                                if(!getResourcesResponse.getData().isEmpty()) {
                                    callback.onSuccess(getResourcesResponse);
                                }else{
                                    callback.onFailure(getResourcesResponse, true);
                                }
                            } else {
                                if (getResourcesResponse.getMessage() != null) {
                                    callback.onFailure(getResourcesResponse, true);
                                } else {
                                    callback.onFailure(getResourcesResponse, false);
                                }
                            }
                        } else {

                            callback.onFailure(getResourcesResponse, false);

                        }
                    }
                });
    }


    public interface GetOffersListCallback {
        void onSuccess(GetOffersRes getOffersRes);

        void onError(NetworkError networkError);
    }

    public interface GetProjectListCallback {
        void onSuccess(ProjectListingRes projectListingRes);

        void onError(NetworkError networkError);

        void onFailure(ProjectListingRes projectListingRes);
    }

    public interface GetProjectDetailsCallback {
        void onSuccess(ProjectDetailsRes projectDetailsRes);

        void onError(NetworkError networkError);

        void onFailure(ProjectDetailsRes projectDetailsRes);
    }

    public interface GetNotificationListCallback {
        void onSuccess(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes);

        void onError(NetworkError networkError);

        void onFailure(GetAllJumbleNotificationsRes getAllJumbleNotificationsRes);
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


    public interface GetProjectInterestedInCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetScheduleSiteVisitCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetLayoutDetailsCallback {
        void onSuccess(GetLayoutDetailsRes getLayoutDetailsRes);

        void onError(NetworkError networkError);

        void onFailure(GetLayoutDetailsRes getLayoutDetailsRes, boolean flag);

        void onFailure(GetLayoutDetailsRes getLayoutDetailsRes);
    }

    public interface GetMyProfileCallback {
        void onSuccess(MyProfileRes myProfileRes);

        void onError(NetworkError networkError);

        void onFailure(MyProfileRes myProfileRes, boolean flag);
    }

    public interface GetEditMyProfileCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetVerifyMobileNumberCallback {
        void onSuccess(VerifyMobileNumberRes verifyMobileNumberRes);

        void onError(NetworkError networkError);

        void onFailure(VerifyMobileNumberRes verifyMobileNumberRes, boolean flag);
    }

    public interface GetDisplayFeedbackCallback {
        void onSuccess(DisplayFeedbackRes displayFeedbackRes);

        void onError(NetworkError networkError);

        void onFailure(DisplayFeedbackRes displayFeedbackRes, boolean flag);
    }

    public interface GetDeleteFeedbackCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetInsertUpdateFeedbackCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetDisplayComplaintCallback {
        void onSuccess(GetComplaintsHistoryRes getComplaintsHistoryRes);

        void onError(NetworkError networkError);

        void onFailure(GetComplaintsHistoryRes getComplaintsHistoryRes, boolean flag);
    }

    public interface GetDeleteComplaintCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetInsertUpdateComplaintCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);

        void onFailure(CommonRes commonRes, boolean flag);
    }

    public interface GetSendMISCountCallback {
        void onSuccess(CommonRes commonRes);

        void onError(NetworkError networkError);
    }

    public interface GetResourcesCallback {
        void onSuccess(GetResourcesResponse getResourcesResponse);

        void onError(NetworkError networkError);

        void onFailure(GetResourcesResponse getResourcesResponse, boolean flag);
    }
}
