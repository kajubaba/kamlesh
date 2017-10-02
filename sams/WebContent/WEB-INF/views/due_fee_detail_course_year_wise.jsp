<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Due Students | ${courseYear.course.displayName} ${courseYear.name} Yr.</span></div>
		<div class="working_area_spacer">
			<div class="page_title_txt_color" style="margin-bottom: 5px">
				Academic Year : ${academicYear.name} <span style="float: right;"><a href="javascript:void(0)" onclick="exportToExcel()">Export To Excel</a></span>
			</div>
			<div class="page_title_txt_color">
				Due Date : ${feeDueDateStr}
			</div>
			
			<form id="dueStudentFeeForm" action="<c:url value='/fee/due/students/export' />" method="post">
				<input type="hidden" name="academicYearId" value="${academicYear.id}"/>
				<input type="hidden" name="courseYearId" value="${courseYear.id}" />
				<input type="hidden" name="feeDueDateStr" value="${feeDueDateStr}"/>
			</form>
			
			
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(studentFees)!=0 }">
			         		Displaying 1-${fn:length(studentFees)} of ${fn:length(studentFees)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="due-fee-detail-table" class="grid grid_color_theme_border">
		         <thead>
			          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">ID</th>
				            <th class="grid_item">Student</th>
				            <th class="grid_item">Contact#</th>
				            <th class="grid_item">Projected</th>
				           	<th class="grid_item">Deposited</th>
				            <th class="grid_item">Due</th>
				            <th class="grid_item">Advance Deposited</th>
				            <th class="grid_item">Late Fees Deposited</th>
				           
				            
			          </tr>
		          </thead>
		          <tbody>
		          <c:forEach var="studentFee" items="${studentFees}" varStatus="rowCounter">
			         	
			         
			         		<tr class="grid_main_row">
				           	<td class="grid_item">
				        		<a href="<c:url value='/fee/admissionrenewal/${studentFee.studentId}' />">
				        		${studentFee.studentId}
				        		</a>
				            </td>
				           
				            <td class="grid_item">
				        		${studentFee.studentFirstName} ${studentFee.studentLastName}
				            </td>
				            <td class="grid_item">
				        		${studentFee.studentContactNo1}
				            </td>
				           <td class="grid_item">
				           		<c:set var="projectedFeeSum" value="${projectedFeeSum+studentFee.adminFee+studentFee.customizedFee+studentFee.busFee}"></c:set>
				        		${studentFee.adminFee+studentFee.customizedFee+studentFee.busFee}
				            </td>
				            
				            <td class="grid_item">
				            	<c:set var="paidFeeSum" value="${paidFeeSum+studentFee.paidFee}"></c:set>
				            	${studentFee.paidFee}
				            </td>
				            <td class="grid_item">
				            	${studentFee.adminFee+studentFee.customizedFee+studentFee.busFee-studentFee.paidFee}
				            </td>
				            <td class="grid_item">
				            	<c:set var="paidFeeInAdvanceSum" value="${paidFeeInAdvanceSum+studentFee.paidFeeInAdvance}"></c:set>
				            	${studentFee.paidFeeInAdvance}
				            </td>
				            <td class="grid_item">
				            	<c:set var="paidLateFeeSum" value="${paidLateFeeSum+studentFee.paidLateFee}"></c:set>
				            	${studentFee.paidLateFee}
				            </td>
				             
		           		</tr>
			         	
			         	
			         	
		           		</c:forEach>
		           <tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item">Total</td>
			            <td class="grid_item"></td>
			            <td class="grid_item"></td>
			            <td class="grid_item">${projectedFeeSum}</td>
			            <td class="grid_item">${paidFeeSum}</td>
			            <td class="grid_item">${projectedFeeSum-paidFeeSum}</td>
			            <td class="grid_item">${paidFeeInAdvanceSum}</td>
			            <td class="grid_item">${paidLateFeeSum}</td>
		          	</tr>
		          </tfoot>
		          </tbody>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(studentFees)!=0 }">
			         		Displaying 1-${fn:length(studentFees)} of ${fn:length(studentFees)}
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
function exportToExcel(){
	$("#dueStudentFeeForm").submit();
}

$(document).ready(function() {
    $('#due-fee-detail-table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false
      
       

    } );
} );

</script>
        	 
       