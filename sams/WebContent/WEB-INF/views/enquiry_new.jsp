  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<jsp:include page="sams_header_new.jsp"/>


 <!-- Row -1 -->
      <div class="row">
        <div class="col-md-12">
          <h3 class="page-header">
            <div class="row">
              <div class="col-md-12">
              		<i class="fa fa-dashboard"></i> 
              		Enquiry 
              </div>
			</div>
          </h3>
        </div>
      </div>
 <!--// Row -1 -->
 
 
 
 <!-- Row -6 -->
 
<form id="enquiryForm" name="enquiryForm">
                	<input type="hidden" id="academicYearId" value="${activeAcademicYearId}">
                	<input type="hidden" name="id" value="${enquiry.id}">
	                <input type="hidden" name="studentPrevClass.id" value="${enquiry.studentPrevClass.id}">
	                <input type="hidden" name="studentInternalExamInfo.id" value="${enquiry.studentInternalExamInfo.id}">
	                <input type="hidden" name="enquiryStatus.id" value="${enquiry.enquiryStatus.id}">
	              	<input type="hidden" name="studentCompetitiveExamInfo.id" value="${enquiry.studentCompetitiveExamInfo.id}"> 

<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Enquiry Class/Type</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">University</td>
			      					<td>
			      						 <select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" class="form-control" onchange="findCourses()" >
											 <option value=""></option>
											  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
											  		<option value="${affiliationAuth.id}" <c:if test="${null!= enquiry.academicYearClass && affiliationAuth.id == enquiry.academicYearClass.courseYear.course.affiliatedTo.id}">selected </c:if> >${affiliationAuth.displayName}</option>
											  </c:forEach>
											</select>
			      					</td>
			      					<td class="profile-label col-md-2">Class</td>
			      					<td>
			      						 <select id="drpDwnCourse" name="drpDwnCourse" class="form-control" onchange="findActiveClasses()" >
										  <option value=""></option>
										  <c:forEach var="course" items="${courses}">
										  		<option value="${course.id}"  <c:if test="${null!= enquiry.academicYearClass && course.id == enquiry.academicYearClass.courseYear.course.id}">selected</c:if> >${course.displayName}</option>
										  </c:forEach>
										</select>
			      					</td>
			      					<td class="profile-label col-md-2">Yr/Sem</td>
			      					<td>
			      						<select id="drpDwnClass" name="academicYearClass.id"  class="form-control" >
										  <option value=""></option>
										   <c:forEach var="academicYearClass" items="${classes}">
										  		<option value="${academicYearClass.id}"  <c:if test="${null!= enquiry.academicYearClass && academicYearClass.id == enquiry.academicYearClass.id}">selected</c:if>  >${academicYearClass.displayName}</option>
										  </c:forEach>
										</select>
			      					</td>
			      					
			      					
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">Enquiry Type</td>
			      					<td colspan="5">
			      						 <input type="radio" name="type" checked="checked" value="face_to_face"  <c:if test="${'face_to_face' == enquiry.type}">checked</c:if> /> Face to face counselling &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="telephonic" <c:if test="${'telephonic' == enquiry.type}">checked</c:if> />Telephonic counselling&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="on_visit"  <c:if test="${'on_visit' == enquiry.type}">checked</c:if>/>Counselling on visit
			      					</td>
			      					
			      					
			      				</tr>
			      				
			      		</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>     
 
<!--// Row -6 -->
 
 
 <!-- Row -6 -->
<c:if test="${instituteSetting.enquirySettings.enableRegistered==true}">
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Registration Details</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">Registration No</td>
			      					<td>
			      						<input id="registrationNo" name="registrationNo" class="form-control" type="text" value="${enquiry.registrationNo}" maxlength="10"/>
			      					</td>
			      					<td class="profile-label col-md-2">Registration Fee</td>
			      					<td><input id="registrationFee" name="registrationFee" class="form-control"  type="text" value="${enquiry.registrationFee}" class="numeric" maxlength="5"/></td>
			      					<td class="col-md-2"></td>
			      					<td class="col-md-2"></td>
			      					
			      				</tr>
			      		</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>     
</c:if>  
<!--// Row -6 -->
 
 
 
