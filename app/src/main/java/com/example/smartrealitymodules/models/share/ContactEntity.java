package com.example.smartrealitymodules.models.share;

import java.util.ArrayList;

public class ContactEntity {
    String ConatactName;
    String ContactEmail;
    ArrayList<PhoneNumber> arrPhoneNumber;

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }

    public ArrayList<PhoneNumber> getArrPhoneNumber() {
        return arrPhoneNumber;
    }

    public void setArrPhoneNumber(ArrayList<PhoneNumber> arrPhoneNumber) {
        this.arrPhoneNumber = arrPhoneNumber;
    }

    public String getConatactName() {
        return ConatactName;
    }

    public void setConatactName(String conatactName) {
        ConatactName = conatactName;
    }
}
