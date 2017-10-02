<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Misc Program - Fee Summary</span></div>
		<div class="working_area_spacer">
			
			<div>
			<form action="<c:url value='/miscfee/report/miscactivityfeesummary' />" method="get">
				<table width="700px">
						<tr>
							<td class="bold">Academic Year : </td>
							<td> 
									<select id="academicYearId" name="ayId" style="width:180px;height: 22px" onchange="getMiscActivities()">
										<option value=""></option>
										<c:forEach var="academicYear" items="${academicYears}">
											<option <c:if test="${selectedAy==academicYear.id}"> selected="selected" </c:if> value="${academicYear.id}">${academicYear.name}</option>
										</c:forEach>
									</select> 
							</td>
							<td class="bold">Misc Program : </td>
							<td> 
									<select id="miscActivityId" name="miscActivityId" style="width:180px;height: 22px">
										<option value=""></option>
										<c:forEach var="miscActivity" items="${miscActivities}">
											<option <c:if test="${selectedMiscActivity==miscActivity.id}"> selected="selected" </c:if>  value="${miscActivity.id}"> ${miscActivity.name}</option>
										</c:forEach>
										
									</select> 
							</td>
							<td><input type="submit" class="button" value="Search"></td>
						</tr>
				</table>
				</form>			
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(feeSummaryReport)!=0 }">
			         		Displaying 1-${fn:length(feeSummaryReport)} of ${fn:length(feeSummaryReport)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="misc_fee_summary_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Class</th>
				            <th class="grid_item">Year</th>
				            <th class="grid_item">Total student</th>
				            <th class="grid_item">Projected Fee</th>
				            <th class="grid_item">Paid Fee</th>
				            <th class="grid_item">Remaining Fee</th>
			          </tr>
		          </thead>
		          <tbody>
		           <c:forEach var="feeSummary" items="${feeSummaryReport}">
			          	<tr  class="grid_main_row">
				           	<td class="grid_item">
				        		
				        		<a href='<c:url value="//miscfee/report/studlist?academicYearId=${selectedAy}&courseYearId=${feeSummary.courseYear.id}&miscActivityCourseYearId=${feeSummary.miscActivityCourseYearId}" />'>
					        		${feeSummary.course.displayName}
				        		</a>
				            </td>
				            <td class="grid_item">
				        		${feeSummary.courseYear.name}
				            </td>
				            <td class="grid_item">
				            	<c:set var="studentCount" value="${studentCount+feeSummary.confirmAdmissions}"></c:set>
				        		${feeSummary.confirmAdmissions}
				            </td>
				            <td class="grid_item">
				        		<c:set var="projectedFeeSum" value="${projectedFeeSum+(feeSummary.confirmAdmissions*miscActivity.fee)}"></c:set>
				        		${feeSummary.confirmAdmissions*miscActivity.fee}
				            </td>
				            <td class="grid_item">
				        		<c:set var="depositedSum" value="${depositedSum+feeSummary.depositedFee}"></c:set>
				        		${feeSummary.depositedFee}
				            </td>
				             <td class="grid_item">
				             	<c:set var="remainingSum" value="${remainingSum+(feeSummary.confirmAdmissions*miscActivity.fee)-feeSummary.depositedFee}"></c:set>
				             	${(feeSummary.confirmAdmissions*miscActivity.fee)-feeSummary.depositedFee}
				            </td>
		           		</tr>
		           		</c:forEach>
		           	<tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${studentCount}</td>
			            <td class="grid_item sortable">${projectedFeeSum}</td>
			            <td class="grid_item sortable">${depositedSum}</td>
			            <td class="grid_item sortable">${remainingSum}</td>
		          </tr>
		          </tfoot>
		          </tbody>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(courseYearFeeSummaries)!=0 }">
			         		Displaying 1-${fn:length(courseYearFeeSummaries)} of ${fn:length(courseYearFeeSummaries)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>					
			
		
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>

<script type="text/javascript">

var misc_activity_url='<c:url value="/miscfee/report" />';

$(function() {
	bindReportWithDataTable();
});


function bindReportWithDataTable(){
	$('#misc_fee_summary_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	    } );
	
}

</script>
        	 
       