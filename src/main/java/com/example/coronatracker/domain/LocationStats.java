package com.example.coronatracker.domain;

public class LocationStats {
    private String state;
    private String country;
    private String region;
    private int totalNoCases;
    private int diffFromPrevDay;

    public LocationStats() {
    }

    public LocationStats(String state, String country, String region, int totalNoCases, int diffFromPrevDay) {
        this.state = state;
        this.country = country;
        this.region = region;
        this.totalNoCases = totalNoCases;
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getTotalNoCases() {
        return totalNoCases;
    }

    public void setTotalNoCases(int totalNoCases) {
        this.totalNoCases = totalNoCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", totalNoCases=" + totalNoCases +
                '}';
    }
}
