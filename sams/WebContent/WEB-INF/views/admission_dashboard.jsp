  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
 <div id="content_area">
  <div id="wider_working_area" class="color_theme_border">
      <div id="enquiry_dashboard_container" class="working_area_spacer">
      <span class="form_heading"> Academic Year ${activeAcademicYear.name}</span>
	    <c:choose>
		  	<c:when test="${null==activeAcademicYear}">
		  			<div style="width:100%;color: red;font-weight: bold;text-align: center;padding-top: 120px">
						Admission academic year is not configured
						<br/><br/>
						Please contact Administrator
					</div>
		  	</c:when>
		<c:otherwise>
		<input type="hidden" id="activeAcademicYearId" value="${activeAcademicYear.id}"/>
			<div style="float: right;">
	  			<security:authorize access="hasRole('ROLE_RENEW_ADMISSION')">
	  				<a href="<c:url value='/admission/promote/view' />">Promote Students</a>&nbsp;&nbsp;
	  			</security:authorize>	
	  			<%-- 
	  			<a href="<c:url value='/admission/newold' />">Old Admission</a>&nbsp;&nbsp;
	  			--%>
	  			<a href="<c:url value='/admission/new' />">New Admission</a>
	  			<security:authorize access="hasRole('ROLE_RENEW_ADMISSION')">
	  				&nbsp;&nbsp;<a href="<c:url value='/admission/renewadmission/list/def' />">Renew Admissions</a>
	  			</security:authorize>
	  			&nbsp;&nbsp; <a href='<c:url value="/admission/list/${activeAcademicYear.id}" />'> All Admissions (${allAdmn }) </a>
	  			&nbsp;&nbsp; <a href='<c:url value="/admission/report/busstopwise?academicYear=${activeAcademicYear.id}" />'> Bus Stop wise Admissions </a>
	  		</div>
	  		<br/>
	  		<br/>
	  		<div class="form_header_top form_header_top_bg">
	  		 <span class="form_heading">Class Wise (<span id="admnCount"><a href='<c:url value="/admission/list/${activeAcademicYear.id}?statusId=5" />'>${classWiseAdmn}</a></span>)</span>
	  		 <span>
	  		 	<select id="studStatus" onchange="updateClassWiseChart(${activeAcademicYear.id})" style="height: 15px;">
	  		 		<option value="7">Admission withdraw</option>
	  		 		<option value="1">Cancelled</option>
    				<option value="5" selected="selected">Confirmed</option>
    				<option value="3">Degree Awarded</option>
    				<option value="4" >Temporary</option>
    				<option value="2">Terminated</option>
    				<option value="6">Temporary Renewed</option>
	  		 	</select>
	  		 </span>
	  		
	  		</div>
	        
	        <div id="classwiseChartContainer" class="form_header_bottom form_header_bottom_border" style="text-align:center">
			</div>
			
			<br/>
			
			<div id="enq_type" style="float:left; width:49%">
				<div class="form_header_top form_header_top_bg"> <span class="form_heading">Academic year comparision view</span>
				
				</div>
		        <div class="form_header_bottom form_header_bottom_border" style="text-align:justify">
				
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="450" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/admission/dashboard/academicyearwise" />&chartWidth=450&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/admission/dashboard/academicyearwise" />&chartWidth=450&chartHeight=250" quality="high" width="450" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
	  			</div>
			</div>
			<div style="float:left; width:20px">&nbsp;</div>
			<div id="enq_over_time" style="float:left;width:49%">
				<div class="form_header_top form_header_top_bg"> 
					<span class="form_heading">New Admission (<span id="admnTypeCount"><a href='<c:url value="/admission/list/${activeAcademicYear.id}?admissionTypeId=1&statusId=5" />'>${newAdmn}</a></span>) </span>
					<span>
			  		 	<select id="admissionType" onchange="updateAdmissionTypeChart(${activeAcademicYear.id})">
			  		 		<option value="1" selected="selected">New</option>
			  		 		<option value="2">Regular</option>
			  		 	</select>
	  		 		</span>
				</div>
		        
		        <div id="admissionTypeChartContainer" class="form_header_bottom form_header_bottom_border" style="text-align:center">
				</div>
			</div>
			
			<p>&nbsp;</p>
		</c:otherwise>
	  	</c:choose>	
	  </div>
   </div>
   </div>
<jsp:include page="sams_footer.jsp"/>
<script type="text/javascript">
var admission_base_url="<c:url value='/admission' />";
var admission_list_base_url="<c:url value='/admission/list' />";
var admission_db_base_url="<c:url value='/admission/dashboard' />";
var classwiseChart = new FusionCharts('<c:url value="/resources/" />/charts/FCF_Column3D.swf', "classwiseChart", "1000", "300");
var admissionTypeChart = new FusionCharts('<c:url value="/resources/" />/charts/FCF_Column3D.swf', "admissionTypeChart", "500", "250");
 

$(function() {
	renderClasswiseChart(${activeAcademicYear.id});
	renderadmissionTypeChart(${activeAcademicYear.id});
});

 
 </script>  