/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import net.indialend.attendance.constant.LeaveType;
import net.indialend.attendance.constant.WorkingDay;

/**
 *
 * @author jass
 */
@Entity
@Table(name = "working_days")
public class WorkingDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workingDay_id")
    private long workingDayId;

    @Enumerated(EnumType.STRING)
    private WorkingDay workingDay;

    private boolean selected;

    private int minWorkingHour;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getWorkingDayId() {
        return workingDayId;
    }

    public void setWorkingDayId(long workingDayId) {
        this.workingDayId = workingDayId;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public int getMinWorkingHour() {
        return minWorkingHour;
    }

    public void setMinWorkingHour(int minWorkingHour) {
        this.minWorkingHour = minWorkingHour;
    }

}
