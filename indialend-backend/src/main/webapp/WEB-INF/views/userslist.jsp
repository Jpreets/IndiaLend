<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Users List</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
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
                    <div class="panel-heading"><span class="lead">List of Users </span></div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Gender</th>
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
                                    <td> <button type="button"  id="myBtn" class="btn btn-danger custom-width">Track</button></td>
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
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
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


    var selectedPhone;
    function initMap(a, b) {
        var latlng = new google.maps.LatLng(a, b);
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

            var interval = 5000;
            function doAjax()
            {
                $.ajax({
                    type: "get",
                    url: "user",
                    async: false,
                    data: "phone="+selectedPhone ,
                    datatype: "json",
                    success: function (data)
                    {
                        initMap(data.latitude, data.longitute);
                    },
                    complete: function (data) {
                        // Schedule the next
                        setTimeout(doAjax, interval);
                    }
                });
            }
            doAjax();
            
        });
        $("#myBtn").click(function () {
            selectedPhone = this.parentElement.parentElement.children[2].innerText;

            $("#myModal").modal();

        });
    });

</script>