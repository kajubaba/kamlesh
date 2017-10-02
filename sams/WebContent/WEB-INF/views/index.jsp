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
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/css/bootstrap.min.css" />?ver=0.0.0.15" rel="stylesheet">
<!-- DataTables CSS -->
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatable/dataTables.bootstrap.css" />?ver=0.0.0.15" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatables-responsive/css/dataTables.responsive.css" />?ver=0.0.0.15" rel="stylesheet">


<!-- Angular Block UI CSS -->
<link href="<c:url value="/sams/assets/libs/angular-block-ui/angular-block-ui.min.css" />?ver=0.0.0.15" rel="stylesheet">
<link href="<c:url value="/sams/assets/libs/angular-ui-grid/ui-grid.min.css" />?ver=0.0.0.15" rel="stylesheet">



<!-- Custom Fonts -->
<link href="<c:url value="/sams/assets/libs/font-awesome-4.7.0/css/font-awesome.min.css" />?ver=0.0.0.15" rel="stylesheet">

<!-- JQuery UI -->
<link href="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.min.css" />?ver=0.0.0.15" rel="stylesheet">
<link href="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.theme.min.css" />?ver=0.0.0.15" rel="stylesheet">
<link href="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/metismenu/metisMenu.min.css" />?ver=0.0.0.15" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/sams/assets/css/sams.css" />?ver=0.0.0.15" rel="stylesheet">
<link href="<c:url value="/sams/assets/css/timeline.css" />?ver=2.0.3" rel="stylesheet">
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
		<div>
		<nav class="navbar navbar-default navbar-fixed-top skin-blue" role="navigation" style="margin-bottom: 0">
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
			<ul id="top-nav" class="nav navbar-top-links ">
				
				
				 
				<security:authorize access="hasRole('ROLE_ENQUIRY_TAB')">
					<li> <a href="<c:url value="#/enquiry/dashboard" />">Enquiry</a></li>
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
				<security:authorize access="hasRole('ROLE_REPORT_TAB')">
					<li> <a href="<c:url value='#/reports' />">Reports</a></li>
				</security:authorize>
				<security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
				 	<li> <a href="<c:url value="#/admin/settings" />">Settings </a></li>
				 </security:authorize>
				
				 
				<li ng-controller="student.quick.search.controller as searchCtrl" 
					ng-init="searchCtrl.initAutoComplete()"
					style="padding-left: 50px"> 
						<input type="text" id="autoCompleteTxtBox" class="form-control" placeholder="Search student by ID or Name" style="width:200%">
						 
				</li>
				<li class="dropdown navbar-right">
					<a class="dropdown-toggle"
					   data-toggle="dropdown" 
					   href="javascript:void(0)"> 
					   <i class="fa fa-user fa-fw"></i>
					   <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="javascript:void(0)"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="javascript:void(0)"><i class="fa fa-gear fa-fw"></i> Settings</a>
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
		</div>
		<!-- Page Content -->
		<div id="page-wrapper-full-screen">
			
			
			
			<div class="container-fluid">
				<c:if test="${not empty warningMessage}">
					<div class="alert alert-danger" style="margin-top: 10px">
						 <span class="glyphicon glyphicon-warning-sign"></span>
						 <strong>${warningMessage}</strong>
					</div>
				</c:if>
					<div data-ng-view></div>
				
			</div>
			<!-- /.container-fluid -->
	
		</div>
		<!-- /#page-wrapper -->
	</div>
	
	<!-- /#wrapper -->
	
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/fusion-charts/FusionCharts.js" />?ver=0.0.9.0164'></script>
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/lodash-4.6.1/lodash-core.js" />?ver=0.0.9.0164'></script>
	
	<!-- jQuery -->
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/jquery-2.1.4.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/DataTables-1.10.10/js/jquery.dataTables.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/metismenu/metisMenu.min.js" />?ver=0.0.0.15"></script>
	<!-- Bootstrap Core JavaScript -->
	
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/js/bootstrap.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatable/dataTables.bootstrap.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-route.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-datatables-0.4.3/angular-datatables.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/ui-bootstrap-tpls-1.3.1.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-drag-and-drop-lists-1.4.0.min.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-local-storage.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/ui-grid.min.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/csv.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/pdfmake.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/vfs_fonts.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/assets/libs/amcharts/amcharts.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/amcharts/serial.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/assets/libs/amcharts/plugins/export/export.min.js" />?ver=0.0.0.15"></script>
	<link rel="stylesheet" href="<c:url value="/sams/assets/libs/amcharts/plugins/export/export.css" />?ver=0.0.0.15" type="text/css" media="all" />
	<script src="<c:url value="/sams/assets/libs/amcharts/themes/light.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/assets/libs/amChartsDirective.js" />?ver=0.0.0.15"></script>
	
	
	
	<!-- Angular-block ui -->
	
	
	<script src="<c:url value="/sams/assets/libs/angular-block-ui/angular-block-ui.min.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/app.module.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/permissions.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/app.routes.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/has.permissions.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/core/installment.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/core/institute.setting.service.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/admission/admission.dashboard.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.dashboard.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.studentlist.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.studentlist.service.js" />?ver=0.0.0.15"></script>
	
	
	<!-- JS to manage admission who adopted bus facility -->
	
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.student.view.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.class.view.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.busstop.view.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/bus.notadopted.student.view.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/bus.notadopted.student.service.js" />?ver=0.0.0.15"></script>
	
	<!-- JS to manage new admission -->
	
	<script src="<c:url value="/sams/components/admission/admission.newadmission.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/admission.newadmission.service.js" />?ver=0.0.0.15"></script>
	
	<!-- JS for student details -->
	
	<script src="<c:url value="/sams/components/student/student.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/student/student.service.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/shared/student.quick.search.controller.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/shared/server.error.handler.service.js" />?ver=0.0.0.15"></script>
	
	
	<!-- JS for admission renewal -->
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.manage.renewal.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.manage.renewal.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.renew.controller.js" />?ver=0.0.0.15"></script>
	
	
	<script src="<c:url value="/sams/components/admission/admission.editadmission.controller.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/student/student.activity.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/student/student.activity.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/student/student.conversation.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/student/student.conversation.service.js" />?ver=0.0.0.15"></script>
	
	
	<!-- JS for fee module -->
	
	<!-- JS for dashboard -->
	
	<script src="<c:url value="/sams/components/fee/fee.dashboard.controller.js" />?ver=0.0.0.15"></script>
	
	
	
	<!-- student fee -->
	<script src="<c:url value="/sams/components/fee/student/student.fee.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.customize.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/fee.customize.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/student.feeview.information.controller.js" />?ver=0.0.0.15"></script>
	
	<!-- //student fee -->

	<!-- Paid Fee -->
	<script src="<c:url value="/sams/components/fee/paidfee/paid.fee.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/paidfee/paid.fee.headwise.controller.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/fee/paidfee/paid.fee.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/paidfee/transaction.detail.popup.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.service.js" />?ver=0.0.0.15"></script>
	<!-- // Paid Fee -->
	
	<!-- adjusted fee -->
	<script src="<c:url value="/sams/components/fee/adjustedstudent/fee.adjusted.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/adjustedstudent/fee.adjusted.service.js" />?ver=0.0.0.15"></script>
	
	<!-- // adjusted fee -->

	
	
	
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.annualfee.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.annualfee.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.duefee.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.duefee.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/reports/duefee.notice.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/reports/duefee.notice.service.js" />?ver=0.0.0.15"></script>
	
	
	
	
	<!-- //JS for fee module -->
	
	<script src="<c:url value="/sams/components/shared/sams.ajax.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/student/student.brief.information.controller.js" />?ver=0.0.0.15"></script>
	
	<!-- JS for transaction module -->
	
	
	<script src="<c:url value="/sams/components/transportation/settings/translations/trans.student.translations.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/settings/translations/trans.student.translations.service.js" />?ver=0.0.0.15"></script>
	
	
	<script src="<c:url value="/sams/components/transportation/transportation.dashboard.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/vehicle/transportation.vehicle.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/vehicle/transportation.vehicle.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.academicyearvehicle.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.academicyearvehicle.service.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.bus.student.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.bus.student.service.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.plan.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.plan.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/busstop/transportation.busstop.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/busstop/transportation.busstop.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.pickupdroppoint.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.pickupdroppoint.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/student/transportation.student.detail.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/student/transportation.student.detail.service.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/transportation/settings/driver/trans.driver.list.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/settings/driver/trans.manage.driver.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/transportation/settings/driver/trans.driver.service.js" />?ver=0.0.0.15"></script>
	
	
	<!-- //JS for transaction module -->
	
	<script src="<c:url value="/sams/components/shared/academicsession.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/shared/fee.academicsession.controller.js" />?ver=0.0.0.15"></script>
	
	<script src="<c:url value="/sams/components/shared/academicsession.service.js" />?ver=0.0.0.15"></script>
	
	<!-- //JS for common module -->
	<script src="<c:url value="/sams/components/shared/common.service.js" />?ver=0.0.0.15"></script>
	
	<!-- JS for student late fee adjustments -->
	
	<script src="<c:url value="/sams/components/fee/student/student.fee.adjust.latefee.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.adjust.latefee.service.js" />?ver=0.0.0.15"></script>
	
	<!-- // JS for student late fee adjustments -->
	
	<!-- JS for Institute -->
	
	<script src="<c:url value="/sams/components/shared/institute.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/shared/institute.service.js" />?ver=0.0.0.15"></script>
	<script type="text/javascript" src='<c:url value="/resources/js/common/common.js" />?ver=0.0.0.15'></script>
	<!-- // JS for Institute -->
	
	
	<!-- JS for Academics -->
	
	
	
	<script src="<c:url value="/sams/components/academics/settings/subject/academics.manage.class.subject.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/settings/subject/academics.manage.class.subject.service.js" />?ver=0.0.0.15"></script>
	
	
	
	<script src="<c:url value="/sams/components/academics/settings/section/academics.manage.class.section.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/settings/section/academics.manage.class.section.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/student-list/academics.studentlist.controller.js" />?ver=0.0.0.15"></script>
	
	<!-- Assessment Setting-->
	
		<script src="<c:url value="/sams/components/academics/settings/academics.settings.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.pattern.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.pattern.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.pattern.class.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.pattern.class.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.setup.scholastic.assessment.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.setup.coscholastic.assessment.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.manage.csa.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.manage.csa.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.manage.exam.controller.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.grade.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.review.scoreacard.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/settings/exam/academics.exam.review.scorecard.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.attendance.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.attendance.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.print.marksheet.controller.js" />?ver=0.0.0.15"></script>
		
		
		
	
	<!-- // Assessment Setting-->
	
	
	
	<script src="<c:url value="/sams/components/academics/birthday/academics.student.birthday.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/birthday/academics.student.birthday.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/managesection/academics.manage.student.section.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/managesection/academics.manage.student.section.service.js" />?ver=0.0.0.15"></script>
	
	
	<!-- Assessment -->
		<script src="<c:url value="/sams/components/academics/exam/academics.exam.scholastic.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.exam.scholastic.score.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.exam.coscholastic.stud.search.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.exam.co-scholastic.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/academics/exam/academics.exam.co-scholastic.service.js" />?ver=0.0.0.15"></script>
	<!-- //Assessment -->
	
	
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.evaluationtype.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.evaluationterm.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.termassessment.service.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.rollno.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.rollno.service.js" />?ver=0.0.0.15"></script>
	
	
	<script src="<c:url value="/sams/components/academics/student/academics.student.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/academics/student/academics.student.service.js" />?ver=0.0.0.15"></script>
	
	
	
	<!-- // JS for Academics -->
	
	<!--  JS for Reports -->
	<script src="<c:url value="/sams/components/reports/communication/reports.conversation.controller.js" />?ver=0.0.0.15"></script>
	<script src="<c:url value="/sams/components/reports/communication/conversation.service.js" />?ver=0.0.0.15"></script>	
	<!-- // JS for Reports -->
	
	<!--  JS for Settings -->
	
		<script src="<c:url value="/sams/components/admin/communication/sms.settings.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/communication/sms.settings.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/bus-stop/busstop.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/bus-stop/busstop.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/bus-stop/busstop.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/fee-head/feehead.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/fee-head/feehead.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/fee-head/feehead.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/affiliation-authority/affiliationauthority.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/affiliation-authority/affiliationauthority.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/affiliation-authority/affiliationauthority.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/course/course.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/course/course.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/course/course.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/latefee-rule/latefeerule.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/latefee-rule/latefeerule.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/latefee-rule/latefeerule.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/admission-scheme/admission.scheme.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/admission-scheme/admission.scheme.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/admission-scheme/admission.scheme.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/student-category/student.category.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/student-category/student.category.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/student-category/student.category.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/student-status/student.status.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/student-status/student.status.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/student-status/student.status.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/enquiry/enquiry.status.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/enquiry/enquiry.status.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/enquiry/enquiry.status.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.select.wizard.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.manage.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.busstop.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.assign.busstop.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.busstop.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.busfee.installment.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.busfee.installment.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/settings/institute.settings.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.class.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.class.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.classdetail.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.class.installment.controller.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.admissionscheme.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.admissionscheme.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.assign.admissionscheme.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.document.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/admin/academic-session/academicsession.document.controller.js" />?ver=0.0.0.15"></script>
		
		
		<script src="<c:url value="/sams/components/enquiry/enquiry.list.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.dashboard.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.list.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.classwise.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.classwise.student.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.statuswise.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.statuswise.student.controller.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/enquiry/enquiry.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.detail.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.new.follow.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.followup.service.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.follow.list.controller.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/enquiry/enquiry.upcoming.follow.controller.js" />?ver=0.0.0.15"></script>
		<script src="<c:url value="/sams/components/enquiry/enquiry.upcoming.followup.service.js" />?ver=0.0.0.15"></script>
		
		<script src="<c:url value="/sams/components/enquiry/enquiry.activity.service.js" />?ver=0.0.0.15"></script>
		
		
		
		
		
	<!--  // JS for Settings -->
			
	
	<script>
		var _appContextPath = "${pageContext.request.contextPath}";
		
		/*  $(".nav li").on("click", function(){
				 $(".nav a").css({color:"#ffffff"})
				 $(".nav li").removeClass("nav-active");
				 $(this).addClass("nav-active");
				 $(this).children("a").css({color:"#367fa9"});
		 });
 */		 
		 
	</script>
	
</body>
</html>

