<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link rel="stylesheet" href='<c:url value="/resources/styles/manage_account.css" />?ver=0.0.0' type="text/css"></link>
		<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-1.7.1.min.js" />?ver=0.0.0'></script>
		<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-ui-1.8.18.custom.min.js" />?ver=0.0.0'></script>
		<script type="text/javascript" src='<c:url value="/resources/js/manage_accounts.js" />?ver=0.0.0'></script>
		<script type="text/javascript">
			<!--
			var ma_base_url="<c:url value='/manageaccount'/>";
			//-->
		</script>
		
	</head>
	<body>
		<div id="ac_header" class="ac_header">
			<span style="padding-left: 50px; padding-top: 15px;	color:#990000;font-size: 25px;font-style: italic;">SPITM</span>
		</div>
		<div >
			<div style="width: 500;text-align: center;"><h2>Dear '${user.firstName} ${user.lastName}'</h2></div>
			<div style="width: 500;text-align: center;">Your password has been changed successfully</div>
			
		</div>	
	</body>
</html>
        	 
       