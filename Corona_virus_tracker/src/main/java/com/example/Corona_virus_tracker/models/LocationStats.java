package com.example.Corona_virus_tracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int latestNoOfCases;
    private int increaseFromPrevDay;

    public int getIncreaseFromPrevDay() {
        return increaseFromPrevDay;
    }

    public void setIncreaseFromPrevDay(int increaseFromPrevDay) {
        increaseFromPrevDay = increaseFromPrevDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestNoOfCases() {
        return latestNoOfCases;
    }

    public void setLatestNoOfCases(int latestNoOfCases) {
        this.latestNoOfCases = latestNoOfCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestNoOfCases=" + latestNoOfCases +
                '}';
    }
}
