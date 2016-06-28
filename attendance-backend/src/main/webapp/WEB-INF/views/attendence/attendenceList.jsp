<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Users List</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
                    <span class="lead">Staff Attendence</span><br>

                   
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
            window.location.href = "edit?branchId=" + searchIDs[0];
        }

    });
    $('#delete').click(function () {
        var searchIDs = [];
        $('input:checked').map(function () {
            if ($(this).val() != "0")
                searchIDs.push($(this).val());
        });

        window.location.href = "delete?branchId=" + searchIDs;

    });

    $('#selectAll').click(function () {
        $('input:checkbox').prop('checked', $(this).is(':checked'));
    });
</script>