<!-- Row -2 -->
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Student Information </div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">First Name <span class="color-red">*</span></td>
			      					<td>
			      						<input type="text" id="studentFirstName" name="studentFirstName" value="${enquiry.studentFirstName}" class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Middle Name</td>
			      					<td><input type="text" id="studentMiddleName" name="studentMiddleName" value="${enquiry.studentMiddleName}" class="form-control" maxlength="126"/></td>
			      					<td class="profile-label col-md-2">Last Name <span class="color-red">*</span></td>
			      					<td><input type="text" id="studentLastName" name="studentLastName" value="${enquiry.studentLastName}" class="form-control" maxlength="126"/></td>
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">S/O D/O</td>
			      					<td>
			      						<input type="text" id="studentDependsOn" name="studentDependsOn" value="${enquiry.studentDependsOn}" class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">DOB</td>
			      					<td>
			      						<input type="text" id="studentDob" name="studentDobString" readonly="readonly" class="form-control" value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${enquiry.studentDob}" />' />
			      					</td>
			      					<td class="profile-label col-md-2">Gender <span class="color-red">*</span></td>
			      					<td>
			      						<input type="radio" name="studentGender" value="Male" <c:if test="${'Male' == enquiry.studentGender}">checked</c:if>/>
			      						 Male
                    <input type="radio" name="studentGender" value="Female" <c:if test="${'Female' == enquiry.studentGender}">checked</c:if>/>
                    Female
			      					</td>
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">Phone 1 #<span class="color-red">*</span></td>
			      					<td>
			      						<input type="text" id="studentPhone1" name="studentPhone1" value="${enquiry.studentPhone1}" class="form-control" maxlength="12" class="numeric"/>
			      					</td>
			      					<td class="profile-label col-md-2">Phone 2 #</td>
			      					<td><input type="text" id="studentPhone2" name="studentPhone2" value="${enquiry.studentPhone2}" class="form-control" maxlength="12" class="numeric"/></td>
			      					<td class="profile-label col-md-2">Phone 3 #</td>
			      					<td><input type="text" id="studentPhone3" name="studentPhone3" value="${enquiry.studentPhone3}" class="form-control" maxlength="12" class="numeric"/></td>
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">Email</td>
			      					<td>
			      						<input type="text" id="studentEmailId" name="studentEmailId" value="${enquiry.studentEmailId}" class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Category</td>
			      					<td>
			      						<select id="category" name="studentCategory.id" class="form-control">
							  <option value="1" <c:if test="${'1' == enquiry.studentCategory.id}">Selected</c:if>  >General</option>
							  <option value="2"  <c:if test="${'2' == enquiry.studentCategory.id}">Selected</c:if> >OBC</option>
							  <option value="3" <c:if test="${'3' == enquiry.studentCategory.id}">Selected</c:if> >SC</option>
							  <option value="4" <c:if test="${'4' == enquiry.studentCategory.id}">Selected</c:if> >ST</option>
							</select>
			      					</td>
			      					<td></td>
			      					<td></td>
			      					
			      				</tr>
			      			</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>        
<!--// Row -2 -->


<!-- Row -3 -->
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Address</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">Line 1</td>
			      					<td colspan="3">
			      						<input id="address_line1" name="address.line1" type="text" class="form-control" value="${enquiry.address.line1}" maxlength="510"/>
			      					</td>
			      					<td class="profile-label col-md-2">Colony/Town</td>
			      					<td><input id="address_line2" name="address.line2" type="text" class="form-control" value="${enquiry.address.line2}" maxlength="126"/></td>
			      					
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">City</td>
			      					<td>
			      						<input id="address_city" name="address.city" type="text" class="form-control" value="${enquiry.address.city}" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Teh.</td>
			      					<td>
			      						<input id="address_teh" name="address.teh" type="text" class="form-control" value="${enquiry.address.teh}" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Dist.</td>
			      					<td>
			      						<input id="address_district" name="address.district" type="text" class="form-control" value="${enquiry.address.district}" maxlength="126"/>
			      					</td>
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">State</td>
			      					<td>
			      						<select id="state_id" name="state.id" class="form-control">
											 <c:forEach var="state" items="${states}">
											  		<option value="${state.id}" <c:if test="${state.id == enquiry.state.id}">selected</c:if> >${state.displayName}</option>
											  </c:forEach>
										  </select>
			      					</td>
			      					<td class="profile-label col-md-2">Country</td>
			      					<td>
			      						 <select id="country_id" name="country.id" class="form-control">
											 <c:forEach var="country" items="${countries}">
											  		<option value="${country.id}"  <c:if test="${country.id == enquiry.country.id}">selected</c:if> >${country.name}</option>
											  </c:forEach>
										  </select>
			      					</td>
			      					<td class="profile-label col-md-2">Zip / Postal</td>
			      					<td><input type="text" name="address.zipCode" class="form-control" value="${enquiry.address.zipCode}"/></td>
			      				</tr>
			      		
			      			</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>        
