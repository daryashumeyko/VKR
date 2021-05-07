package com.example.vetsertification.ui.recordrosselchoz;

import java.sql.Time;
import java.util.Date;

public class RecordRosselchozData {

    private String rosselchoz;
    private Date date;
    private Time time;

    public String getRosselchoz() {
        return rosselchoz;
    }

    public void setRosselchoz(String rosselchoz) {
        this.rosselchoz = rosselchoz;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}