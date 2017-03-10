package com.example.smartrealitymodules.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/3/17.
 */
public class MyProfileRes {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("result")
    public Result result;

    public static class Products {
        @SerializedName("ProductImage")
        public String ProductImage;
        @SerializedName("ProductTitle")
        public String ProductTitle;

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String productImage) {
            ProductImage = productImage;
        }

        public String getProductTitle() {
            return ProductTitle;
        }

        public void setProductTitle(String productTitle) {
            ProductTitle = productTitle;
        }
    }

    public static class Result {
        @SerializedName("Name")
        public String Name;
        @SerializedName("Points")
        public String Points;
        @SerializedName("ProfilePhoto")
        public String ProfilePhoto;
        @SerializedName("MobileNo")
        public String MobileNo;
        @SerializedName("AltMobileNo")
        public String AltMobileNo;
        @SerializedName("EmailID")
        public String EmailID;
        @SerializedName("DOB")
        public String DOB;
        @SerializedName("MaritalStatus")
        public String MaritalStatus;
        @SerializedName("Anniversary")
        public String Anniversary;
        @SerializedName("Address")
        public String Address;
        @SerializedName("PinCode")
        public String PinCode;
        @SerializedName("Products")
        public List<Products> Products;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPoints() {
            return Points;
        }

        public void setPoints(String points) {
            Points = points;
        }

        public String getProfilePhoto() {
            return ProfilePhoto;
        }

        public void setProfilePhoto(String profilePhoto) {
            ProfilePhoto = profilePhoto;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }

        public String getAltMobileNo() {
            return AltMobileNo;
        }

        public void setAltMobileNo(String altMobileNo) {
            AltMobileNo = altMobileNo;
        }

        public String getEmailID() {
            return EmailID;
        }

        public void setEmailID(String emailID) {
            EmailID = emailID;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            MaritalStatus = maritalStatus;
        }

        public String getAnniversary() {
            return Anniversary;
        }

        public void setAnniversary(String anniversary) {
            Anniversary = anniversary;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String pinCode) {
            PinCode = pinCode;
        }

        public List<MyProfileRes.Products> getProducts() {
            return Products;
        }

        public void setProducts(List<MyProfileRes.Products> products) {
            Products = products;
        }
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
