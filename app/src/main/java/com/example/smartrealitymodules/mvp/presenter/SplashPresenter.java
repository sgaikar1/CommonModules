package com.example.smartrealitymodules.mvp.presenter;

import android.os.Handler;
import android.view.animation.Animation;

import com.example.smartrealitymodules.api.NetworkError;
import com.example.smartrealitymodules.models.request.SendMISCountReq;
import com.example.smartrealitymodules.models.response.CommonRes;
import com.example.smartrealitymodules.mvp.model.MainModel;
import com.example.smartrealitymodules.mvp.view.SplashView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by user on 10/3/17.
 */
public class SplashPresenter {
    private final MainModel mainModel;
    private final CompositeSubscription subscriptions;
    private final SplashView view;
    int SPLASH_SCREEN_TIMOUT = 3000;

    public SplashPresenter(MainModel mainModel, SplashView view) {
        this.mainModel = mainModel;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getSendMISCount(SendMISCountReq obj, String apiname) {

        Subscription subscription = mainModel.getSendMISCount(obj, apiname, new MainModel.GetSendMISCountCallback() {
            @Override
            public void onSuccess(CommonRes commonRes) {

            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getAppErrorMessage());
            }


        });

        subscriptions.add(subscription);
    }

    public void startNavigation(final Animation animation, final Animation splashAnim) {
        if (animation == splashAnim) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.startNavigation();
                }
            }, SPLASH_SCREEN_TIMOUT);
        }
    }
}
