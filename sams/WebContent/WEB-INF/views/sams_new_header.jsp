<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
		<%-- 
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/sams-cust-bs.css" />' >
		
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/menu/metisMenu.min.css" />' >
		--%>
		<!-- Optional theme -->
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/bootstrap-theme.min.css" />' >
		<link rel="stylesheet" href='<c:url value="/resources/bs-3.3.5/css/sams-cust-bs.css" />' >
		
		<!-- JQuery -->
		
		<script src='<c:url value="/resources/js/jquery/1.11.3.min.js" />'></script>
		
		<!-- Latest compiled and minified JavaScript -->
		<script src='<c:url value="/resources/bs-3.3.5/js/bootstrap.min.js" />'></script>
	</head>
	<body>
		 <div id="wrapper">
		 	<!-- Navigation -->
        	<nav class="navbar navbar-default" role="navigation">
        		<div class="container">
        		<div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="index.html">SAMS 2.0</a>
            	</div>
            	<!-- /.navbar-header -->
			    <div id="navbar" class="collapse navbar-collapse">
			    	 <ul class="nav navbar-nav">
						<security:authorize access="hasRole('ROLE_ENQUIRY_TAB')">
							<li <c:if test="${tabName=='enquiry'}">class="active"</c:if>><a href="<c:url value="/enquiry/dashboard" />">Enquiry</a></li>
						</security:authorize>	
						<security:authorize access="hasRole('ROLE_ADMISSION')">
							<li <c:if test="${tabName=='admission'}">class="active"</c:if>><a href="<c:url value="/admission/dashboard" />">Admission</a></li>
						</security:authorize>
					    <c:set var="showFeeDashboard" value="false" />
			         	<security:authorize access="hasRole('ROLE_FEE')">
				         	<li <c:if test="${tabName=='fee'}">class="active"</c:if>>
				         		<a href="<c:url value="/fee/dashboard" />">Fee</a>
				         	</li>
					         <c:set var="showFeeDashboard" value="true" />
				        </security:authorize>
				         <c:if test="${showFeeDashboard==false}">
				         	<security:authorize access="hasRole('ROLE_CUST_STUD_FEE')">
				         		<c:set var="showFeeDashboard" value="true" />
				         		<li <c:if test="${tabName=='fee'}">class="active"</c:if>>
				         			<a href="<c:url value="/fee/default" />">Fee</a>
				         		</li>
				         	</security:authorize>
				         </c:if>        
						 <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
							<li <c:if test="${tabName=='admin'}">class="active"</c:if>><a href="<c:url value="/admin" />">Settings</a></li>
						</security:authorize>
			        </ul>
			        <ul class="nav navbar-nav navbar-right">
               			<li>User name</li>
                    </ul>
                    <!-- /.dropdown-user -->
			    </div>
			    </div>
        	</nav>
		 	<div class="container">
		 			<div class="row">
		 				<div class="col-lg-12">
		 					<h1 class="page-header">Admission</h1>
						</div>
			 		</div>	
			 		
			 		<div class="row">
		 				<div class="col-lg-8">
		 					<div class="panel panel-default">
		 						<div class="panel-heading"> Student Personal Information </div>
		 						<div class="panel-body"> 
		 							<div class="row">
		 								<div class="col-lg-12">
		 									<form role="form" class="form-horizontal">
		 										<div class="form-group">
		 											<label for="inputEmail" class="control-label col-md-3">First Name</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Middle Namae</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Last Name</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Father's Name</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Mother's Name</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">DOB</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Gender</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
											        <label for="inputEmail" class="control-label col-md-3">Category</label>
		 											 <div class="col-md-3">
            											<input type="email" class="form-control" id="inputEmail" placeholder="Email">
											        </div>
		 										</div>
		 									</form>
		 									
		 								</div>
		 							</div>
		 						 </div>
		 					</div>
		 					
						</div>
			 			<div class="col-lg-4">
			 				<div class="panel panel-default">
			 					<div class="panel-heading"> Current Class </div>
		 						<div class="panel-body">
		 							<form role="form" class="form-vertical">
		 								<div class="form-group">
											<label>University</label>
											<select class="form-control">
												<option>Select University</option>
											</select>
										
										
											<label>Class</label>
											<select class="form-control">
												<option>Select Class</option>
											</select>
										
											<label>Year/Semester</label>
											<select class="form-control">
												<option>Select year/semester</option>
											</select>
										</div>
		 							</form>
		 						</div>
			 				</div>
			 			</div>
			 		</div>
		 	</div>
		 </div>
</body>
</html>		 

  
    
