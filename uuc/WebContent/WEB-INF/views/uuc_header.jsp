<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href='<c:url value="/resources/styles/main.css" />?ver=0.0.0' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/default_theme.css" />?ver=0.0.0' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/datatable_default.css" />?ver=0.0.0' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/jquery-ui-1.8.18.custom.css" />?ver=0.0.0' type="text/css"></link>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-1.7.1.min.js" />?ver=0.0.0'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery_numeric.js" />?ver=0.0.0'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-ui-1.8.18.custom.min.js" />?ver=0.0.0'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery.dataTables.js" />?ver=0.0.0'></script>
<script type="text/javascript" src='<c:url value="/resources/js/user.js" />?ver=0.0.0'></script>
<script type="text/javascript" src='<c:url value="/resources/js/role.js" />?ver=0.0.0'></script>

<title>UUC</title>
</head>

<body style="font-size:11px; margin: 0;padding: 0">
<div id="main_container">
  <div id="header" class="header_border">
    <div class="header_left">
      <div class="customer_name">UUC</div>
    </div>
    <div class="header_right">
      <div id="navigaion" style="margin-top: 30px;margin-left: 10px">
        	<security:authorize access="hasAnyRole('ROLE_NEW_USER','ROLE_MANAGE_USER_ROLE')">
        		<a href="<c:url value="/user/list" />" style="text-decoration: none;color: #ffffff;font-weight: bold;">Manage User</a>&nbsp;&nbsp;&nbsp;&nbsp;
        	</security:authorize>
        	<security:authorize access="hasAnyRole('ROLE_NEW_ROLE','ROLE_MANAGE_ROLE_PERMISSIONS')">
        		<a href="<c:url value="/role/list" />" style="text-decoration: none;color: #ffffff;font-weight: bold;">Manage Security</a>&nbsp;&nbsp;&nbsp;&nbsp;
        	</security:authorize>
      </div>
     <div style="float: right;margin-right: 15px;color: #ffffff" >
       	 
       	  <security:authentication property="principal.firstName" /> <security:authentication property="principal.lastName" />
         &nbsp;&nbsp;<a href='<c:url value="/static/j_spring_security_logout" />' style="text-decoration: none;"> <span style="color: #ffffff;font-weight: bold;"> Logout</span></a>
         
       </div>
    </div>
  </div>
  
    
