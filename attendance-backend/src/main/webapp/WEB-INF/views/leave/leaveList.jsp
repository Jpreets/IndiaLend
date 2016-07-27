<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Leave</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        </script>
    </head>

    <body>


        <div class="container">

            <jsp:include page="../navigation.jsp"/>

            <div class="generic-container">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <span class="lead"><%= Calendar.getInstance().get(Calendar.YEAR)%> Year Leave of ${staff.name}</span><br>


                    </div>
                    <div style="overflow-x:auto;" class="table-responsive">

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th width="200px">Date</th>
                                    <th>Type</th>
                                    <th>Detail</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${leaveList}" var="leave">

                                    <tr>
                                        <td><fmt:formatDate type="date" value="${leave.leaveDate}" /> </td>
                                        <td>${leave.type}</td>
                                        <td>${leave.detail}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>


