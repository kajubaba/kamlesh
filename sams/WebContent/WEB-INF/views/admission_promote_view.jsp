<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">Promote Students</span></div>
		<div id="admission_container" class="working_area_spacer">
			<div class="add_new">
         		<c:if test="${academicYearId==1234}">
         			<input type="button" value="Renew Admission" onclick="renewAdmission();" class="button"/>
         		</c:if>
        	</div>
          	<div class="search_param">
    			 <form id="findForm">	
         		 <input type="hidden" id="academicYearId" name="academicYearId" value="${academicYearId}">
    					<table cellpadding="0" cellspacing="10"  border="0">
    						<tr>
    							
								 <td>University</td>
								  <td>
								  	<select id="drpDwnAffiliationAuth" name="affiliationAuthoritId" onchange="findCourses()" style="width: 150px; height: 22px">
									 <option value=""></option>
									  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
									  		<option value="${affiliationAuth.id}">${affiliationAuth.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
								  <td>Class</td>
								  <td>
								  	<select id="drpDwnCourse" name="courseId" onchange="findActivePromotionClasses()" style="width: 200px; height: 22px">
									  <option value=""></option>
									  <c:forEach var="course" items="${courses}">
									  		<option value="${course.id}">${course.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
						   		  
								  <td>Yr/Sem</td>
								  <td>
								  <select id="drpDwnClass" name="academicYearClassId" style="width: 200px; height: 22px">
									  <option value=""></option>
									 
									</select>
								  </td>
								  <td><input type="button" class="button" value="Search" onclick="findStudentsToBePromoted()"/></td>
    							 <td></td>
    							 <td></td>
    							 
    						</tr>
    					</table>
    					</form>
					 </div>
<div id="listContainer">
	<jsp:include page="admission_promote_listing.jsp"/>
</div>	
    
    		
          	 
						
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>

<script type="text/javascript">

var course_url="<c:url value='/course' />";
var promote_base_url="<c:url value='/admission/promote' />";

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
	        "aoColumns": [
						  { "bSortable": false },
	                      null,
	                      null,
	                      null,
	                      { "bSortable": false },
	                      null,
	                      null
	                      ]

	    } );
	
}


function findStudentsToBePromoted(){
	var searchFormData = $("#findForm").serialize();
	$.ajax({
		type : 'GET',
		url : promote_base_url+"/search/",
		data : searchFormData,
		success : findStudentsToBePromotedSuccess
	});	
}


function findStudentsToBePromotedSuccess(response){
		$("#listContainer").html(response);
		bindStudentListWithDataTable();
}

function promoteStudents(){
	
	var data = $("#promoteIdForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : promote_base_url+"/confirm",
		data : data,
		success: promoteStudentsSuccess
	});
}

function promoteStudentsSuccess(response){
	alert("Promoted Successfully");
}

</script>
        	 
       