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
                    <span class="lead">
                        <c:choose>
                            <c:when test="${staff.staffId >0}">
                                Edit Staff
                            </c:when>
                            <c:otherwise>
                                Add Staff
                            </c:otherwise>
                        </c:choose>
                    </span><br>

                </div>
                <br>

                <form role="form" action="save" method="POST">
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Name</label>
                        <input class="form-control" id="textbox1" type="text" name="name" value="<c:out value="${staff.name}"/>"/>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Email</label>
                        <input class="form-control" id="textbox2" type="text"  name="email"  value="<c:out value="${staff.email}"/>" />
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Phone</label>
                        <input class="form-control" id="textbox1" type="text"  name="phone"  value="<c:out value="${staff.phone}"/>"/>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Gender</label>
                         <select class="form-control"  id="textbox1"  name="gender" >
                                <option value="MALE"  ${staff.gender ==  "MALE" ?"selected":""} >Male</option>
                                <option value="FEMALE"  ${staff.gender ==  "FEMALE" ?"selected":""} >Female</option>
                        </select>
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Branch</label>
                        <select class="form-control"  id="textbox1"  name="branchId" >
                            <c:forEach items="${branchList}" var="branch">
                                <option value="${branch.branchId}"  ${branch.branchId ==  staff.branch.branchId ?"selected":""} >${branch.name}</option>

                            </c:forEach>

                        </select>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Bood Group</label>
                        <input class="form-control" id="textbox2" type="text"  name="bloodGroup"  value="<c:out value="${staff.bloodGroup}"/>"/>
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Aadhaar No.</label>
                        <input class="form-control" id="textbox1" type="text"  name="aadhaarNo"  value="<c:out value="${staff.aadhaarNo}"/>"/>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">PAN No.</label>
                        <input class="form-control" id="textbox2" type="text"  name="panNo"  value="<c:out value="${staff.panNo}"/>"/>
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Father Name</label>
                        <input class="form-control" id="textbox1" type="text"  name="fatherName"  value="<c:out value="${staff.fatherName}"/>"/>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Mother Name</label>
                        <input class="form-control" id="textbox2" type="text"  name="motherName"  value="<c:out value="${staff.motherName}"/>"/>
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-6 form-group">
                        <label for="textbox1">Current Address</label>
                        <textarea class="form-control" id="textbox1"   name="currentAddr" ><c:out value="${staff.currentAddr}"/></textarea>
                    </div>
                    <div class="col-md-6 form-group">
                        <label for="textbox2">Permanent Address</label>
                        <textarea class="form-control" id="textbox2"  name="permanentAddr" ><c:out value="${staff.permanentAddr}"/> </textarea>
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-3 form-group">
                         <input type="hidden" 
                               name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input  type="hidden"  name="staffId"  value="<c:out value="${staff == null ?0:staff.staffId}"/>"/>
                        <button type="submit" class="btn btn-default">Submit</button>
                        <a  href="list" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>


