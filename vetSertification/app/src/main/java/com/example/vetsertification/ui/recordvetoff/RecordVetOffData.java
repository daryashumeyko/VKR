package com.example.vetsertification.ui.recordvetoff;

import java.sql.Time;
import java.util.Date;

public class RecordVetOffData {

    private String vetOff;
    private Date date;
    private Time time;

    public String getVetOff() {
        return vetOff;
    }

    public void setVetOff(String vetOff) {
        this.vetOff = vetOff;
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
