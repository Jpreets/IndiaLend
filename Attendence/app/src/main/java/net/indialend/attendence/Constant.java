package net.indialend.attendence;

/**
 * Created by jaspreetsingh on 7/1/16.
 */
public interface Constant {

//    String domain ="http://2a921b9f.ngrok.io/attendance-backend";
    String domain ="http://jazzkart-jazzkart.rhcloud.com/attendance-backend";


    String attendenceUrl = domain+"/attendence/save";
    String attendenceStatusUrl = domain+"/attendence/data";
    String staffDetailUrl = domain+"/attendence/staffDetail";
    String loginUrl = domain+"/attendence/login";
    String staffUpdateUrl = domain+"/attendence/updateStaff";

}
