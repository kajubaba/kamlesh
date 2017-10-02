<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href='<c:url value="/resources/styles/main.css" />?ver=0.0.9.0164' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/default_theme.css" />?ver=0.0.9.0164' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/datatable_default.css" />?ver=0.0.9.0164' type="text/css"></link>
<link rel="stylesheet" href='<c:url value="/resources/styles/jquery-ui-1.8.18.custom.css" />?ver=0.0.9.0164' type="text/css"></link>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-1.7.1.min.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery_numeric.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery-ui-1.8.18.custom.min.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery.dataTables.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery.jqprint-0.3.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/enquiry/enquiry.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/enquiry/enquiry_listing.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/enquiry/enquiry_activity.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_course.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/academic_year_settings.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_bus_stop.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_bus_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_miscactivities.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_fee_installments.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/common/common.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/academic_year_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/student_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/student_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/customize_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/admission.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/admission_listing.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/admission_dashboard.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_hostel.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_hostel_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/admission_activity.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/customize_late_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/manage_late_fee_rules.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admin/leave_plan.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/admission/student_misc_fee.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/charts/FusionCharts.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/charts/FusionCharts.js" />?ver=0.0.9.0164'></script>
 

<script type="text/javascript" src='<c:url value="/resources/js/jquery/vendor/jquery.ui.widget.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery.iframe-transport.js" />?ver=0.0.9.0164'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery/jquery.fileupload.js" />?ver=0.0.9.0164'></script>


 <style>
.ui-autocomplete {
max-height: 350px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
* html .ui-autocomplete {
height: 100px;
}
</style>


<title>SAMS ${tabName}
</title>
</head>

<body style="font-size:11px">
<div id="main_container">
  <div id="header" class="header_border">
    <div class="header_left">
 
      <div class="customer_name">${userSession.workingInstitute.name}</div>
      <div style="font-size: 9px;color: #ffffff;margin-left: 15px;margin-top: 15px">
      	<fmt:formatDate type="both" timeStyle="short" value="${serverTime}" />
      	</div>
    </div>
    
    <div class="header_right">
      <div id="navigaion">
      
      <security:authorize access="hasRole('ROLE_ENQUIRY_TAB')">
      		<a href="<c:url value="/enquiry/dashboard" />">
      		<c:choose>
	       		<c:when test="${tabName=='enquiry'}"><div id="tab1" class="enqSelected">&nbsp;</div></c:when>
	       		<c:otherwise><div id="tab1" class="enqPlain">&nbsp;</div></c:otherwise>
       		</c:choose>
       		</a>
      </security:authorize>
        <security:authorize access="hasAnyRole('ROLE_ADMISSION_READ_ONLY', 'ROLE_NEW_REGISTRATION', 'ROLE_RENEW_ADMISSION', 'ROLE_UPDATE_STUD_PERSONAL_INFO', 'ROLE_UPDATE_STUD_PIC', 'ROLE_CHANGE_STUD_CLASS', 'ROLE_CHANGE_STUD_BUS_STOP', 'ROLE_CHANGE_STUD_ADMN_SCHEME')">
         	 <a href="<c:url value="/home#/admission" />">
         	 	<c:choose>
		       		<c:when test="${tabName=='admission'}"><div id="tab2" class="admSelected">&nbsp;</div></c:when>
		       		<c:otherwise><div id="tab2" class="admPlain">&nbsp;</div></c:otherwise>
       			</c:choose>
         	 </a>	
         </security:authorize>
        <%-- 
         <c:set var="showFeeDashboard" value="false" />
         --%>
          <security:authorize access="hasAnyRole('ROLE_FEE_READ_ONLY', 'ROLE_ADJUST_STUD_ACADEMIC_FEE', 'ROLE_ADJUST_STUD_LATE_FEE', 'ROLE_STUD_FEE_COLLECT', 'ROLE_VIEW_ANNUAL_FEE_REPORT', 'ROLE_VIEW_DUE_FEE_REPORT', 'ROLE_PRINT_DUPLICATE_FEE_RECEIPT')">
         	 
         	
         	<a href="<c:url value="/home#/fee/dashboard" />">
         		<c:choose>
		       		<c:when test="${tabName=='fee'}"><div id="tab3" class="feeSelected">&nbsp;</div></c:when>
		       		<c:otherwise><div id="tab3" class="feePlain">&nbsp;</div></c:otherwise>
       			</c:choose>
         	</a>
         </security:authorize>
        
        
        <%-- 
         <c:if test="${showFeeDashboard==false}">
         	<security:authorize access="hasRole('ROLE_CUST_STUD_FEE')">
         	<c:set var="showFeeDashboard" value="true" />
         	<a href="<c:url value="/fee/default" />">
         		<c:choose>
		       		<c:when test="${tabName=='fee'}"><div id="tab3" class="feeSelected">&nbsp;</div></c:when>
		       		<c:otherwise><div id="tab3" class="feePlain">&nbsp;</div></c:otherwise>
       			</c:choose>
         	</a>
         </security:authorize>
         </c:if>
         --%>
         
        <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        	<a href="<c:url value="/admin" />">
        		<c:choose>
		       		<c:when test="${tabName=='admin'}"><div id="tab4" class="adminSelected">&nbsp;</div></c:when>
		       		<c:otherwise><div id="tab4" class="adminPlain">&nbsp;</div></c:otherwise>
       			</c:choose>
        	</a>
        </security:authorize>
      </div>
      <%-- 
      <security:authorize access="hasRole('ROLE_ADMISSION')">
	      <div>
	      		<a href='<c:url value="/home#/admission" />'  style="color:#ffffff;text-decoration: none;">New Addmision*</a>
	      </div>
	  </security:authorize>
	  --%>    
     <div style="float: right;margin-right: 15px;color: #ffffff" >
     		
     		<%-- 
     		
     		<c:if test="${userSession.nonWorkingInstitute!=null}">
     			<a href="<c:url value="/institute/ci/${userSession.nonWorkingInstitute.id}" />" style="text-decoration: none;"> <span style="color: #ffffff;">Switch to ${userSession.nonWorkingInstitute.name}</span></a>
       	  	&nbsp;&nbsp;
     		</c:if>
     		--%>
     		
       	  	<security:authentication property="principal.firstName" /> <security:authentication property="principal.lastName" />
         	&nbsp;&nbsp;
         	<a href='<c:url value="/static/j_spring_security_logout" />' style="text-decoration: none;"> <span style="color: #ffffff;font-weight: bold;"> Logout</span></a>
       </div>
       
    </div>
  </div>
  
    