<!--// Row -3 -->

<!-- Row -4 -->
<c:if test="${instituteSetting.enquirySettings.enablePreviousClass==true}">
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Previous Class Information</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">Class Name</td>
			      					<td>
			      						<input type="text" name="studentPrevClass.className" value="${enquiry.studentPrevClass.className}" class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">School/College</td>
			      					<td><input type="text" name="studentPrevClass.instituteName" value="${enquiry.studentPrevClass.instituteName}" class="form-control" maxlength="126"/></td>
			      					<td class="profile-label col-md-2">City/Town</td>
			      					<td><input type="text" name="studentPrevClass.city" value="${enquiry.studentPrevClass.city}" class="form-control" maxlength="126"/></td>
			      					
			      				</tr>
			      				<tr>
			      					<td class="profile-label col-md-2">University/Board</td>
			      					<td>
			      						<input id="studentPrevClassBoard" name="studentPrevClass.board" type="text" class="form-control" value="${enquiry.studentPrevClass.board }" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Roll No #</td>
			      					<td>
			      						 <input id="rollNo" name="studentPrevClass.rollNo" type="text" class="form-control" maxlength="20" value="${enquiry.studentPrevClass.rollNo }"/>
			      					</td>
			      					<td class="profile-label col-md-2">Status</td>
			      					<td>
			      						<select class="form-control" name="studentPrevClass.studentStatus">
											<option value=""></option>
											<option value="pursuing" <c:if test="${'pursuing' == enquiry.studentPrevClass.studentStatus}">selected</c:if>  >Pursuing</option>
											<option value="result awaited" <c:if test="${'result awaited' == enquiry.studentPrevClass.studentStatus}">selected</c:if>  >Result Awaited</option>
											<option value="result declared"  <c:if test="${'result declared' == enquiry.studentPrevClass.studentStatus}">selected</c:if> >Result Declared </option>
											<option value="fail"  <c:if test="${'fail' == enquiry.studentPrevClass.studentStatus}">selected</c:if> >Fail </option>
											
										</select>
			      					</td>
			      				</tr>
			      				
			      		
			      			</tbody>
			      	</table>
			      	
			      	<h4>Score</h4>
			      	<table class="table-responsive table" style="width: 50%">
			      			
			      			<thead>
			      				<tr>
			      					<th>Subject</th>
			      					<th>Total Marks</th>
			      					<th>Marks Obtained</th>
			      				</tr>
			      			</thead>
			      			
			      			<tbody>
			      				<tr>
			      					<td>
			      						<input type="hidden" name="studentPrevClass.subjectMarks[0].id" value="${enquiry.studentPrevClass.subjectMarks[0].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[1].id" value="${enquiry.studentPrevClass.subjectMarks[1].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[2].id" value="${enquiry.studentPrevClass.subjectMarks[2].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[3].id" value="${enquiry.studentPrevClass.subjectMarks[3].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[4].id" value="${enquiry.studentPrevClass.subjectMarks[4].id}" />
			      						
			      						 <input type="text" class="form-control" name="studentPrevClass.subjectMarks[0].subject" value="${enquiry.studentPrevClass.subjectMarks[0].subject}" /> 
			      					</td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[0].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[0].totalMarks}"/></td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[0].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[0].marksObtained}"/></td>
			      				</tr>
			      				<tr>
			      					<td> <input type="text" class="form-control" name="studentPrevClass.subjectMarks[1].subject" value="${enquiry.studentPrevClass.subjectMarks[1].subject}" /> </td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[1].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[1].totalMarks}"/></td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[1].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[1].marksObtained}"/></td>
			      				</tr>
			      				<tr>
			      					<td> <input type="text" class="form-control" name="studentPrevClass.subjectMarks[2].subject" value="${enquiry.studentPrevClass.subjectMarks[2].subject}" /> </td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[2].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[2].totalMarks}"/></td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[2].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[2].marksObtained}"/></td>
			      				</tr>
			      				<tr>
			      					<td> <input type="text" class="form-control" name="studentPrevClass.subjectMarks[3].subject" value="${enquiry.studentPrevClass.subjectMarks[3].subject}" /> </td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[3].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[3].totalMarks}"/></td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[3].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[3].marksObtained}"/></td>
			      				</tr>
			      				<tr>
			      					<td> <input type="text" class="form-control" name="studentPrevClass.subjectMarks[4].subject" value="${enquiry.studentPrevClass.subjectMarks[4].subject}" /> </td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[4].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[4].totalMarks}"/></td>
			      					<td><input type="text" class="numeric form-control" name="studentPrevClass.subjectMarks[4].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[4].marksObtained}"/></td>
			      				</tr>
			      				
			      		
			      			</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>  
