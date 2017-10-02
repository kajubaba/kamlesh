 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
</div>
			<!-- /.container-fluid -->
			<div style="margin-top:20px;" >
		Copyright © 2016 . All rights reserved.
	</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	
	<!-- /#wrapper -->
	
	
	
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/fusion-charts/FusionCharts.js" />?ver=0.0.9.0164'></script>
	<script type="text/javascript" src='<c:url value="/sams/assets/libs/lodash-4.6.1/lodash-core.js" />?ver=0.0.9.0164'></script>
	
	<!-- jQuery -->
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/jquery-2.1.4.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/DataTables-1.10.10/js/jquery.dataTables.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/jquery-2.1.4/plugins/datepicker/jquery-ui.min.js" />?ver=2.0.3"></script>
	<!-- Bootstrap Core JavaScript -->
	
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/js/bootstrap.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/bootstrap-3.3.4/plugins/datatable/dataTables.bootstrap.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-route.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/angular-datatables-0.4.3/angular-datatables.min.js" />?ver=2.0.3"></script>
	<script src="<c:url value="/sams/assets/libs/angularjs-1.4.8/ui-bootstrap-tpls-1.3.1.min.js" />?ver=2.0.3"></script>
	
	
	
	<!-- Angular-block ui -->
	
	
	<script src="<c:url value="/sams/assets/libs/angular-block-ui/angular-block-ui.min.js" />?ver=2.0.3"></script>
		
	<script>
		var _appContextPath = "${pageContext.request.contextPath}";
		$(function() {
			
			$('#loadingDiv').hide()
			.ajaxStart(function() {
			    $(this).show();
			})
			.ajaxStop(function() {
			    $(this).hide();
			})
			
			
		});
		
	</script>
	
</body>
</html>
<div id="loadingDiv"></div>
