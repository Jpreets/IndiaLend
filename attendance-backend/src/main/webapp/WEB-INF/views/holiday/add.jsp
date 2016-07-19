<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Holiday</title>
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
                    <span class="lead">
                        <c:choose>
                            <c:when test="${holiday.holidayId >0}">
                                Edit Holiday
                            </c:when>
                            <c:otherwise>
                                Add Holiday
                            </c:otherwise>
                        </c:choose>
                    </span><br>

                </div>
                <br>

                <form role="form" action="save" method="POST">
                    <div class="col-md-6 form-group">
                        
                        <label for="textbox1">Date</label>
                        <input class="form-control"  id="datepicker" type="text" name="holidayDate" />
                         <script>

                                $("#datepicker").datepicker({
                                    dateFormat:"yy/mm/dd",
                                    
                                });
                                var setDate= new Date('${holiday.holidayDate}');
                                
                                $("#datepicker").datepicker('setDate', setDate);

                            </script>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Detail</label>
                        <input class="form-control" id="textbox2" type="text"  name="detail"  value="<c:out value="${holiday.detail}"/>" />
                    </div>
                    
                    <span class="clearfix"/>
                    <div class="col-md-3 form-group">
                         <input type="hidden" 
                               name="${_csrf.parameterName}" value="${_csrf.token}" />
                         <input  type="hidden"  name="holidayId"  value="<c:out value="${holiday == null ?0:holiday.holidayId}"/>"/>
                        <button type="submit" class="btn btn-default">Submit</button>
                        <a  href="list" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>


