<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">Customize Students</span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<div style="margin-bottom: 10px">
					<span class="label">Academic Year :</span>
					<select id="academicYearId" name="academicYearId">
									<c:forEach var="academicYear" items="${academicYears}">
										<option value="${academicYear.id}" <c:if test="${selectedAcademicYear==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
									</c:forEach>
					</select> 
				
				</div>
				<div>
					<span class="label">Student Name :</span>
					<input id="searchStr" type="text" value="${studentName}" size="40"/>
					&nbsp;&nbsp;&nbsp; <input type="button" value="Search" class="button" onclick="searchCustomizeStudents();" />
				</div>
				<div class="eg" style="margin-left: 110px">ex. firstname lastname</div>
			</div>
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="due-fee-summary-table" class="grid grid_color_theme_border">
		          <thead>
		          		
		          		<tr class="grid_heading grid_heading_theme">
				           
				            <th class="grid_item">Student Name</th>
				            <th class="grid_item">Class</th>
				            <th class="grid_item">Due Date(s)</th>
				            <th class="grid_item">Installment (Rs)</th>
				            <th class="grid_item">User</th>
				            
		         		 </tr>
		          </thead>
		          <tbody>
		          
		           <c:forEach var="student" items="${students}">
				           <tr class="grid_main_row">
				           
				            <td class="grid_item">
				        		<a href="<c:url value='/fee/admissionrenewal/${student.studentId}' />">
				        			${student.firstName} ${student.lastName}
				        		</a>
				            </td>
				            <td class="grid_item">
				        		${student.academicYearClass.courseYear.course.displayName} ${student.academicYearClass.courseYear.name} Yr
				            </td>
				            <td class="grid_item">
				        		<c:set var="emi" value=""></c:set>
				        		<c:forEach var="installment" items="${student.getInstallments(academicYearId)}" varStatus="rowCounter">
				        			<c:choose>
				        				<c:when test="${rowCounter.count !=1}">, 
				        					<c:set var="emi" value="${emi},${installment.totalFee+installment.totalBusFee}"></c:set>
				        				</c:when>
				        				<c:otherwise>
				        					<c:set var="emi" value="${installment.totalFee+installment.totalBusFee}"></c:set>
				        				</c:otherwise>
				        			</c:choose>
				        			<c:choose>
				        				<c:when test="${null==installment.dueDate}">NA</c:when>
				        				<c:otherwise>
				        					<fmt:formatDate pattern="dd-MMM-yyyy" value="${installment.dueDate}" />
				        				</c:otherwise>
				        			</c:choose>
				        			<c:set var="userName" value="${installment.modifiedBy.firstName} ${installment.modifiedBy.lastName}"></c:set>
				        		</c:forEach>
				            </td>
				            <td class="grid_item">
				        		${emi}
				            </td>
				             <td class="grid_item">
				        		${userName}
				            </td>
		           		</tr>
		           		</c:forEach>
		           
		          </tbody>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
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

var reportURL="<c:url value='/fee/customizestudents' />";

$(document).ready(function() {
    $('#due-fee-summary-table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
        "aoColumns": [
                      
                      null,
                      null,
                      { "bSortable": false },
                      
                      { "bSortable": false },
                      null
                      ]

    } );
} );

function searchCustomizeStudents(){
	window.location=reportURL+"/search?studentName="+$("#searchStr").val()+"&academicYearId="+$("#academicYearId").val();
}

</script>
        	 
       