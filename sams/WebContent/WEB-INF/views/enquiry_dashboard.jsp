  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="sams_header.jsp"/>
 <div id="content_area">
  <div id="wider_working_area" class="color_theme_border">
      <div id="enquiry_dashboard_container" class="working_area_spacer">
	  
	  <c:choose>
	  	<c:when test="${null== academicYearId}">
	  			<div style="width:100%;color: red;font-weight: bold;text-align: center;padding-top: 120px">
					Enquiry academic year is not configured
					<br/><br/>
					Please contact Administrator
				</div>
	  	</c:when>
			<c:otherwise>
						  		<div>
	  			<span class="form_heading"> Academic Year ${currentAcademicYear.name}</span>
	  			&nbsp;&nbsp;&nbsp;
	  				<select id="ayId">
	                  	<option></option>
	                  	<c:forEach var="academicYear" items="${academicYears}">
	                  		<option value="${academicYear.id}">${academicYear.name}</option>
	                   	</c:forEach>
	                  </select>
	             &nbsp;&nbsp;&nbsp;     
	  			<a href="javascript:void(0)" onclick="switchAcademicYear()">Switch</a>
	  			<a href='<c:url value="/enquiry/list/all?academicYearId=${academicYearId}" />' style="float: right;"> All Enquiries </a>
	  		</div>
	  		<br/>
	  		<br/>
	  		<div class="form_header_top form_header_top_bg"> <span class="form_heading">City Wise (${totalEnq})</span></div>
	        
	        <div class="form_header_bottom form_header_bottom_border" style="text-align:center">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="1000" height="300" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/citywise?academicYearId=${academicYearId}" />&chartWidth=1000&chartHeight=300" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/citywise?academicYearId=${academicYearId}" />&chartWidth=1000&chartHeight=300" quality="high" width="1000" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      				</object>
			</div>
			
			<br/>
			
			<div id="enq_type" style="float:left; width:49%">
				<div class="form_header_top form_header_top_bg"> <span class="form_heading">Enquiry Status (${totalEnq})</span>
			
				</div>
		        <div class="form_header_bottom form_header_bottom_border" style="text-align:justify">
				


	      			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="450" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/statuswise?academicYearId=${academicYearId}" />&chartWidth=450&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/statuswise?academicYearId=${academicYearId}" />&chartWidth=450&chartHeight=250" quality="high" width="450" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
	      			
	  			</div>
			</div>
			<div style="float:left; width:20px">&nbsp;</div>
			<div id="enq_over_time" style="float:left;width:49%">
				<div class="form_header_top form_header_top_bg"> <span class="form_heading">Class Wise (${totalEnq})</span></div>
		        <div class="form_header_bottom form_header_bottom_border" style="text-align:center">
				
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="450" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/classwise?academicYearId=${academicYearId}" />&chartWidth=450&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/classwise?academicYearId=${academicYearId}" />&chartWidth=450&chartHeight=250" quality="high" width="450" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
	      		
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
	function switchAcademicYear(){
		var url='<c:url value="/enquiry/dashboard" />';
		url=url+"?academicYearId="+$("#ayId").val();
		window.location = url;
	}
</script>