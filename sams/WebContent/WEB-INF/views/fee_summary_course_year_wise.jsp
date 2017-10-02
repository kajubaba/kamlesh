<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Academic Year Fee Summary</span></div>
		<div class="working_area_spacer">
			
			<div class="search_param">
			<form action="<c:url value='/fee/report/annual' />" method="get">
				<table width="300px">
						<tr>
							<td class="bold">Academic Year : </td>
							<td> 
								
									<select name="academicYearId">
										<c:forEach var="academicYear" items="${academicYears}">
											<option value="${academicYear.id}" <c:if test="${selectedAcademicYear==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
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
			         	<c:when test="${ fn:length(courseYearFeeSummaries)!=0 }">
			         		Displaying 1-${fn:length(courseYearFeeSummaries)} of ${fn:length(courseYearFeeSummaries)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="peojected_fee_summary_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Course Year</th>
				            <th class="grid_item">Total student</th>
				            <th class="grid_item">Projected Fee</th>
				            <th class="grid_item">Discount Given</th>
				            <th class="grid_item">Actual Payable</th>
				            <th class="grid_item">Deposited</th>
				            <th class="grid_item">Deposited Late Fee</th>
				            <th class="grid_item">Remaining</th>
			          </tr>
		          </thead>
		          <tbody>
		           <c:forEach var="courseYearFeeSummary" items="${courseYearFeeSummaries}">
			          	<tr  class="grid_main_row">
				           	<td class="grid_item">
				        		${courseYearFeeSummary.course.displayName}, ${courseYearFeeSummary.courseYear.name} Year
				            </td>
				            <td class="grid_item">
				            	<c:set var="studentCount" value="${studentCount+courseYearFeeSummary.studentCount()}"></c:set>
				        		${courseYearFeeSummary.studentCount()}
				            </td>
				            <td class="grid_item">
				        		<c:set var="projectedFeeSum" value="${projectedFeeSum+courseYearFeeSummary.projectedFee()+ courseYearFeeSummary.getBusFee()}"></c:set>
				        		${courseYearFeeSummary.projectedFee()+ courseYearFeeSummary.getBusFee()}
				            </td>
				            <td class="grid_item">
				        		<c:set var="discountSum" value="${discountSum+courseYearFeeSummary.discoutGiven}"></c:set>
				        		
				        		<a href="<c:url value="discountgiven?academicYearId=${selectedAcademicYear}&courseYearId=${courseYearFeeSummary.courseYear.id}" />">${courseYearFeeSummary.discoutGiven}</a>
				        		
				        		
				        		
				        		
				            </td>
				             <td class="grid_item">
				        		${courseYearFeeSummary.projectedFee()+ courseYearFeeSummary.getBusFee() - courseYearFeeSummary.discoutGiven}
				            </td>
				            <td class="grid_item">
				            	<c:set var="depositedSum" value="${depositedSum+courseYearFeeSummary.depositedFee}"></c:set>
				        		${courseYearFeeSummary.depositedFee}
				            </td>
				            <td class="grid_item">
				            	<c:set var="depositedLateFeeSum" value="${depositedLateFeeSum+courseYearFeeSummary.lateFeeDeposited}"></c:set>
				        		${courseYearFeeSummary.lateFeeDeposited}
				            </td>
				            <td class="grid_item">
				        		${courseYearFeeSummary.projectedFee()+ courseYearFeeSummary.getBusFee() - courseYearFeeSummary.discoutGiven-courseYearFeeSummary.depositedFee}
				            </td>
		           		</tr>
		           		</c:forEach>
		           	<tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable"></td>
			            
			            <td class="grid_item sortable">${studentCount}</td>
			            <td class="grid_item sortable">${projectedFeeSum}</td>
			            <td class="grid_item sortable">${discountSum}</td>
			            <td class="grid_item sortable">${projectedFeeSum-discountSum}</td>
			            <td class="grid_item sortable">${depositedSum}</td>
			            <td class="grid_item sortable">${depositedLateFeeSum}</td>
			            <td class="grid_item sortable">${projectedFeeSum-discountSum-depositedSum}</td>
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


$(function() {
	
	$( "#dueDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	bindReportWithDataTable();
	
	
});


function bindReportWithDataTable(){
	$('#peojected_fee_summary_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	    } );
	
}

</script>
        	 
       