<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">${academicYear.name} Admissions >> Bus Stop >> ${busStop.name} </span></div>
		<div class="working_area_spacer">
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
    		
          	 <table id="bus_stop_tbl" class="grid grid_color_theme_border">
		          <thead>
		          		
		          		<tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Student Name</th>
				            <th class="grid_item">Gender</th>
				            <th class="grid_item">class</th>
		         		 </tr>
		          </thead>
		          <tbody>
		          
		           <c:forEach var="student" items="${students}">
				           <tr class="grid_main_row">
				           	<td class="grid_item">
				           		<a href="<c:url value='/admission/edit/student/${student.student.studentId}' />">${student.student.firstName} ${student.student.lastName}</a>
				        		
				            </td>
				            <td class="grid_item">
				        		<c:if test="${student.student.gender=='male'}">Male</c:if>
				        		<c:if test="${student.student.gender=='female'}">Female</c:if>
				            </td>
				            <td class="grid_item">
				        		${student.academicYearClass.displayName}
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

$(document).ready(function() {
    $('#bus_stop_tbl').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
       
    } );
} );

</script>
        	 
       