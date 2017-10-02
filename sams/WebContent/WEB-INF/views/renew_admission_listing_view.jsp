<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">${academicYear.name} Admissions (Admissions to be renewed)</span></div>
		<div id="admission_container" class="working_area_spacer">
			
          	<div class="search_param">
    			 <form id="findForm">	
         		 <input type="hidden" id="academicYearId" name="academicYearId" value="${academicYearId}">
    					<table style="width: 100%">
    						<tr>
    							
								 <td>University</td>
								  <td>
								  	<select id="drpDwnAffiliationAuth" name="affiliationAuthoritId" onchange="findCourses()" style="width: 180px;">
									 <option value=""></option>
									  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
									  		<option value="${affiliationAuth.id}">${affiliationAuth.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
								  <td>Class</td>
								  <td>
								  	<select id="drpDwnCourse" name="courseId" onchange="findActiveClasses()" style="width: 200px;">
									  <option value=""></option>
									  <c:forEach var="course" items="${courses}">
									  		<option value="${course.id}">${course.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
						   		  
								  <td>Yr/Sem</td>
								  <td>
								  <select id="drpDwnClass" name="academicYearClassId" style="width: 200px;">
									  <option value=""></option>
									 
									</select>
								  </td>
    						</tr>
    						<tr>
    							<td valign="top">Student Name</td>
    							<td colspan="5"> <input type="text" name="studentName" style="width: 742px"/> <div class="eg" style="margin-left: 110px">ex. firstname lastname</div> </td>
    							 <td> <input type="button" class="button" value="Search" onclick="findStudentsToBeRenewed()" /> </td>
    							 <td> 
    							 	<c:if test="${null!=academicYearId}">
    							 		<input type="button" class="button" value="New Renewal" onclick="newRenewal()" />
    							 	</c:if>
    							 </td>
    						</tr>
    					</table>
    					</form>
					 </div>
<div id="listContainer">
	<jsp:include page="renew_admission_list_content.jsp"/>
</div>	
    
    		
          	 
						
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>

<script type="text/javascript">

var course_url="<c:url value='/course' />";
var admission_renewal_base_url="<c:url value='/admission/renewadmission' />";
$(document).ready(function() {
	
	bindStudentListWithDataTable();
} );

function bindStudentListWithDataTable(){
	
	$('#admitted_students_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        
	    } );
	
}






</script>
        	 
       