</c:if>      
<!--// Row -4 -->

<!-- Row -5 -->
<c:if test="${instituteSetting.enquirySettings.enableInternalExam==true}">
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Internal Exam Details (If any)</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">Exam Name</td>
			      					<td>
			      						<input type="text" name="studentInternalExamInfo.examName" value="${enquiry.studentInternalExamInfo.examName}" class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Roll No #</td>
			      					<td><input type="text" name="studentInternalExamInfo.rollNo" value="${enquiry.studentInternalExamInfo.rollNo}" class="form-control" maxlength="126"/></td>
			      					<td class="profile-label col-md-2">Marks #</td>
			      					<td><input type="text" name="studentInternalExamInfo.totalMarks" value="${enquiry.studentInternalExamInfo.totalMarks}" class="form-control" maxlength="18"/></td>
			      					
			      				</tr>
			      		</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>     
</c:if>   
<!--// Row -5 -->

<!-- Row -6 -->
<c:if test="${instituteSetting.enquirySettings.enableCompetitiveExam==true}">
<div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
            	<div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i>Competitive Exam Details (If any)</div>
		        <div class="panel-body">	
		        	
		        	<table class="table-responsive table tbl-profile">
			      			<tbody>
			      				<tr>
			      					<td class="profile-label col-md-2">Exam Name</td>
			      					<td>
			      						<input type="text" name="studentCompetitiveExamInfo.examName" value="${enquiry.studentCompetitiveExamInfo.examName}"  class="form-control" maxlength="126"/>
			      					</td>
			      					<td class="profile-label col-md-2">Roll No #</td>
			      					<td><input type="text" name="studentCompetitiveExamInfo.rollNo" value="${enquiry.studentCompetitiveExamInfo.rollNo}" class="form-control" maxlength="126"/></td>
			      					<td class="profile-label col-md-2">Marks #</td>
			      					<td><input type="text" name="studentCompetitiveExamInfo.totalMarks" value="${enquiry.studentCompetitiveExamInfo.totalMarks}" class="form-control" maxlength="10" class="numeric"/></td>
			      					
			      				</tr>
			      		</tbody>
			      	</table>
			     </div> 	
			</div>      			
        </div>
</div>     
</c:if>   
<!--// Row -6 -->

<div class="row">
        <div class="col-md-12 text-center">
        	<a class="btn btn-info" href="javascript:void(0)" onClick="showEnquiryListing($('#academicYearId').val());"><span class="glyphicon glyphicon-arrow-left" ></span> Cancel</a>
        		<security:authorize access="hasRole('ROLE_ENQUIRY_ADD_EDIT')">
        		
        		
        		<c:choose>
	                        	<c:when test="${action=='update'}">
	                        		<c:if test="${activeAcademicYearId==enquiry.academicYear.id}">
	                          			<button type="button" type="button" class="btn btn-info"  onclick="validateForm('update');">Update</button>
	                          			
	                          		</c:if>
	                        	</c:when>
	                        	<c:otherwise>
									<button type="button" class="btn btn-info" onclick="validateForm('add');" >Save</button>	                  
									      			
	                        	</c:otherwise>
	             </c:choose>
	                        
        		
        		
        		</security:authorize>
        </div>
</div>

</form>

  
<jsp:include page="sams_footer_new.jsp"/> 
 
<script type="text/javascript">
<!--
var course_url="<c:url value='/course' />";
var enqUrl="<c:url value='/enquiry'/>";
var enqListUrl="<c:url value='/enquiry/list'/>";

function bindMarksWithDataTable(){
	
	$('#mdt').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      { "bSortable": false },
	                      { "bSortable": false },
	                      { "bSortable": false }
	                      ]

	    } );
	
}


function bindDatePicker(){
	$( "#studentDob" ).datepicker({
		
		dateFormat:'d-M-yy',
		
		changeMonth: true,
		changeYear: true
	});
}
$(document).ready(function() {
	//$(".numeric").numeric(false,false);
	bindDatePicker();
	bindMarksWithDataTable();
} );
//-->
</script>