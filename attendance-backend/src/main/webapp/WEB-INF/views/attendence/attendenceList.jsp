<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Attendance</title>
        <meta charset="utf-8">
        <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <script src="http://maps.google.com/maps/api/js?sensor=false&v=3.23">
        <link href = "<c:url value='/static/css/app.css' />" rel = "stylesheet" > < /link>
        </script>
    </head>

    <body>


        <div class="container">

            <jsp:include page="../navigation.jsp"/>

            <div class="generic-container">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <span class="lead">Last 30 Days Attendence of ${staff.name}</span><br>


                    </div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th width="200px">Date</th>
                                <th>Check IN</th>
                                <th>Check OUT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${attendenceList}" var="attendence">

                                <tr>
                                    <td><fmt:formatDate type="date" value="${attendence.checkIn}" /> </td>
                                    <td><fmt:formatDate type="time" value="${attendence.checkIn}" /> </br> 
                                        <a   id="myBtn_chk_in_${attendence.attendenceId}" >
                                            ( ${attendence.chkInLat} :  ${attendence.chkInLong})</a><br> 
                                            <div id="location_chk_in_${attendence.attendenceId}"></div>       
                                        <script>
                                                    $("#myBtn_chk_in_${attendence.attendenceId}").click(function () {
                                                selected.long = ${attendence.chkInLong};
                                                selected.lat = ${attendence.chkInLat};
                                                selected.date = '<fmt:formatDate type="date" value="${attendence.checkIn}" />';
                                                selected.type = "Check In";
                                                $("#myModal").modal();
                                            });

                                            $.ajax({
                                                type: "get",
                                                url: "https://maps.googleapis.com/maps/api/geocode/json",
                                                async: false,
                                                data: "latlng=${attendence.chkInLat},${attendence.chkInLong}",
                                                datatype: "json",
                                                success: function (data)
                                                {
                                                   $('#location_chk_in_${attendence.attendenceId}').text(data.results[0].formatted_address);
                                                }
                                            });

                                        </script> 
                                    </td>
                                    <td><fmt:formatDate type="time" value="${attendence.checkOut}" />  <br> 
                                        <a   id="myBtn_chk_out_${attendence.attendenceId}" >
                                            (${attendence.chkInLong} : ${attendence.chkInLat})</a><br> 
                                            <div id="location_chk_out_${attendence.attendenceId}"></div>       

                                        <script>
                                            $("#myBtn_chk_out_${attendence.attendenceId}").click(function () {
                                                selected.long = ${attendence.chkOutLong};
                                                selected.lat = ${attendence.chkOutLat};
                                                selected.date = '<fmt:formatDate type="date" value="${attendence.checkIn}" />';
                                                selected.type = "Check Out";
                                                $("#myModal").modal();
                                            });
                                            
                                            $.ajax({
                                                type: "get",
                                                url: "https://maps.googleapis.com/maps/api/geocode/json",
                                                async: false,
                                                data: "latlng=${attendence.chkInLat},${attendence.chkInLong}",
                                                datatype: "json",
                                                success: function (data)
                                                {
                                                   $('#location_chk_out_${attendence.attendenceId}').text(data.results[0].formatted_address);
                                                }
                                            });
                                        </script> 

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <div class="modal fade fullscreen-modal" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <div id="title" style="float: left;">Login :</div>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div id="dvMap" style="width: 100%; height: 500px">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
<script>
    var selected = {};
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

    function initMap(latitude, longitude) {
        var latlng = new google.maps.LatLng(latitude, longitude);
        var myOptions = {
            zoom: 15,
            center: latlng
        };
        var map = new google.maps.Map(document.getElementById('dvMap'), myOptions);
        var marker = new google.maps.Marker({
            position: latlng,
            map: map

        });
    }
    $(document).ready(function () {


        $("#myModal").on("shown.bs.modal", function () {
            initMap(selected.lat, selected.long);
            $('#title').text('' + selected.type + ' : ' + selected.date);

        });
    });
</script>

