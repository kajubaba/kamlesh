<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html lang="en" data-ng-app="samsApp" ng-controller="mainAppCtrl as mainAppCtrl" ng-init="mainAppCtrl.init()">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>SAMS 2.0</title>
<!-- Bootstrap Core CSS -->
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/css/bootstrap.min.css" />?ver=2.0.57" rel="stylesheet">
<!-- DataTables CSS -->
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatable/dataTables.bootstrap.css" />?ver=2.0.57" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatables-responsive/css/dataTables.responsive.css" />?ver=2.0.57" rel="stylesheet">


<!-- Angular Block UI CSS -->
<link href="<c:url value="/sams/assets/libs/angular-block-ui/angular-block-ui.min.css" />?ver=2.0.57" rel="stylesheet">
<link href="<c:url value="/sams/assets/libs/angular-ui-grid/ui-grid.min.css" />?ver=2.0.57" rel="stylesheet">



<!-- Custom Fonts -->
<link href="<c:url value="/sams/assets/libs/font-awesome/css/font-awesome.min.css" />?ver=2.0.57" rel="stylesheet">

<!-- JQuery UI -->
<link href="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.min.css" />?ver=2.0.57" rel="stylesheet">
<link href="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.theme.min.css" />?ver=2.0.57" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/sams/assets/css/sams.css" />?ver=2.0.57" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via  -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top skin-blue" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header logo-skin-blue">
					<div>
					
						<c:choose>
						<c:when test="${fn:length(userSession.institutes) > 1}">
							<select id="instituteDropDown" class="navbar-brand navbar-brand-drop-down" 
							style="color: #ffffff" onchange="switchInstitute()">
						<c:forEach var="institute" items="${userSession.institutes}">
							<option value="${institute.institute.id}" <c:if test="${institute.institute.id == userSession.workingInstituteId}">selected="selected"</c:if>>${institute.institute.name}</option>
						</c:forEach>
					
					</select>
					
						
						</c:when>
						<c:otherwise>
							<div class="navbar-brand" style="color: #ffffff;min-width: 100px">
								${userSession.instituteSetting.institute.name}
							</div>
					</c:otherwise>
					</c:choose>
						
					
					</div>
			</div>
			<!-- /.navbar-header -->
			<ul class="nav navbar-top-links ">
				<security:authorize access="hasRole('ROLE_ENQUIRY_TAB')">
					<li> <a href="<c:url value="/enquiry/dashboard" />">Enquiry</a></li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_ADMISSION_READ_ONLY', 'ROLE_NEW_REGISTRATION', 'ROLE_RENEW_ADMISSION', 'ROLE_UPDATE_STUD_PERSONAL_INFO', 'ROLE_UPDATE_STUD_PIC', 'ROLE_CHANGE_STUD_CLASS', 'ROLE_CHANGE_STUD_BUS_STOP', 'ROLE_CHANGE_STUD_ADMN_SCHEME')">
					<li> <a href="<c:url value="#/admission" />">Admission</a></li>
				</security:authorize>
				<security:authorize access="hasAnyRole('ROLE_FEE_READ_ONLY', 'ROLE_ADJUST_STUD_ACADEMIC_FEE', 'ROLE_ADJUST_STUD_LATE_FEE', 'ROLE_STUD_FEE_COLLECT', 'ROLE_VIEW_ANNUAL_FEE_REPORT', 'ROLE_VIEW_DUE_FEE_REPORT', 'ROLE_PRINT_DUPLICATE_FEE_RECEIPT')">
				 	<li> <a href="<c:url value="#/fee/dashboard" />">Fee</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_MANAGE_ACADEMICS')">
					<li> <a href="<c:url value="#/academics" />">Academics</a></li> 
				</security:authorize>
				<security:authorize access="hasRole('ROLE_MANAGE_TRANSPORTATION')">
					<li> <a href="<c:url value="#/transportation/dashbord" />">Transportation</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
				 	<li> <a href="<c:url value="/admin/settings" />">Settings</a></li>
				 </security:authorize>
	
				<li class="dropdown navbar-right"><a class="dropdown-toggle"
					data-toggle="dropdown" href="javascript:void(0)"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="<c:url value="/static/j_spring_security_logout" />" ng-click="mainAppCtrl.logout()"><i class="fa fa-sign-out fa-fw"></i>
								Logout</a></li>
								
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->
			<!-- /.navbar-static-side -->
		</nav>
		
		