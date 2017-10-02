  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <jsp:include page="sams_header.jsp"/>
 <div id="content_area">
  <div id="wider_working_area" class="color_theme_border">
      <div id="enquiry_dashboard_container" class="working_area_spacer">
	  	 <span class="form_heading"> Academic Year ${admissionAcademicYear.name}</span>
	  	 <c:choose>
		  	<c:when test="${null== admissionAcademicYear}">
		  			<div style="width:100%;color: red;font-weight: bold;text-align: center;padding-top: 120px">
						Admission academic year is not configured
						<br/><br/>
						Please contact Administrator
					</div>
		  	</c:when>
		<c:otherwise>
			<div style="float: right;">
	  			<a href="<c:url value='/fee/customizestudents' />">Customize Students</a>&nbsp;&nbsp;
	  			<a href="<c:url value='/fee/due' />">Due Fee</a>&nbsp;&nbsp;
	  			<a href="<c:url value='/fee/report/annual' />">Annual Fee Summary</a>&nbsp;&nbsp;
	  			<a href="<c:url value='/fee/transaction' />">Fee Transaction</a>&nbsp;&nbsp;
	  			<a href="<c:url value='/fee/default' />">Fee Payment</a>&nbsp;&nbsp;
	  			<a href="<c:url value='/miscfee/home' />">Misc Program</a>&nbsp;&nbsp;
	  		</div>
	  		<br/>
	  		<br/>
	  		<div class="form_header_top form_header_top_bg">
	  			 <span class="form_heading">Class Wise (<a href="<c:url value='/fee/paid/classwise' />">${classWiseSum}</a>)</span>
	  		</div>
	        <div class="form_header_bottom form_header_bottom_border" style="text-align:center">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="1000" height="300" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/fee/dashboard/classwise?academicYearId=${admissionAcademicYear.id}" />&chartWidth=1000&chartHeight=300" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/fee/dashboard/classwise?academicYearId=${admissionAcademicYear.id}" />&chartWidth=1000&chartHeight=300" quality="high" width="1000" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      				</object>
			</div>
			
			<br/>
			
			 
			
			<div id="enq_type" style="float:left; width:49%">
				<div class="form_header_top form_header_top_bg">
					 <span class="form_heading">Last one month (<a href="<c:url value='/fee/paid/lastonemonth' />">${oneMonthFeeSum}</a>)</span>
				</div>
		        <div class="form_header_bottom form_header_bottom_border" style="text-align:justify">
				
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="450" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/fee/dashboard/onemonth?academicYearId=${admissionAcademicYear.id}" />&chartWidth=450&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/fee/dashboard/onemonth?academicYearId=${admissionAcademicYear.id}" />&chartWidth=450&chartHeight=250" quality="high" width="450" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
	  			</div>
			</div>
			<div style="float:left; width:20px">&nbsp;</div>
			<div id="enq_over_time" style="float:left;width:49%">
				<div class="form_header_top form_header_top_bg">
					 <span class="form_heading">Today Fee (<a href="<c:url value='/fee/paid/today' />">${todayFeeSum}</a>)</span>
					 <span><a href="<c:url value='/fee/paid/headwise' />">Fee Head View</a> </span>
				</div>
		        <div class="form_header_bottom form_header_bottom_border" style="text-align:center">
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="450" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/fee/dashboard/today?academicYearId=${admissionAcademicYear.id}" />&chartWidth=450&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/fee/dashboard/today?academicYearId=${admissionAcademicYear.id}" />&chartWidth=450&chartHeight=250" quality="high" width="450" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
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
 var admission_base_url="<c:url value='/admission' />";
 </script>  