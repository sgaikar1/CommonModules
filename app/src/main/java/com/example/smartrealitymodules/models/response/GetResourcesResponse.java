package com.example.smartrealitymodules.models.response;

/**
 * Created by user on 10/3/17.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/9/16.
 */
public class GetResourcesResponse {

    boolean status;
    public String message;
    public ArrayList<Data> data = new ArrayList<Data>();
    public String code;

    public GetResourcesResponse(boolean status, String message, ArrayList<Data> data, String code) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public class Data {

        public String department_id;
        public String department_name;
        public String image;
        public List<Number> contact_no = new ArrayList<Number>();

        public Data(String department_id, String department_name, String image, List<Number> contact_no) {
            this.department_id = department_id;
            this.department_name = department_name;
            this.image = image;
            this.contact_no = contact_no;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<Number> getContact_no() {
            return contact_no;
        }

        public void setContact_no(List<Number> contact_no) {
            this.contact_no = contact_no;
        }
    }

    public class Number {

        public String number;

        public Number(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}