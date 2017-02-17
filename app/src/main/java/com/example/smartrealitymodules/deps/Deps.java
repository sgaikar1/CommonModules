package com.example.smartrealitymodules.deps;


import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.ui.activity.HomeActivity;
import com.example.smartrealitymodules.ui.activity.OffersActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ennur on 6/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
    void inject(OffersActivity offersActivity);
}
