package net.indialend.attendence;

/**
 * Created by jaspreetsingh on 7/1/16.
 */
public interface Constant {

    String domain ="http://1819114b.ngrok.io/attendance-backend";
//    String domain ="http://jazzkart-jazzkart.rhcloud.com/attendance-backend";


    String attendenceUrl = domain+"/api/saveAttendence";
    String attendenceStatusUrl = domain+"/api/attendenceData";
    String staffDetailUrl = domain+"/api/staffDetail";
    String loginUrl = domain+"/api/login";
    String staffUpdateUrl = domain+"/api/updateStaff";
    String leaveDataUrl = domain+"/api/leaveData";
    String leaveSaveUrl = domain+"/api/saveLeave";

}
