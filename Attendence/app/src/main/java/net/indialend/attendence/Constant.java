package net.indialend.attendence;

/**
 * Created by jaspreetsingh on 7/1/16.
 */
public interface Constant {

//    String attendenceUrl = "http://jazzkart-jazzkart.rhcloud.com/attendence-backend/attendence";
//    String loginUrl = "http://jazzkart-jazzkart.rhcloud.com/attendence-backend/staff/login";

    String domain ="http://32372cbc.ngrok.io/attendance-backend";

    String attendenceUrl = domain+"/attendence/save";
    String loginUrl = domain+"/attendence/login";
}
