<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
	
	</div>
	
	<!-- /#wrapper -->
	
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/fusion-charts/FusionCharts.js" />?ver=0.0.9.0164'></script>
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/lodash-4.6.1/lodash-core.js" />?ver=0.0.9.0164'></script>
	
	<!-- jQuery -->
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/jquery-2.1.4.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/DataTables-1.10.10/js/jquery.dataTables.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.min.js" />?ver=2.0.57"></script>
	<!-- Bootstrap Core JavaScript -->
	
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/js/bootstrap.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatable/dataTables.bootstrap.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-route.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-datatables-0.4.3/angular-datatables.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/ui-bootstrap-tpls-1.3.1.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-local-storage.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/ui-grid.min.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/csv.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/pdfmake.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/assets/libs/angular-ui-grid/grunt-scripts/vfs_fonts.js" />?ver=2.0.57"></script>
	
	
	
	<!-- Angular-block ui -->
	
	
	<script src="<c:url value="/sams/assets/libs/angular-block-ui/angular-block-ui.min.js" />?ver=2.0.57"></script>
	
	<script src="<c:url value="/sams/app.module.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/permissions.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/app.routes.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/has.permissions.js" />?ver=2.0.57"></script>
	
	<script src="<c:url value="/sams/components/admission/admission.dashboard.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.dashboard.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.studentlist.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.studentlist.service.js" />?ver=2.0.57"></script>
	
	
	<!-- JS to manage admission who adopted bus facility -->
	
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.student.view.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.class.view.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.busstop.view.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.bus.facility.service.js" />?ver=2.0.57"></script>
	
	
	<!-- JS to manage new admission -->
	
	<script src="<c:url value="/sams/components/admission/admission.newadmission.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/admission.newadmission.service.js" />?ver=2.0.57"></script>
	
	<!-- JS for student details -->
	
	<script src="<c:url value="/sams/components/student/student.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/student/student.service.js" />?ver=2.0.57"></script>
	
	<script src="<c:url value="/sams/components/shared/student.quick.search.controller.js" />?ver=2.0.57"></script>
	
	<!-- JS for admission renewal -->
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.manage.renewal.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.manage.renewal.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/admission/renewalpending/admission.renew.controller.js" />?ver=2.0.57"></script>
	
	
	<script src="<c:url value="/sams/components/admission/admission.editadmission.controller.js" />?ver=2.0.57"></script>
	
	<script src="<c:url value="/sams/components/student/student.activity.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/student/student.activity.service.js" />?ver=2.0.57"></script>
	
	<!-- JS for fee module -->
	
	<!-- JS for dashboard -->
	
	<script src="<c:url value="/sams/components/fee/fee.dashboard.controller.js" />?ver=2.0.57"></script>
	
	
	
	<!-- student fee -->
	<script src="<c:url value="/sams/components/fee/student/student.fee.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.customize.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/fee.customize.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/student.feeview.information.controller.js" />?ver=2.0.57"></script>
	
	<!-- //student fee -->

	<!-- Paid Fee -->
	<script src="<c:url value="/sams/components/fee/paidfee/paid.fee.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/paidfee/paid.fee.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/paidfee/transaction.detail.popup.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.service.js" />?ver=2.0.57"></script>
	<!-- // Paid Fee -->
	
	<!-- adjusted fee -->
	<script src="<c:url value="/sams/components/fee/adjustedstudent/fee.adjusted.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/adjustedstudent/fee.adjusted.service.js" />?ver=2.0.57"></script>
	
	<!-- // adjusted fee -->

	
	
	
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.annualfee.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.annualfee.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.duefee.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/reports/fee.reports.duefee.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/reports/duefee.notice.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/reports/duefee.notice.service.js" />?ver=2.0.57"></script>
	
	
	
	
	<!-- //JS for fee module -->
	
	<script src="<c:url value="/sams/components/shared/sams.ajax.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/student/student.brief.information.controller.js" />?ver=2.0.57"></script>
	
	<!-- JS for transaction module -->
	
	<script src="<c:url value="/sams/components/transportation/transportation.dashboard.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/vehicle/transportation.vehicle.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/vehicle/transportation.vehicle.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.academicyearvehicle.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/academicyearvehicle/transportation.academicyearvehicle.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.plan.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/route/transportation.route.plan.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/busstop/transportation.busstop.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/busstop/transportation.busstop.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.service.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.pickupdroppoint.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/transportation/students/transportation.student.pickupdroppoint.service.js" />?ver=2.0.57"></script>
	
	<!-- //JS for transaction module -->
	
	<script src="<c:url value="/sams/components/shared/academicsession.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/shared/fee.academicsession.controller.js" />?ver=2.0.57"></script>
	
	<script src="<c:url value="/sams/components/shared/academicsession.service.js" />?ver=2.0.57"></script>
	
	<!-- //JS for common module -->
	<script src="<c:url value="/sams/components/shared/common.service.js" />?ver=2.0.57"></script>
	
	<!-- JS for student late fee adjustments -->
	
	<script src="<c:url value="/sams/components/fee/student/student.fee.adjust.latefee.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/fee/student/student.fee.adjust.latefee.service.js" />?ver=2.0.57"></script>
	
	<!-- // JS for student late fee adjustments -->
	
	<!-- JS for Institute -->
	
	<script src="<c:url value="/sams/components/shared/institute.controller.js" />?ver=2.0.57"></script>
	<script src="<c:url value="/sams/components/shared/institute.service.js" />?ver=2.0.57"></script>
	<script type="text/javascript" src='<c:url value="/resources/js/common/common.js" />?ver=2.0.57'></script>
	<!-- // JS for Institute -->
	
	
	<!-- JS for Academics -->
	
	<script src="<c:url value="/sams/components/academics/subject/academics.manage.class.subject.controller.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/subject/academics.manage.class.subject.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/birthday/academics.student.birthday.controller.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/birthday/academics.student.birthday.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/managesection/academics.manage.student.section.controller.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/managesection/academics.manage.student.section.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.scholastic.controller.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.scholastic.score.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.evaluationtype.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.evaluationterm.service.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/academics/exam/academics.exam.termassessment.service.js" />?ver=2.0.5"></script>
	
	
	
	<!-- // JS for Academics -->
	
		<!-- JS for Acad -->
	<script src="<c:url value="/sams/components/acad/classSection/acad.manage.class.section.controller.js" />?ver=2.0.5"></script>
	<script src="<c:url value="/sams/components/acad/classSection/acad.manage.class.section.service.js" />?ver=2.0.5"></script>
	<!-- // JS for Academics -->
	<script>
		var _appContextPath = "${pageContext.request.contextPath}";
		
	</script>
</body>
</html>
