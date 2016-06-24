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
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
        <script src="http://maps.google.com/maps/api/js?sensor=false&v=3.23">
        </script>
    </head>

    <body>
        <div class="container">

            <div class="generic-container">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">
                        <span class="lead">List of Users </span>
                        <span style="float: right;" > <a  style="width: 95px !important;" href="http://jazzkart-jazzkart.rhcloud.com/mobileapp/indialend.apk"  target="_blank" class="btn btn-danger custom-width">Mobile App</a></span>
                    </div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Gender</th>
                                <th>Active</th>
                                <th width="100"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.gender}</td>
                                    <td>${user.active}</td>
                                    <td> 
                                        <c:if test="${user.active}">
                                            <button type="button"  id="myDABtn_${user.phone}" class="btn btn-danger custom-width">Deactivate</button>
                                            <script>
                                            $("#myDABtn_${user.phone}").click(function () {
                                                var r = confirm(" Are you sure you want to deactivate "+ this.parentElement.parentElement.children[0].innerText);
                                                if (r == true) {
                                                   var deactivatePhone = this.parentElement.parentElement.children[2].innerText;

                                                   window.location="deactivate-"+deactivatePhone; 
                                                } 
                                                
                                                
                                            });
                                        </script> 
                                        
                                        <button type="button"  id="myBtn_${user.phone}" class="btn btn-danger custom-width">Track</button>
                                        <script>
                                            $("#myBtn_${user.phone}").click(function () {
                                                $("#title").text(this.parentElement.parentElement.children[0].innerText);
                                                selectedPhone = this.parentElement.parentElement.children[2].innerText;
                                                $("#myModal").modal();
                                            });
                                        </script> 
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>


            <div class="modal fade fullscreen-modal" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
                            <h4 class="modal-title">
                                <div id="title" style="float: left;">Modal Header</div>
                                <span style="float: right;" > Date: <input type="text" id="datepicker" ></span>
                            </h4>
                            <script>
                                $(function() {
                               
                                $("#datepicker").datepicker({
                                    dateFormat:"yy/mm/dd",
                                    
                                    onSelect: function(dateText) {
                                            selectedDate = dateText;
                                            doInitialize();
                                    }
                                });
                                $("#datepicker").datepicker("setDate", "-0d");
                                selectedDate= $("#datepicker").val();
                            });  
                            </script>
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
        </div>
    </body>
</html>
<script>

            var loopStarted = false;
            var selectedPhone;
            var selectedDate;
            var interval = 5000;
            var map;
            var marker;
            function initMap(latitude, longitude) {
            var latlng = new google.maps.LatLng(latitude, longitude);
                    var myOptions = {
                    zoom: 15,
                            center: latlng
                    };
                    map = new google.maps.Map(document.getElementById('dvMap'), myOptions);
                    marker = new google.maps.Marker({
                    position: latlng,
                            map: map

                    });
            }

    function updateMap(latitude, longitude) {
    var latlng = new google.maps.LatLng(latitude, longitude);
            marker.setPosition(latlng);
//        map.setCenter(latlng);

    }

    function updateMaps(locations) {
    var path = [];
            var i = 0;
            for (i = 0; i < locations.length; i++) {

    path.push(new google.maps.LatLng(locations[i].latitude, locations[i].longitute));
    }
    var polyline = new google.maps.Polyline({
    strokeColor: "#0000FF", // blue (RRGGBB, R=red, G=green, B=blue)
            strokeOpacity: 0.4, // opacity of line
    });
            if (path.length > 1) {
    // display the polyline once it has more than one point

    polyline.setPath(path);
            polyline.setMap(map);
    }

    var latlng = new google.maps.LatLng(locations[i - 1].latitude, locations[i - 1].longitute);
            marker.setPosition(latlng);
//        map.setCenter(latlng);

    }

    function doUpdate() {

    $.ajax({
            type: "get",
            url: "user",
            async: false,
            data: "phone=" + selectedPhone+"&selectedDate="+selectedDate,
            datatype: "json",
            success: function (data)
            {
            if (data.userLocations.length > 0) {
            updateMaps(data.userLocations);
            } else {
            updateMap(data.latitude, data.longitute);
            }
            },
            complete: function (data) {
            // Schedule the next
            setTimeout(doUpdate, interval);
            }
    });
    }


    function doInitialize() {
    $.ajax({
    type: "get",
            url: "user",
            async: false,
            data: "phone=" + selectedPhone+"&selectedDate="+selectedDate,
            datatype: "json",
            success: function (data)
            {
            initMap(data.latitude, data.longitute);
                    if (!loopStarted) {
            doUpdate();
                    loopStarted = true;
            }
            }
    });
    }

    $(document).ready(function () {
    $("#myModal").on("shown.bs.modal", function () {
    doInitialize();
    });
    });

</script>