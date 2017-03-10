package com.example.smartrealitymodules.dependancy;


import com.example.smartrealitymodules.api.NetworkModule;
import com.example.smartrealitymodules.ui.activity.AboutDetailsActivity;
import com.example.smartrealitymodules.ui.activity.AboutUsActivity;
import com.example.smartrealitymodules.ui.activity.CalenderActivity;
import com.example.smartrealitymodules.ui.activity.ComplaintActivity;
import com.example.smartrealitymodules.ui.activity.ComplaintDetailsActivity;
import com.example.smartrealitymodules.ui.activity.ComplaintsHistoryActivity;
import com.example.smartrealitymodules.ui.activity.ContactUsActivity;
import com.example.smartrealitymodules.ui.activity.EditUserProfileActivity;
import com.example.smartrealitymodules.ui.activity.FeedBackActivity;
import com.example.smartrealitymodules.ui.activity.FeedbackDetailsActivity;
import com.example.smartrealitymodules.ui.activity.FeedbackHistoryActivity;
import com.example.smartrealitymodules.ui.activity.LayoutActivity;
import com.example.smartrealitymodules.ui.activity.ListContact;
import com.example.smartrealitymodules.ui.activity.NotificationActivity;
import com.example.smartrealitymodules.ui.activity.OffersActivity;
import com.example.smartrealitymodules.ui.activity.ProjectAmenitiesActivity;
import com.example.smartrealitymodules.ui.activity.ProjectDetailsActivity;
import com.example.smartrealitymodules.ui.activity.ProjectListingActivity;
import com.example.smartrealitymodules.ui.activity.ReferFriendActivity;
import com.example.smartrealitymodules.ui.activity.ResourcesActivity;
import com.example.smartrealitymodules.ui.activity.ScheduleSiteVisitActivity;
import com.example.smartrealitymodules.ui.activity.ShareActivity;
import com.example.smartrealitymodules.ui.activity.SplashFadeInActivity;
import com.example.smartrealitymodules.ui.activity.SplashVideoActivity;
import com.example.smartrealitymodules.ui.activity.UserProfileActivity;
import com.example.smartrealitymodules.ui.fragments.OTPCodeFragment;
import com.example.smartrealitymodules.ui.fragments.OTPMobileNumberFragment;

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
    void inject(UserProfileActivity userProfileActivity);
    void inject(EditUserProfileActivity editUserProfileActivity);
    void inject(OTPMobileNumberFragment otpMobileNumberFragment);
    void inject(OTPCodeFragment otpCodeFragment);
    void inject(FeedBackActivity feedBackActivity);
    void inject(FeedbackHistoryActivity feedbackHistoryActivity);
    void inject(FeedbackDetailsActivity feedbackDetailsActivity);
    void inject(ComplaintsHistoryActivity complaintsHistoryActivity);
    void inject(ComplaintActivity complaintActivity);
    void inject(ComplaintDetailsActivity complaintDetailsActivity);
    void inject(SplashFadeInActivity splashFadeInActivity);
    void inject(SplashVideoActivity splashVideoActivity);
    void inject(ResourcesActivity resourcesActivity);
}
