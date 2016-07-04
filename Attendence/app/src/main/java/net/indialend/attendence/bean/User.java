package net.indialend.attendence.bean;

/**
 * Created by jaspreetsingh on 6/29/16.
 */
public class User {
    private String staffId;
    private String password;
    private String attendenceId;
    private String gcmToken;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(String attendenceId) {
        this.attendenceId = attendenceId;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    public String getParamData(){
        return "staffId="+staffId+"&attendenceId="+attendenceId+"&gcmToken="+gcmToken+"&password="+password;

    }
}
