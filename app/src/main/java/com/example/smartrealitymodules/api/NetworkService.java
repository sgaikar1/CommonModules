package com.example.smartrealitymodules.api;



import com.example.smartrealitymodules.models.CityListResponse;
import com.example.smartrealitymodules.models.request.CommonReq;
import com.example.smartrealitymodules.models.response.GetOffersRes;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();

    @Headers("encryptKey:nfzGgyg18wr9pQF6iS+IhXjgcrp7OjA17Bo/33u7ntk=")
    @POST(ApiNames.GetOffers)
    Observable<GetOffersRes> getOffersList(@Body CommonReq obj);
}
