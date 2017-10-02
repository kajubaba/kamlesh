<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">${academicYear.name} Admissions</span></div>
		<div id="admission_container" class="working_area_spacer">
			
          	<div class="search_param">
    			 <form id="findForm">	
         		 	<input type="hidden" id="academicYearId" name="academicYearId" value="${academicYearId}">
    				<table cellpadding="0" cellspacing="8"  border="0">
    					<tr>
    						 <td>University</td>
							  <td>
								  	<select id="drpDwnAffiliationAuth" name="affiliationAuthoritId" onchange="findCourses()" class="drop-down" style="width: 150px;">
										 <option value=""></option>
										 <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
									  			<option value="${affiliationAuth.id}"   <c:if test="${affiliationAuth.id==academicYearClass.courseYear.course.affiliatedTo.id }">selected="selected"</c:if> >${affiliationAuth.displayName}</option>
									  	</c:forEach>
									</select>
								</td>
								  <td>Class</td>
								  <td>
								  	<select id="drpDwnCourse" name="courseId" onchange="findActiveClasses()" class="drop-down" style="width: 150px;">
									  <option value=""></option>
									  <c:forEach var="course" items="${courses}">
									  		<option value="${course.id}" <c:if test="${course.id==academicYearClass.courseYear.course.id }">selected="selected"</c:if>>${course.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
						   		  
								  <td>Yr/Sem</td>
								  <td>
								  <select id="drpDwnClass" name="academicYearClassId" class="drop-down" style="width: 150px;">
									  <option value=""></option>
									 <c:forEach var="clazz" items="${classes}">
									  		<option value="${clazz.id}" <c:if test="${clazz.id==academicYearClass.id }">selected="selected"</c:if>>${clazz.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
								  <td>Type</td>
    							 <td>
    							 	<select name="admissionTypeId" class="drop-down" style="width: 150px;">
    									<option value=""></option>
    									<option value="1" <c:if test="${admissionTypeId==1}">selected="selected"</c:if>>New</option>
    									<option value="2" <c:if test="${admissionTypeId==2}">selected="selected"</c:if>>Regular</option>
    							 	</select>
    							  </td>
    							 <td></td>
    							 
    						</tr>
    						<tr>
    							<td valign="top">Student Name</td>
    							<td colspan="3"> <input type="text" name="studentName" style="width:350px"/> </td>
    							<td>Bus Stop</td>
    							<td>
    								 <select name="busStopId" class="drop-down" style="width: 150px;">
    									<option value=""></option>
    									<c:forEach var="busStop" items="${busStops}">
									  		<option value="${busStop.id}">${busStop.name}</option>
									  </c:forEach>
    							 	</select>
    							 </td>
    							 <td>Status</td>
    							<td>
    								 <select name="statusId" class="drop-down" style="width: 150px;">
    									<option value=""></option>
    									<option value="7" <c:if test="${studentStatus==7}">selected="selected"</c:if>>Admission withdraw</option>
    									<option value="1" <c:if test="${studentStatus==1}">selected="selected"</c:if>>Cancelled</option>
    									<option value="5" <c:if test="${studentStatus==5}">selected="selected"</c:if>>Confirmed</option>
    									<option value="3" <c:if test="${studentStatus==3}">selected="selected"</c:if>>Degree Awarded</option>
    									<option value="4" <c:if test="${studentStatus==4}">selected="selected"</c:if>>Temporary</option>
    									<option value="2" <c:if test="${studentStatus==2}">selected="selected"</c:if>>Terminated</option>
    									<option value="6" <c:if test="${studentStatus==6}">selected="selected"</c:if>>Temporary Renewed</option>
    							 	</select>
    							 </td>
    							 <td> <input type="button" class="button" value="Search" onclick="findStudents(${academicYearId})" /> </td>
								  <td>
								  		<security:authorize access="hasRole('ROLE_EXPORT_STUDENT')">
								  		 <input type="button" class="button" value="Export" onclick="exportStudents(${academicYearId})" />
								  		 </security:authorize>
								  </td>
								  
    						</tr>
    					</table>
    					</form>
					 </div>
<div id="listContainer">
	<jsp:include page="admission_listing.jsp"/>
</div>	
    
    		
          	 
						
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>
<div id="popupBackground" class="popupBack" onclick="closeChangeStudentStatusPopup()"></div>
<div id="popupChangeStudentStatus" class="popup" style="height: 336px;visibility: visible;">
	<div class="popup_header">Change Student Status </div>	
	<div class="working_area_spacer">
	<div>
		<div style="color: red">
			Following students are not eligible to change the status</br>
			<span id="chgstserror"></span>
		</div>
	
	<form id="changeStatusFormInfo">
	<table cellspacing="10px">
		
		<tr>
			<td>New Status :</td>
			<td>
				<select  id="sttsid" style="width: 278px">
					<option value="7">Admission withdraw</option>
					<option value="1">Cancel Addmission</option>
					<option value="3">Degree Completed</option>
					<option value="2">Terminate Addmission</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Comments :</td>
			<td>
				<textarea id="sttscmts" rows="5" cols="50"></textarea>
			</td>
		</tr>
	</table>
	</form>
	<div style="width: 100%;text-align: center;margin-top: 25px"> 
		<div style="width: 60px; float: left;"  class="action-button" onclick="closeChangeStudentStatusPopup()">Cancel</div>
		<div style="width: 100px; float: left;"  class="action-button" onclick="changeStudentsStatus()">Change Status</div> 
	</div>
</div>
	</div>
</div>
<script type="text/javascript">

var course_url="<c:url value='/course' />";
var admission_list_base_url="<c:url value='/admission/list' />";
var admission_base_url="<c:url value='/admission' />";
var student_activity_base_url="<c:url value='/studentactivity' />";
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
	        "aaSorting": []
	       
	    } );
	
}






</script>
        	 
       