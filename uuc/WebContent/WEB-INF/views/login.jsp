<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>UUC | Login</title>
<link rel="stylesheet" href='<c:url value="/resources/styles/main.css" />' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/default_theme.css" />' type="text/css"></link>
</head>
<body onload='document.f.j_username.focus();' style="font-size:11px" bgcolor="#FAFAFA">
	<div id="main_container" style="width:400px;margin-top: 150px" >
	 <div class="form_header_top form_header_top_bg" style="width: 400px"> <span class="form_heading">UUC Login</span></div>
     <div class="form_header_bottom form_header_bottom_border" style="width: 400px">
     	<div class="form_container">
     		
	<form name='f' method='POST' action='<c:url value="/static/j_spring_security_check" />'>
	<c:if test="${not empty error}">
		<div>
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
		<table>
			<tr>
				<td class="label">User:</td>
				<td><input type='text' name='j_username' value='' size="32"></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><input type='password' name='j_password' size="30"/></td>
			</tr>
			<tr>
				<td colspan='2' align="center"><br/><input name="submit" type="submit" value="&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;" class="button"/></td>
			</tr>
			<tr>
				<td colspan='2' align="center">
					</br>
					<a href="${manageaccounturl}" style="font-size: 12px">Change Password</a>
				</td>
			</tr>
			
		</table>
	</form>
     		</div>
     </div>
     
	
	
	</div>
</body>
</html>