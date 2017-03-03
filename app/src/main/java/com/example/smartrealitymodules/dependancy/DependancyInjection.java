package com.example.smartrealitymodules.dependancy;


import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.ui.activity.AboutDetailsActivity;
import com.example.smartrealitymodules.ui.activity.AboutUsActivity;
import com.example.smartrealitymodules.ui.activity.CalenderActivity;
import com.example.smartrealitymodules.ui.activity.ContactUsActivity;
import com.example.smartrealitymodules.ui.activity.LayoutActivity;
import com.example.smartrealitymodules.ui.activity.ListContact;
import com.example.smartrealitymodules.ui.activity.NotificationActivity;
import com.example.smartrealitymodules.ui.activity.OffersActivity;
import com.example.smartrealitymodules.ui.activity.ProjectAmenitiesActivity;
import com.example.smartrealitymodules.ui.activity.ProjectDetailsActivity;
import com.example.smartrealitymodules.ui.activity.ProjectListingActivity;
import com.example.smartrealitymodules.ui.activity.ReferFriendActivity;
import com.example.smartrealitymodules.ui.activity.ScheduleSiteVisitActivity;
import com.example.smartrealitymodules.ui.activity.ShareActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ennur on 6/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface DependancyInjection {
    void inject(ReferFriendActivity referFriendActivity);
    void inject(OffersActivity offersActivity);
    void inject(NotificationActivity notificationActivity);
    void inject(ContactUsActivity contactUsActivity);
    void inject(AboutUsActivity aboutUsActivity);
    void inject(AboutDetailsActivity aboutDetailsActivity);
    void inject(ShareActivity shareActivity);
    void inject(ListContact listContact);
    void inject(ProjectListingActivity projectListingActivity);
    void inject(ProjectDetailsActivity projectDetailsActivity);
    void inject(ScheduleSiteVisitActivity scheduleSiteVisitActivity);
    void inject(CalenderActivity calenderActivity);
    void inject(LayoutActivity layoutActivity);
    void inject(ProjectAmenitiesActivity projectAmenitiesActivity);
}
