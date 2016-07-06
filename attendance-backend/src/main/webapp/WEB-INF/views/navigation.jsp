<%-- 
    Document   : navigation
    Created on : Jun 25, 2016, 1:49:27 PM
    Author     : jaspreetsingh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- csrt support -->
<form action="../logout" method="post" id="logoutForm" style="display: none;">
    <input type="hidden" 
           name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Attendance</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="${pageContext.request.requestURI.contains('branch')?"active":""}"><a href="../branch/list">Branch</a></li>
            <li class="${pageContext.request.requestURI.contains('staff')?"active":""}"><a href="../staff/list">Staff</a></li>
            <li class="${pageContext.request.requestURI.contains('staff')?"active":""}"><a target="_blank" href="http://jazzkart-jazzkart.rhcloud.com/mobileapp/attendance.apk">Mobile App</a></li>

        </ul>
        <a class="navbar-brand pull-right" href="javascript:formSubmit()">Logout</a>


    </div>
</nav>