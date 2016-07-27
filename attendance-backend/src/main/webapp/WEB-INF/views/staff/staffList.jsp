<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Staff</title>
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
                    <span class="lead">List of Staff </span><br>

                    <a  style="width: 95px !important;" 
                        href="edit" 
                        class="btn btn-default">Add</a>

                    <a  style="width: 95px !important;" 
                        id="edit" 
                        class="btn btn-default">Edit</a>

                    <a  style="width: 95px !important;" 
                        id="delete" 
                        class="btn btn-default">Delete</a>
                    <a  style="width: 95px !important;" 
                        id="attendence" 
                        class="btn btn-default">Attendence</a>
                    <a  style="width: 95px !important;" 
                        id="leave" 
                        class="btn btn-default">Leave</a>
                </div>
                <div style="overflow-x:auto;" class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th><input id="selectAll" type="checkbox" value="0" ></th>
                                <th>Staff ID</th>
                                <th>Name</th>
                                <th>email</th>
                                <th>gender</th>
                                <th>phone</th>
                                <th>Branch</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${staffList}" var="staff">
                                <tr>
                                    <td><input type="checkbox" value="${staff.staffId}"></td>
                                    <td>${staff.staffId}</td>
                                    <td>${staff.name}</td>
                                    <td>${staff.email}</td>
                                    <td>${staff.gender}</td>
                                    <td>${staff.phone}</td>
                                    <td>${staff.branch.name}</td>
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
<script>
    $('#edit').click(function () {
        var searchIDs = [];
        $('input:checked').map(function () {
            if ($(this).val() != "0")
                searchIDs.push($(this).val());
        });
        if (searchIDs.length == 1) {
            window.location.href = "edit?staffId=" + searchIDs[0];
        } else {
            alert("No record selected");
        }

    });

    $('#attendence').click(function () {
        var searchIDs = [];
        $('input:checked').map(function () {
            if ($(this).val() != "0")
                searchIDs.push($(this).val());
        });
        if (searchIDs.length == 1) {
            window.location.href = "../api/attendanceList?staffId=" + searchIDs[0];
        } else {
            alert("No record selected");
        }

    });

    $('#leave').click(function () {
        var searchIDs = [];
        $('input:checked').map(function () {
            if ($(this).val() != "0")
                searchIDs.push($(this).val());
        });
        if (searchIDs.length == 1) {
            window.location.href = "../api/leaveList?staffId=" + searchIDs[0];
        } else {
            alert("No record selected");
        }

    });

    $('#delete').click(function () {
        var searchIDs = [];
        $('input:checked').map(function () {
            if ($(this).val() != "0")
                searchIDs.push($(this).val());
        });

        if (searchIDs.length == 0) {
            alert("No record selected");
        } else {
            window.location.href = "delete?staffId=" + searchIDs;
        }
    });

    $('#selectAll').click(function () {
        $('input:checkbox').prop('checked', $(this).is(':checked'));
    });
</script>

