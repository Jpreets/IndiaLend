/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendence.bean;

import java.util.Date;

/**
 *
 * @author jaspreetsingh
 */

public class Attendence {


    private long attendenceId;

    private long staffId;

    private double chkInLong;
    private double chkInLat;
    private Date checkIn = new Date();

    private double chkOutLong;
    private double chkOutLat;
    private Date checkOut = new Date();

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public long getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(long attendenceId) {
        this.attendenceId = attendenceId;
    }


    public double getChkInLong() {
        return chkInLong;
    }

    public void setChkInLong(double chkInLong) {
        this.chkInLong = chkInLong;
    }

    public double getChkInLat() {
        return chkInLat;
    }

    public void setChkInLat(double chkInLat) {
        this.chkInLat = chkInLat;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public double getChkOutLong() {
        return chkOutLong;
    }

    public void setChkOutLong(double chkOutLong) {
        this.chkOutLong = chkOutLong;
    }

    public double getChkOutLat() {
        return chkOutLat;
    }

    public void setChkOutLat(double chkOutLat) {
        this.chkOutLat = chkOutLat;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getParamData(){
        return "staffId="+staffId
                +"&attendenceId="+attendenceId
                +"&chkInLong="+chkInLong
                +"&chkInLat="+chkInLat
                +"&checkIn="+checkIn
                +"&chkOutLong="+chkOutLong
                +"&chkOutLat="+chkOutLat
                +"&checkOut="+checkOut;

    }
    
    
}
