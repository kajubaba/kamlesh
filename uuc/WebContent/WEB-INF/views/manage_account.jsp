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
			<span style="padding-left: 50px; padding-top: 15px;	color:#990000;font-size: 25px;font-style: italic;">Manage Account</span>
		</div>
		<div >
			<div style="width: 500;text-align: center;"><h1>Having trouble signing in?</h1></div>
			<div id="errorMsg" class="errormsg" style="width: 500;text-align: center;display: none;"></div>
			<form id="troubleForm" action="<c:url value='/manageaccount/change'/>" method="post">
				<table width="500" align="center" cellspacing="10">
					<%-- 
					<tr>
						
						<td valign="top" style="width: 15px"> 
							<input type="radio" value="forgotPass" name="actionName" onchange="showHideDiv('forgotPass')"/>
						</td>
						<td> 
							I don't know my password
							<div id="forgotPass" style="display: none; margin-top: 10px">
								<div class="secondary">To reset your password, enter the username you use to sign in to system.</div>
								<div style="margin-top:8px; margin-bottom: 8px">User name:</div>
								<div><input type="text" size="30" value="" id="forgotPassUserName" name="forgotPassUserName" /></div>
							</div>
							
						</td>
					</tr>
					--%>
					<tr>
						<td valign="top">
							<input type="radio" name="actionName" value="changePass" onchange="showHideDiv('changePass')"/>
						</td>
						<td> I want to change my account password
							<div id="changePass" style="display: none;margin-top: 10px">
								<div class="secondary">To change your password, enter the username you use to sign in to system.</div>
								<div style="margin-top:8px; margin-bottom: 8px">User name:</div>
								<div><input type="text" size="30" value="" id="changePassUserName" name="changePassUserName" /></div>
							</div>
						</td>
					</tr>
					<%-- 
					<tr>
						<td valign="top">
							 <input type="radio" name="actionName" value="changeSeqQ" onchange="showHideDiv('')"/>
						 </td>
						<td>I want to change security answers</td>
					</tr>
					--%>
					<tr>
						<td></td>
						<td><input type="button" value="Contunue" class="mabutton" onclick="continueToProcess()"/></td>
					</tr>
				</table>
		</form>
		</div>
	</body>
</html>
        	 
       