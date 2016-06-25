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
    </script>
</head>

<body>
    <div class="container">

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Attendence</a>
                </div>
                
            </div>
        </nav>

        <div class="generic-container" >
            <div class="panel panel-default" style="margin: auto;max-width: 500px">
                
                        
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <span class="lead">
                        Admin Login
                    </span><br>
                   

                </div>
                <br>
                 <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

                <form role="form" name='loginForm' method='POST'
                      action="<c:url value='${loginUrl}' />">
                    <div class="col-md-12  form-group">
                        <label for="textbox1">User Name :</label>
                        <input class="form-control" id="textbox1" type="text" name="username" />
                    </div>
                    <span class="clearfix"/>

                    <div class="col-md-12 form-group">
                        <label for="textbox1">Password :</label>
                        <input class="form-control" id="textbox1" type="password"  name="password"  />
                    </div>
                    <span class="clearfix"/>
                    <div class="col-md-3 form-group">
                        <input type="hidden" 
                               name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-default">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>


