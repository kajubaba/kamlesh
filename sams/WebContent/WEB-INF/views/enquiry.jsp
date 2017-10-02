  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<jsp:include page="sams_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">Enquiry</span></div>
		<div>       
            <div class="form_container">
              <form id="enquiryForm" name="enquiryForm">
                	<input type="hidden" id="academicYearId" value="${activeAcademicYearId}">
                	<input type="hidden" name="id" value="${enquiry.id}">
	                <input type="hidden" name="studentPrevClass.id" value="${enquiry.studentPrevClass.id}">
	                <input type="hidden" name="studentInternalExamInfo.id" value="${enquiry.studentInternalExamInfo.id}">
	                <input type="hidden" name="enquiryStatus.id" value="${enquiry.enquiryStatus.id}">
	              	<input type="hidden" name="studentCompetitiveExamInfo.id" value="${enquiry.studentCompetitiveExamInfo.id}">
				
				<c:if test="${instituteSetting.enquirySettings.enableRegistered==true}">
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							<td class="label">Registration No</td>
							<td class="data_field_width"> <input id="registrationNo" name="registrationNo" type="text" value="${enquiry.registrationNo}" maxlength="10"/> </td>
							<td class="label">Registration Fee</td>
							<td class="data_field_width"> <input id="registrationFee" name="registrationFee"  type="text" value="${enquiry.registrationFee}" class="numeric" maxlength="5"/> </td>
						</tr>
					</table>
					<br/>
					<div class="separatpr_line" style="width:100%"></div>
					<br/>
				</c:if>
				
				<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
						<td class="label">Enquiry Type</td>
						<td colspan="5">  <input type="radio" name="type" checked="checked" value="face_to_face"  <c:if test="${'face_to_face' == enquiry.type}">checked</c:if> /> Face to face counselling &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="telephonic" <c:if test="${'telephonic' == enquiry.type}">checked</c:if> />Telephonic counselling&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="on_visit"  <c:if test="${'on_visit' == enquiry.type}">checked</c:if>/>Counselling on visit</td>
					</tr>
					
				</table>
				<br/><div class="separatpr_line" style="width:100%"></div><br/>
				
				<div style="background-color:#FCFCFC;border-bottom:1px solid #FFFFFF">
						<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
	                      <td class="label" > University</td>
						  <td class="data_field_width">
						  	<select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" class="data_field_width" onchange="findCourses()" style="height: 22px">
							 	<option value=""></option>
							  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
							  		<option value="${affiliationAuth.id}" <c:if test="${null!= enquiry.academicYearClass && affiliationAuth.id == enquiry.academicYearClass.courseYear.course.affiliatedTo.id}">selected </c:if> >${affiliationAuth.displayName}</option>
							  </c:forEach>
							</select>
						  </td>
						  <td class="label"> Class</td>
						  <td class="data_field_width">
						  	<select id="drpDwnCourse" name="drpDwnCourse" class="data_field_width" onchange="findActiveClasses()" style="height: 22px">
							  <option value=""></option>
							  <c:forEach var="course" items="${courses}">
							  		<option value="${course.id}"  <c:if test="${null!= enquiry.academicYearClass && course.id == enquiry.academicYearClass.courseYear.course.id}">selected</c:if> >${course.displayName}</option>
							  </c:forEach>
							</select>
						  </td>
				   		  
						  <td class="label"> Yr/Sem</td>
						  <td class="data_field_width">
						  <select id="drpDwnClass" name="academicYearClass.id"  class="data_field_width" style="height: 22px">
							  <option value=""></option>
							   <c:forEach var="academicYearClass" items="${classes}">
							  		<option value="${academicYearClass.id}"  <c:if test="${null!= enquiry.academicYearClass && academicYearClass.id == enquiry.academicYearClass.id}">selected</c:if>  >${academicYearClass.displayName}</option>
							  </c:forEach>
							</select>
						  </td>
						  <td>&nbsp;   </td>
					</tr>
				</table>
			</div>
			
				<br/>
				<div class="separatpr_line" style="width:100%">Student Information</div>
				<br/>
					
				<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
	                      <td class="label" ><span class="mandatory_mark">*</span> First Name</td>
						  <td class="data_field_width"><input type="text" id="studentFirstName" name="studentFirstName" value="${enquiry.studentFirstName}" size="29" maxlength="126"/></td>
						  
						  
						  <td class="label">Middle Name</td>
						  <td class="data_field_width"><input type="text" id="studentMiddleName" name="studentMiddleName" value="${enquiry.studentMiddleName}" size="29" maxlength="126"/></td>
				   		  
						  <td class="label"><span class="mandatory_mark">*</span> Last Name</td>
						  <td class="data_field_width"><input type="text" id="studentLastName" name="studentLastName" value="${enquiry.studentLastName}" size="29" maxlength="126"/></td>
					</tr>
					
					<tr>
						<td class="label"> S/O D/O</td>
						<td colspan="5"><input type="text" id="studentDependsOn" name="studentDependsOn" value="${enquiry.studentDependsOn}" size="29" maxlength="126"/></td>
					</tr>
					
					<tr>
	                      <td class="label" >DOB </td>
	                      
						 
						  <td class="data_field_width"><input type="text" id="studentDob" name="studentDobString" readonly="readonly" size="26" value='<fmt:formatDate pattern="dd-MMM-yyyy" value="${enquiry.studentDob}" />' /></td>
						  
						  </td>
						  <td class="label"><span class="mandatory_mark">*</span> Gender</td>
						  <td class="data_field_width" style="vertical-align:top"><input type="radio" name="studentGender" value="Male" <c:if test="${'Male' == enquiry.studentGender}">checked</c:if>/>
                    Male
                    <input type="radio" name="studentGender" value="Female" <c:if test="${'Female' == enquiry.studentGender}">checked</c:if>/>
                    Female </td>
				   		  
						  <td class="label">Category</td>
						  <td class="data_field_width">
						  	<select class="data_field_width" id="category" name="studentCategory.id">
							  <option value="1" <c:if test="${'1' == enquiry.studentCategory.id}">Selected</c:if>  >General</option>
							  <option value="2"  <c:if test="${'2' == enquiry.studentCategory.id}">Selected</c:if> >OBC</option>
							  <option value="3" <c:if test="${'3' == enquiry.studentCategory.id}">Selected</c:if> >SC</option>
							  <option value="4" <c:if test="${'4' == enquiry.studentCategory.id}">Selected</c:if> >ST</option>
							</select>
						</td>
					</tr>
					<tr>
	                      <td class="label" ><span class="mandatory_mark">*</span> Phone 1 #</td>
						  <td class="data_field_width"><input type="text" id="studentPhone1" name="studentPhone1" value="${enquiry.studentPhone1}" size="29" maxlength="12" class="numeric"/></td>
						  
						  </td>
						  <td class="label">Phone 2 #</td>
						  <td class="data_field_width"><input type="text" id="studentPhone2" name="studentPhone2" value="${enquiry.studentPhone2}" size="29" maxlength="12" class="numeric"/></td>
				   		  
						  <td class="label">Phone 3 #</td>
						  <td class="data_field_width"><input type="text" id="studentPhone3" name="studentPhone3" value="${enquiry.studentPhone3}" size="29" maxlength="12" class="numeric"/></td>
					</tr>
					<tr>
						<td class="label"> Email</td>
						<td colspan="5"><input type="text" id="studentEmailId" name="studentEmailId" value="${enquiry.studentEmailId}" size="29" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label"> <input type="button" value="Check Availability" class="button" onclick="checkAvailability();"/>  </td>
						<td colspan="5"> <div id="availabilitystatus" style="color: green;font-weight: bold;"></div>  </td>
					</tr>
					</table>
					<br/>
					<div class="separatpr_line" style="width:100%">Student Address</div>
					<br/>
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
						<td class="label">Line 1</td>
						<td  colspan="5" ><input id="address_line1" name="address.line1" type="text" size="85" value="${enquiry.address.line1}" maxlength="510"/></td>
					</tr>
					<tr>
						<td class="label">Colony/Town</td>
						<td  colspan="5" ><input id="address_line2" name="address.line2" type="text" size="85" value="${enquiry.address.line2}" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label">City</td>
						<td class="data_field_width"><input id="address_city" name="address.city" type="text" size="29" value="${enquiry.address.city}" maxlength="126"/></td>
						<td class="label">Teh. </td>
						<td class="data_field_width"><input id="address_teh" name="address.teh" type="text" size="29" value="${enquiry.address.teh}" maxlength="126"/></td>
						<td class="label">Dist.</td>
						<td class="data_field_width"><input id="address_district" name="address.district" type="text" size="29" value="${enquiry.address.district}" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label">State</td>
						<td class="data_field_width">
							  <select id="state_id" name="state.id" class="data_field_width">
								 <c:forEach var="state" items="${states}">
								  		<option value="${state.id}" <c:if test="${state.id == enquiry.state.id}">selected</c:if> >${state.displayName}</option>
								  </c:forEach>
							  </select>
						
						</td>
						<td class="label">Country</td>
						<td class="data_field_width">
							 <select id="country_id" name="country.id" class="data_field_width">
								 <c:forEach var="country" items="${countries}">
								  		<option value="${country.id}"  <c:if test="${country.id == enquiry.country.id}">selected</c:if> >${country.name}</option>
								  </c:forEach>
							  </select>
						</td>
						<td class="label">Zip / Postal</td>
						<td class="data_field_width"><input type="text" name="address.zipCode" size="29" value="${enquiry.address.zipCode}"/></td>
					</tr>

				</table>
					
					<c:if test="${instituteSetting.enquirySettings.enablePreviousClass==true}">
						<br/>
						<div class="separatpr_line" style="width:100%">Previous Class</div>
						<br/>
						<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
								 <td class="label">Class Name</td>
								 <td class="data_field_width" ><input type="text" name="studentPrevClass.className" value="${enquiry.studentPrevClass.className}" size="29" maxlength="126"/></td>
								 <td class="label">School/College</td>
								 <td class="data_field_width"><input type="text" name="studentPrevClass.instituteName" value="${enquiry.studentPrevClass.instituteName}" size="29" maxlength="126"/></td>
								 <td class="label">City/Town</td>
								 <td class="data_field_width"><input type="text" name="studentPrevClass.city" value="${enquiry.studentPrevClass.city}" size="29" maxlength="126"/></td>
								 
							</tr>
							<tr>
								<td class="label">University/Board</td>
								<td class="data_field_width"> <input id="studentPrevClassBoard" name="studentPrevClass.board" type="text" size="29" value="${enquiry.studentPrevClass.board }" maxlength="126"/> </td>
								<td class="label">Roll No #</td>
								<td class="data_field_width"> <input id="rollNo" name="studentPrevClass.rollNo" type="text" size="29" maxlength="20" value="${enquiry.studentPrevClass.rollNo }"/> </td>
								<td class="label">Status</td>
								<td class="data_field_width">
										<select class="data_field_width" name="studentPrevClass.studentStatus">
											<option value=""></option>
											<option value="pursuing" <c:if test="${'pursuing' == enquiry.studentPrevClass.studentStatus}">selected</c:if>  >Pursuing</option>
											<option value="result awaited" <c:if test="${'result awaited' == enquiry.studentPrevClass.studentStatus}">selected</c:if>  >Result Awaited</option>
											<option value="result declared"  <c:if test="${'result declared' == enquiry.studentPrevClass.studentStatus}">selected</c:if> >Result Declared </option>
											<option value="fail"  <c:if test="${'fail' == enquiry.studentPrevClass.studentStatus}">selected</c:if> >Fail </option>
											
										</select>
									
									</td>
							</tr>
							
							
							<%-- 
							
							<tr>
								 <td class="label">Physics Marks #</td>
								 <td class="data_field_width" ><input type="text" name="studentPrevClass.physicsMarks" value="${enquiry.studentPrevClass.physicsMarks}" size="29" maxlength="5" class="numeric"/></td>
								 <td class="label">Chemistry Marks #</td>
								 <td class="data_field_width"><input type="text" name="studentPrevClass.chemistryMarks" value="${enquiry.studentPrevClass.chemistryMarks}" size="29" maxlength="5" class="numeric"/></td>
								 <td class="label">Maths Marks #</td>
								 <td class="data_field_width"><input type="text" name="studentPrevClass.mathsMarks" value="${enquiry.studentPrevClass.mathsMarks}" size="29" maxlength="5" class="numeric"/></td>
								 
							</tr>
								<tr>
								<td class="label">Total Marks #</td>
								<td class="data_field_width" ><input type="text" name="studentPrevClass.totalMarks" value="${enquiry.studentPrevClass.totalMarks}" size="29" maxlength="5" class="numeric"/></td>
								<td colspan="4">&nbsp;</td>
							</tr>
							--%>
						
							<%-- 
							<tr>
								<td class="label">Status</td>
								<td colspan="5">  <input type="radio" name="studentPrevClass.studentStatus" value="pursuing" <c:if test="${'pursuing' == enquiry.studentPrevClass.studentStatus}">checked</c:if>/>Pursuing &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="studentPrevClass.studentStatus" value="result awaited" <c:if test="${'result awaited' == enquiry.studentPrevClass.studentStatus}">checked</c:if>/>Result Awaited&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="studentPrevClass.studentStatus" value="result declared" <c:if test="${'result declared' == enquiry.studentPrevClass.studentStatus}">checked</c:if>/>Result Declared   </td>
							</tr>
						
							--%>
						</table>
						
						<div style="text-align: center;">
						<table id="mdt" class="grid grid_color_theme_border" style="width: 300px;">
							<thead>
								<tr class="grid_heading grid_heading_theme">
									<th class="grid_item">Subject</th>
									<th class="grid_item">Total Marks</th>
									<th class="grid_item">Marks Obtained</th>
								</tr>
							</thead>
							<tbody>
								
									<tr class="grid_main_row">
										<td class="grid_item">
										<input type="hidden" name="studentPrevClass.subjectMarks[0].id" value="${enquiry.studentPrevClass.subjectMarks[0].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[1].id" value="${enquiry.studentPrevClass.subjectMarks[1].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[2].id" value="${enquiry.studentPrevClass.subjectMarks[2].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[3].id" value="${enquiry.studentPrevClass.subjectMarks[3].id}" />
										<input type="hidden" name="studentPrevClass.subjectMarks[4].id" value="${enquiry.studentPrevClass.subjectMarks[4].id}" />
										
										 <input type="text" name="studentPrevClass.subjectMarks[0].subject" value="${enquiry.studentPrevClass.subjectMarks[0].subject}" />  </td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[0].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[0].totalMarks}"/></td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[0].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[0].marksObtained}"/></td>
									</tr>
									<tr class="grid_main_row">
										<td class="grid_item"> <input type="text" name="studentPrevClass.subjectMarks[1].subject" value="${enquiry.studentPrevClass.subjectMarks[1].subject}"/>  </td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[1].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[1].totalMarks}"/></td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[1].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[1].marksObtained}"/></td>
									</tr>
									<tr class="grid_main_row">
										<td class="grid_item"> <input type="text" name="studentPrevClass.subjectMarks[2].subject" value="${enquiry.studentPrevClass.subjectMarks[2].subject}"/>  </td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[2].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[2].totalMarks}"/></td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[2].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[2].marksObtained}"/></td>
									</tr>
									<tr class="grid_main_row">
										<td class="grid_item"> <input type="text" name="studentPrevClass.subjectMarks[3].subject" value="${enquiry.studentPrevClass.subjectMarks[3].subject}"/>  </td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[3].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[3].totalMarks}"/></td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[3].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[3].marksObtained}"/></td>
									</tr>
									<tr class="grid_main_row">
										<td class="grid_item"> <input type="text" name="studentPrevClass.subjectMarks[4].subject" value="${enquiry.studentPrevClass.subjectMarks[4].subject}"/>  </td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[4].totalMarks" value="${enquiry.studentPrevClass.subjectMarks[4].totalMarks}"/></td>
										<td class="grid_item"><input type="text" class="numeric" name="studentPrevClass.subjectMarks[4].marksObtained" value="${enquiry.studentPrevClass.subjectMarks[4].marksObtained}"/></td>
									</tr>
								
							</tbody>
			</table>
				</div>		
					</c:if>
				
					
					<c:if test="${instituteSetting.enquirySettings.enableInternalExam==true}">
						<br/>
						<div class="separatpr_line" style="width:100%">Internal Exam Details (If any)</div>
						<br/>
					
						<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
								<td class="label">Exam Name</td>
								 <td class="data_field_width"><input type="text" name="studentInternalExamInfo.examName" value="${enquiry.studentInternalExamInfo.examName}" size="29" maxlength="126"/></td>
								 <td class="label">Roll No #</td>
								 <td class="data_field_width"><input type="text" name="studentInternalExamInfo.rollNo" value="${enquiry.studentInternalExamInfo.rollNo}" size="29" maxlength="126"/></td>
								 <td class="label">Marks #</td>
								 <td class="data_field_width"><input type="text" name="studentInternalExamInfo.totalMarks" value="${enquiry.studentInternalExamInfo.totalMarks}" size="29" maxlength="18"/></td>
							</tr>
						</table>
					</c:if>
					<c:if test="${instituteSetting.enquirySettings.enableCompetitiveExam==true}">
						<br/>
						<div class="separatpr_line" style="width:100%">Competitive Exam Details (If any)</div>
						<br/>
						<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							<td class="label">Exam Name</td>
							 <td class="data_field_width"><input type="text" name="studentCompetitiveExamInfo.examName" value="${enquiry.studentCompetitiveExamInfo.examName}"  size="29" maxlength="126"/></td>
							 <td class="label">Roll No #</td>
							 <td class="data_field_width"><input type="text" name="studentCompetitiveExamInfo.rollNo" value="${enquiry.studentCompetitiveExamInfo.rollNo}" size="29" maxlength="126"/></td>
							 <td class="label">Marks #</td>
							 <td class="data_field_width"><input type="text" name="studentCompetitiveExamInfo.totalMarks" value="${enquiry.studentCompetitiveExamInfo.totalMarks}" size="29" maxlength="10" class="numeric"/></td>
						</tr>
						</table>
					</c:if>
										
					<%-- 
					
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
	                      <td class="label" >Enquired By</td>
						  <td class="data_field_width"><input type="text" size="29"/></td>
						  <td class="label">Relationship</td>
						  <td colspan="3">
						  <select class="data_field_width">
							  <option>Father</option>
							  <option>Mother</option>
							  <option>Brother</option>
							  <option>Sister</option>
							  <option>Uncle</option>
							  <option>Friend</option>
							  <option>Other</option>
							</select>
						  
						  </td>
				   		  
						
					</tr>
					<tr>
	                      <td class="label" >Phone 1 #</td>
						  <td class="data_field_width"><input type="text" size="29"/></td>
						  
						  </td>
						  <td class="label">Phone 2 #</td>
						  <td class="data_field_width"><input type="text" size="29"/></td>
				   		  
						  <td class="label">Phone 3 #</td>
						  <td class="data_field_width"><input type="text" size="29"/></td>
					</tr>
					<tr>
						<td class="label">Line 1</td>
						<td  colspan="5" ><input type="text" size="85"/></td>
					</tr>
					<tr>
						<td class="label">City / Town</td>
						<td class="data_field_width"><input type="text" size="29"/></td>
						<td class="label">Teh. </td>
						<td class="data_field_width"><input type="text" size="29"/></td>
						<td class="label">Dist.</td>
						<td class="data_field_width"><input type="text" size="29"/></td>
					</tr>
					<tr>
						<td class="label">State</td>
						<td class="data_field_width">
							<select class="data_field_width">
								 <c:forEach var="state" items="${states}">
								  		<option value="${state.id}">${state.displayName}</option>
								  </c:forEach>
							  </select>
						
						</td>
						<td class="label">Country</td>
						<td class="data_field_width">
							<select class="data_field_width">
								 <c:forEach var="country" items="${countries}">
								  		<option value="${country.id}">${country.name}</option>
								  </c:forEach>
							  </select>
						
						</td>
						<td class="label">Zip / Postal</td>
						<td class="data_field_width"><input type="text" size="29"/></td>
					</tr>
</table>
--%>
<br/>
					<div class="separatpr_line" style="width:100%"></div>
					<br/>
                     <div style="text-align:center">
                          <input type="button" value="Cancel" onClick="showEnquiryListing($('#academicYearId').val());" class="button"/>
	                      <security:authorize access="hasRole('ROLE_ENQUIRY_ADD_EDIT')">
	                        <c:choose>
	                        	<c:when test="${action=='update'}">
	                        		<c:if test="${activeAcademicYearId==enquiry.academicYear.id}">
	                          			<input type="button" value="  Update  " onclick="validateForm('update');" class="button"/>
	                          		</c:if>
	                        	</c:when>
	                        	<c:otherwise>
									<input type="button" value="  Save  " onclick="validateForm('add');" class="button"/>	                        			
	                        	</c:otherwise>
	                        </c:choose>
	                        
	                          		
	                      </security:authorize>  	
                        
                     </div>

              </form>
            </div>
         
     </div>
   </div>
 </div>     
<jsp:include page="sams_footer.jsp"/> 
 
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
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
}
$(document).ready(function() {
	$(".numeric").numeric(false,false);
	bindDatePicker();
	bindMarksWithDataTable();
} );
//-->
</script>