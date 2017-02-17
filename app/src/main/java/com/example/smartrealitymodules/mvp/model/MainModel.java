package com.example.smartrealitymodules.mvp.model;



import com.example.smartrealitymodules.models.CityListResponse;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetOffersRes;
import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.api.NetworkService;

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

    public MainModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback) {

        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }

    public Subscription getOffersList(CommonReq obj, final GetOffersListCallback callback) {

        return networkService.getOffersList(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<?extends GetOffersRes>>() {
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

    public interface GetCityListCallback{
        void onSuccess(CityListResponse cityListResponse);

        void onError(NetworkError networkError);
    }

    public interface GetOffersListCallback{
        void onSuccess(GetOffersRes getOffersRes);

        void onError(NetworkError networkError);
    }
}
