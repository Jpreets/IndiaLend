/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jaspreetsingh
 */
@Entity
@Table(name = "attendence")
public class Attendence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendence_id")
    private long attendenceId;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "chk_in_long", nullable = true)
    private double chkInLong;

    @Column(name = "chk_in_lat", nullable = true)
    private double chkInLat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkIn", nullable = true)
    private java.util.Date checkIn = new Date();

    @Column(name = "chk_out_long", nullable = true)
    private double chkOutLong;

    @Column(name = "chk_out_lat", nullable = true)
    private double chkOutLat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "checkOut", nullable = true)
    private java.util.Date checkOut = new Date();

    public long getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(long attendenceId) {
        this.attendenceId = attendenceId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    
    
}
