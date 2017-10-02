<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Discounts | ${courseYear.course.displayName} ${courseYear.name} Year</span></div>
		<div class="working_area_spacer">
			
			<div class="page_title_txt_color" style="margin-bottom: 5px">
				Academic Year : ${academicYear.name} <span style="float: right;"><a href="<c:url value='/fee/report/discountgiven/excel?academicYearId=${academicYear.id}&courseYearId=${courseYear.id}' />">Export To Excel</a></span>
			</div>
			
			
						
			<div class="grid_info">
	          <%-- 
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			     --%>
			</div>
			
				
    		
          	 <table id="peojected_fee_summary_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Student ID</th>
				            <th class="grid_item">Student Name</th>
				            <th class="grid_item">Total Fee (Rs.)</th>
				            <th class="grid_item">Discount Given (Rs.)</th>
				            <th class="grid_item">Payable Fee (Rs.)</th>
			          </tr>
		          </thead>
		          <tbody>
		           <c:forEach var="student" items="${students}">
			          	
			          	<c:if test="${student.discount!=null && student.discount!=0}">
				          	<tr  class="grid_main_row">
					           	<td class="grid_item">
					        		<a href="<c:url value="/fee/admissionrenewal/${student.studentId}" />" >
					        			${student.studentId}
					        		</a>
					        		
					            </td>
					            <td class="grid_item">
					            	
					        		${student.studentFirstName} ${student.studentLastName}
					            </td>
					            <td class="grid_item">
					        		<c:set var="totalFeeSum" value="${totalFeeSum+student.totalFee+student.discount}"></c:set>
					        		${student.totalFee+student.discount}
					            </td>
					            <td class="grid_item">
					        		<c:set var="discountSum" value="${discountSum+student.discount}"></c:set>
					        		${student.discount}
					            </td>
					             <td class="grid_item">
					        		${student.totalFee+student.discount - student.discount}
					            </td>
					           
			           		</tr>
		           		</c:if>
		           		</c:forEach>
		           	<tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${totalFeeSum}</td>
			            <td class="grid_item sortable">${discountSum}</td>
			            <td class="grid_item sortable">${totalFeeSum - discountSum}</td>
			           
		          </tr>
		          </tfoot>
		          </tbody>
		     </table>
		
		
		     <div class="grid_info">
	           <%--
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			      --%>
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
        	 
       