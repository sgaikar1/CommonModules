package com.example.smartrealitymodules.models.response;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.smartrealitymodules.utils.Utils;

import java.util.ArrayList;

/**
 * Created by vijay on 28/11/16.
 */

public class GetOffersRes extends BaseObservable {

    public String status;
    public String message;
    public ArrayList<Result> result = new ArrayList<Result>();

    public GetOffersRes(String status, String message, ArrayList<Result> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public class Result {

        public String OfferID;
        public String Title;
        public String Description;
        public String EndDate;
        public String OfferImage;

        public Result(String offerID, String title, String description, String endDate, String offerImage) {
            OfferID = offerID;
            Title = title;
            Description = description;
            EndDate = endDate;
            OfferImage = offerImage;
        }

        public String getOfferID() {
            return OfferID;
        }

        public void setOfferID(String offerID) {
            OfferID = offerID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String endDate) {
            EndDate = endDate;
        }

        public String getOfferImage() {
            return OfferImage;
        }

        public void setOfferImage(String offerImage) {
            OfferImage = offerImage;
        }

    }
//    @BindingAdapter({"Bind:offerImageUrl"})
//    public static void loadImage(ImageView view, String offerImageUrl) {
//        Utils mUtils = new Utils();
//        mUtils.loadImageInImageview(view.getContext(),offerImageUrl,view);
//    }
}
