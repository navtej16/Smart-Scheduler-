package com.example.scrollview.model;

import android.widget.ProgressBar;

import java.security.PrivateKey;

public class Attendence {
    private String code;
    private String name;
    private  String profName;
    private String number;
    private String emailID;
    private String profileURL;
    private String attendence;


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProfName() {
        return profName;
    }

    public String getNumber() {
        return number;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getAttendanceURL() {
        return attendence ;
    }


    public Attendence()
    {
        code = "MIN 106";
        name="Engineering Thermodynamics";
        profName = "Dhananshri M joglekar";
        attendence="20/90";
    }



    public Attendence(String code, String name, String profName, String number, String emailID, String profileURL,String attendence) {
        this.code = code;
        this.name = name;
        this.profName = profName;
        this.number = number;
        this.emailID = emailID;
        this.profileURL = profileURL;
        this.attendence = attendence;
    }



}
