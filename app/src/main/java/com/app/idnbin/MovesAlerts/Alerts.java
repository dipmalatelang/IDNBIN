package com.app.idnbin.MovesAlerts;

public class Alerts {

    private String alertPrice;
    public String time;

    public Alerts(){
    }

    public Alerts(String alertPrice, String time)
    {
        this.alertPrice = alertPrice;
        this.time = time;
    }

    public void setAlert(String alertPrice){
        this.alertPrice = alertPrice;
    }

    public String getAlertPrice(){
        return alertPrice;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTime(){
        return time;
    }
}
