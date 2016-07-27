package net.indialend.attendence.bean;

import java.util.Date;

/**
 * Created by jaspreetsingh on 7/22/16.
 */
public class Leave {
    private long leaveId;
    private String leaveDate;
    private String type;
    private String detail;

    public String getParamData(){
        return "&leaveDate="+leaveDate
                +"&detail="+detail;

    }

    public long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(long leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
