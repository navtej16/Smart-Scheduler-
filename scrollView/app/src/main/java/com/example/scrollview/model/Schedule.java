package com.example.scrollview.modal;

public class Schedule {
    private String code;
    private String name;
    private  String profName;
    private String venue;
    private String time;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProfName() {
        return profName;
    }

    public String getVenue() {
        return venue;
    }

    public String getTime() {
        return time;
    }

    public Schedule() {
        code = "MIN 106";
        name="Engineering Thermodynamics";
        profName = "Dhananshri M joglekar";
        venue = "LHC-205";
        time = "1:00-2:00";
    }

    public Schedule(String code, String name, String profName, String venue, String time) {
        this.code = code;
        this.name = name;
        this.profName = profName;
        this.venue = venue;
        this.time = time;
    }
}
