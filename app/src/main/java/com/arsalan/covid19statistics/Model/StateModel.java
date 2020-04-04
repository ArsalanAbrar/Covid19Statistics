package com.arsalan.covid19statistics.Model;

public class StateModel {
    String statename;
    String active;
    String confirmed;
    String death;
    String recovered;
    String deltaconfirm;
    String delarecover;
    String deladeath;

    public StateModel(String statename, String active, String confirmed, String death, String recovered, String deltaconfirm, String delarecover, String deladeath) {
        this.statename = statename;
        this.active = active;
        this.confirmed = confirmed;
        this.death = death;
        this.recovered = recovered;
        this.deltaconfirm = deltaconfirm;
        this.delarecover = delarecover;
        this.deladeath = deladeath;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeltaconfirm() {
        return deltaconfirm;
    }

    public void setDeltaconfirm(String deltaconfirm) {
        this.deltaconfirm = deltaconfirm;
    }

    public String getDelarecover() {
        return delarecover;
    }

    public void setDelarecover(String delarecover) {
        this.delarecover = delarecover;
    }

    public String getDeladeath() {
        return deladeath;
    }

    public void setDeladeath(String deladeath) {
        this.deladeath = deladeath;
    }
}
