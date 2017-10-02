<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
		<title>SAMS | Login</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/bootstrap.min.css" />' >
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/sams-cust-bs.css" />' >
		
		<!-- Optional theme -->
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/bootstrap-theme.min.css" />' >
		<!-- Latest compiled and minified JavaScript -->
		<script src='<c:url value="/resources/bs-3.3.5/js/bootstrap.min.js" />'></script>
	</head>
	<body>
		<div>
			<div class="container">
	          <div class="row">
	            <div class="col-md-4 col-md-offset-4">
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">SAMS-Please Sign In</h3>
	                    </div>
	                    <div class="panel-body">
	                        <form role="form" name='f' method='POST' action='<c:url value="/static/j_spring_security_check" />'>
	                        	<c:if test="${not empty error}">
									<div>
										Your login attempt was not successful, try again.<br /> Caused :
										${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
									</div>
								</c:if>
	                            <fieldset>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="User Name" name='j_username' autofocus>
	                                </div>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
	                                </div>
	                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Login" />
	                            </fieldset>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	  </div>	
	</body>
</html>