<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Due Fee </span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<form action="<c:url value='/fee/due/summary' />" method="get">
					<table width="600px">
						<tr>
							<td class="bold">Academic Year: </td>
							<td> 
								<select name="academicYearId">
										<c:forEach var="academicYear" items="${academicYears}">
											<option value="${academicYear.id}" <c:if test="${academicYearId==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
										</c:forEach>
									</select> 
							
							 </td>
							<td class="bold">Due Date: </td>
							<td> <input id="dueDate" name="DueDateStr" type="text" readonly="readonly" value="${dueDate}" > </td>
							<td> <input type="submit" class="button" value="Search"> </td>
							<td> <%-- <a href="javascript:void(0)" onclick="exportToExcel()" >Export to Excel</a> --%></td>
						</tr>	
					</table>
				</form>
				<form id="dueFeeForm" action="<c:url value='/fee/due/summary/export' />" method="POST">
					<input id="hiddenDueDate" name="DueDateStr" type="hidden"  />
					<input id="hiddenAYId" name="academicYearId" type="hidden" value="${academicYearId}" />
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
    		
          	 <table id="due-fee-summary-table" class="grid grid_color_theme_border">
		          <thead>
		          		
		          		<tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Class</th>
				            <th class="grid_item">Year</th>
				            <th class="grid_item">Projected</th>
				            <th class="grid_item">Deposited</th>
				            
				            <th class="grid_item">Due</th>
				            <th class="grid_item">Advance Deposited</th>
				            <th class="grid_item">Late Fees Deposited </th>
				            <th class="grid_item">T Deposited </th>
				            
		         		 </tr>
		          </thead>
		          <tbody>
		          
		           <c:forEach var="courseYearFeeSummary" items="${courseYearFeeSummaries}" varStatus="rowCounter">
				           <tr class="grid_main_row">
				           	<td class="grid_item">
				        		${courseYearFeeSummary.course.displayName}
				            </td>
				           <td class="grid_item">
				        		${courseYearFeeSummary.courseYear.name}
				            </td>
				           
				            <td class="grid_item">
				        		<c:set var="projectedFeeSum" value="${projectedFeeSum+courseYearFeeSummary.getDueProjectedFee()+courseYearFeeSummary.getBusFee()}"></c:set>
				        		${courseYearFeeSummary.getDueProjectedFee()+courseYearFeeSummary.getBusFee()}
				            </td>
				            
				           
				            <td class="grid_item">
				        		<c:set var="depositedFeeSum" value="${depositedFeeSum+courseYearFeeSummary.depositedFee}"></c:set>
				        		${courseYearFeeSummary.depositedFee}
				            </td>
				            				            <td class="grid_item">
				        	<a href="<c:url value='/fee/due/students' />?academicYearId=${academicYearId}&courseYearId=${courseYearFeeSummary.courseYear.id}&feeDueDateStr=${dueDate}&includeNonFeeCustomize=${courseYearFeeSummary.nonCustomizeStudentAvailable}">
				        		${courseYearFeeSummary.getDueProjectedFee()+courseYearFeeSummary.getBusFee()-courseYearFeeSummary.depositedFee}
				        	</a>
				            </td>
				           
				            <td class="grid_item">
				            	<c:set var="advanceDepositedFeeSum" value="${advanceDepositedFeeSum+courseYearFeeSummary.advanceDeposited}"></c:set>
				            	${courseYearFeeSummary.advanceDeposited}
				            </td>
				            <td class="grid_item">
				            	<c:set var="depositedLateFeeSum" value="${depositedLateFeeSum+courseYearFeeSummary.lateFeeDeposited}"></c:set>
				            	${courseYearFeeSummary.lateFeeDeposited}
				            </td>
				            <td class="grid_item">
				            	<c:set var="tFeeSum" value="${tFeeSum+courseYearFeeSummary.depositedFee+courseYearFeeSummary.advanceDeposited+courseYearFeeSummary.lateFeeDeposited}"></c:set>
				            	${courseYearFeeSummary.depositedFee+courseYearFeeSummary.advanceDeposited+courseYearFeeSummary.lateFeeDeposited}
				            </td>
				            
		           		</tr>
		           		</c:forEach>
		           <tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${projectedFeeSum}</td>
			            <td class="grid_item sortable">${depositedFeeSum}</td>
			            
			            <td class="grid_item sortable">${projectedFeeSum-depositedFeeSum}</td>
			            <td class="grid_item sortable">${advanceDepositedFeeSum}</td>
			            <td class="grid_item sortable">${depositedLateFeeSum}</td>
			            <td class="grid_item sortable">${tFeeSum}</td>
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
});

function exportToExcel(){
	$("#hiddenDueDate").val($("#dueDate").val());
	$("#dueFeeForm").submit();
}

$(document).ready(function() {
    $('#due-fee-summary-table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
       
    } );
} );

</script>
        	 
       