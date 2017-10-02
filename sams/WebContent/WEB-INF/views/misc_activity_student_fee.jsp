<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Misc Program - Student List</span></div>
		<div class="working_area_spacer">
			
			<div>
				${academicYear.name} >> 
				${miscActivityCourseYear.miscActivity.name} >>
				${miscActivityCourseYear.courseYearSetting.courseYear.course.displayName}, ${miscActivityCourseYear.courseYearSetting.courseYear.name} Yr. |
				Rs. ${miscActivityCourseYear.miscActivity.fee}
				<c:set var="miscActivtyFee" value="${miscActivityCourseYear.miscActivity.fee}" ></c:set>
				
				
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(studentList)!=0 }">
			         		Displaying 1-${fn:length(studentList)} of ${fn:length(studentList)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="misc_fee_summary_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Student ID</th>
				            <th class="grid_item">Student Name</th>
				            <th class="grid_item">Current Class</th>
				            <th class="grid_item">Year</th>
				            <th class="grid_item">Fee</th>
				            <th class="grid_item">Paid Fee</th>
				            <th class="grid_item">Remaining</th>
			          </tr>
		          </thead>
		          <tbody>
		           <c:forEach var="studentFee" items="${studentList}">
			          	<tr  class="grid_main_row">
				           	<td class="grid_item">
					        	<a href="<c:url value="/miscfee/${studentFee.studentId}" />">${studentFee.studentId}</a>
				            </td>
				            <td class="grid_item">
				        		${studentFee.studentFirstName} ${studentFee.studentMiddleName} ${studentFee.studentLastName}
				            </td>
				            <td class="grid_item">
				        		${studentFee.currentClass.courseYear.course.displayName}
				            </td>
				            <td class="grid_item">
				        		${studentFee.currentClass.courseYear.name}
				            </td>
				            <td class="grid_item">
				            	<c:set var="feeSum" value="${feeSum+miscActivtyFee}"></c:set>
				            	${miscActivtyFee}
				            </td>
				            <td class="grid_item">
				        		<c:set var="paidFeeSum" value="${paidFeeSum+studentFee.paidFee}"></c:set>
				        		${studentFee.paidFee}
				            </td>
				            <td class="grid_item">
				        		<c:set var="remainingFeeSum" value="${remainingFeeSum+(miscActivtyFee-studentFee.paidFee)}" ></c:set>
				        		${miscActivtyFee-studentFee.paidFee}
				            </td>
				             
		           		</tr>
		           		</c:forEach>
		           	<tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			          
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${feeSum}</td>
			            <td class="grid_item sortable">${paidFeeSum}</td>
			            <td class="grid_item sortable">${remainingFeeSum}</td>
			            
		          </tr>
		          </tfoot>
		          </tbody>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(studentList)!=0 }">
			         		Displaying 1-${fn:length(studentList)} of ${fn:length(studentList)}
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
        	 
       