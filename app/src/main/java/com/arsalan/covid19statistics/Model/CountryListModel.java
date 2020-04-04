package com.arsalan.covid19statistics.Model;

import java.util.ArrayList;

public class CountryListModel {
    String countryname;
    int Totalcase;
    String date;
    String time;
    String newcase;
    int active;
    int critical;
    int recovered;
    String newdeath;
    int totaldeath;

    public CountryListModel(String countryname, int totalcase, String date, String time, String newcase, int active, int critical, int recovered, String newdeath, int totaldeath) {
        this.countryname = countryname;
        Totalcase = totalcase;
        this.date = date;
        this.time = time;
        this.newcase = newcase;
        this.active = active;
        this.critical = critical;
        this.recovered = recovered;
        this.newdeath = newdeath;
        this.totaldeath = totaldeath;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public int getTotalcase() {
        return Totalcase;
    }

    public void setTotalcase(int totalcase) {
        Totalcase = totalcase;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNewcase() {
        return newcase;
    }

    public void setNewcase(String newcase) {
        this.newcase = newcase;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public String getNewdeath() {
        return newdeath;
    }

    public void setNewdeath(String newdeath) {
        this.newdeath = newdeath;
    }

    public int getTotaldeath() {
        return totaldeath;
    }

    public void setTotaldeath(int totaldeath) {
        this.totaldeath = totaldeath;
    }
}
