<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title">
			
			<div class="page_title_text" style="float: left;width: 20%">Admission</div>
			
			<div style="overflow: hidden;width: 76%;text-align: right;">
				 	<c:if test="${action!='add'}">
				 		<security:authorize access="hasAnyRole('ROLE_FEE','ROLE_CUST_STUD_FEE')">
				 			<a href="<c:url value='/fee/admissionrenewal/${student.studentId}' />">View Fee summary</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 		</security:authorize>	
		       			 <security:authorize access="hasRole('ROLE_CHANGE_STUDENT_BUS_STOP')">
								<c:if test="${5==student.studentStatus.id || (4==student.studentStatus.id || 6==student.studentStatus.id )&& customized==true}">
									<a href="javascript:void(0)" onclick="getChangeBusStopPopup(${student.id})">Change Current Bus Stop</a>
								</c:if>
						 </security:authorize>
					 </c:if>
			</div>
		</div>
		<form id="studentForm" enctype="multipart/form-data">
            
            <input type="hidden" id="chnageCustBusStop" name="chnageCustBusStop" value="false">
            <input type="hidden" id="studentId" name="id" value="${student.id}">
            <input type="hidden" name="studentId" value="${student.studentId}">
            <input type="hidden" id="academicYearId" name="academicYear.id" value="${activeAcademicYearId}">
            
            <div class="form_container">

    		
    		<div style="background-color:#FCFCFC;border-bottom:1px solid #FFFFFF">
						
					<table style="width: 100%;">
							
							<tr>
								<td>
									<a href="javascript:void(0)" id="std_image">
										<c:choose>
											<c:when test="${student.imageName==null}">
												<img style="border:0px" id="studentImage" height="135px" width="145px" alt="" src="<c:url value="/resources/images/default_theme/image_icon.jpg" />" />
											</c:when>
											<c:otherwise>
												<img style="border:0px" id="studentImage" height="135px" width="145px" alt="" src="<c:url value="/resources/studentpics/${student.imageName}" />" />
											</c:otherwise>
										</c:choose>
									</a>
    								<input id="fileupload" type="file" name="file" data-url="<c:url value="/admission/uploadpic" />" style="display: none;" />
								</td>
								<td valign="top">
									<table cellpadding="0" cellspacing="9">
										<tr>
											<td width="150px" style="text-align: right;" height="20">
												<c:if test="${instituteSetting.admissionSettings.formNoMandatory==true}">
													<span class="mandatory_mark">*</span> 
												</c:if>
												Form No#
											</td>
											<td>
												<c:choose>
													<c:when test="${action=='add' ||  (4==student.studentStatus.id || 6==student.studentStatus.id) && (customized==null ||customized==false)}">
														<input type="text" id="admissionFormNo" name="admissionFormNo" value="${student.admissionFormNo}" size="23"/>
													</c:when>
													<c:otherwise>
														<input type="hidden" id="admissionFormNo" name="admissionFormNo" value="${student.admissionFormNo}" />
														${student.admissionFormNo}
													</c:otherwise>
												</c:choose>
											</td>
											<td  width="150px" style="text-align: right;" height="20">Student ID#</td>
						  					<td> <div id="ggggg">${student.studentId}</div></td>
						  					<td width="150px"></td>
						  					<td></td>
										</tr>
										<tr>
											<td style="text-align: right;"><span class="mandatory_mark">*</span> University</td>
											  <td height="20">
												 <c:choose>
													<c:when test="${action=='add'}">
														<select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" onchange="findCourses()" style="width: 180px">
															 <option value=""></option>
															  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
															  		<option value="${affiliationAuth.id}" >${affiliationAuth.displayName}</option>
															  </c:forEach>
														</select>
													</c:when>
													<c:when test="${(4==student.studentStatus.id || 6==student.studentStatus.id) && (customized==null ||customized==false)}">
														<select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" onchange="findCourses()" style="width: 180px">
															 <option value=""></option>
															  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
															  		<option value="${affiliationAuth.id}" <c:if test="${affiliationAuth.id == student.academicYearClass.courseYear.course.affiliatedTo.id}">selected="selected"</c:if> >${affiliationAuth.displayName}</option>
															  </c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														
														${student.academicYearClass.courseYear.course.affiliatedTo.displayName}
													</c:otherwise>
												</c:choose>
											  </td>
										
										
											<td style="text-align: right;"><span class="mandatory_mark">*</span> Class</td>
											  <td>
											  	<c:choose>
													<c:when test="${action=='add'}">
														<select id="drpDwnCourse" name="drpDwnCourse" onchange="findActiveClasses()" style="width: 180px">
														  <option value=""></option>
														</select>
													</c:when>
													<c:when test="${(4==student.studentStatus.id || 6==student.studentStatus.id) && (customized==null ||customized==false)}">
														<select id="drpDwnCourse" name="drpDwnCourse" class="data_field_width" onchange="findActiveClasses()" style="width: 180px">
														  <option value=""></option>
														  <c:forEach var="course" items="${courses}">
															  		<option value="${course.id}" <c:if test="${course.id == student.academicYearClass.courseYear.course.id}">selected="selected"</c:if> >${course.displayName}</option>
															  </c:forEach>
														</select>
													</c:when>
													<c:otherwise>
														${student.academicYearClass.courseYear.course.displayName}
													</c:otherwise>
												</c:choose>
											  </td>
										
										
											<td style="text-align: right;"><span class="mandatory_mark">*</span> Yr/Sem</td>
											  <td>
											  	<c:choose>
													<c:when test="${action=='add'}">
														<select id="drpDwnClass" name="academicYearClass.id"  style="width: 180px">
															  <option value=""></option>
														</select>
													</c:when>
													<c:when test="${(4==student.studentStatus.id || 6==student.studentStatus.id) && (customized==null ||customized==false)}">
														<select id="drpDwnClass" name="academicYearClass.id"  style="width: 180px">
															  <option value=""></option>
															  <c:forEach var="clazz" items="${classes}">
															  		<option value="${clazz.id}" <c:if test="${clazz.id == student.academicYearClass.id}">selected="selected"</c:if> >${clazz.displayName}</option>
															  </c:forEach>
														</select>
													
													</c:when>
													<c:otherwise>
														
														<input type="hidden" id="drpDwnClass" name="academicYearClass.id" value="${student.academicYearClass.id}"/>
														${student.academicYearClass.displayName}
													</c:otherwise>
												</c:choose>
											  </td>
										</tr>
										<tr>
					                      <td style="text-align: right;"><span class="mandatory_mark">*</span> First Name</td>
										  <td><input type="text" id="firstName" name="firstName" size="23" value="${student.firstName}" maxlength="126"/></td>
										  
										  
										  <td style="text-align: right;">Middle Name</td>
										  <td><input type="text" name="middleName" value="${student.middleName}" size="23" maxlength="126"/></td>
								   		  
										  <td style="text-align: right;"><span class="mandatory_mark">*</span> Last Name</td>
										  <td><input type="text" id="lastName" name="lastName" value="${student.lastName}" size="23" maxlength="126"/></td>
									</tr>
									<tr>
										<td style="text-align: right;"> Father's Name</td>
										<td><input type="text" name="guardianName" value="${student.fatherName}" size="23" maxlength="126"/></td>
										<td style="text-align: right;"> Mother's Name</td>
										<td colspan="3"><input type="text" name="motherName" value="${student.motherName}" size="23" maxlength="126"/></td>
										
									</tr>
									</table>
								</td>
							</tr>
							
						</table>
					</div>
			
					<br/>
					
					<div class="separatpr_line" style="width:100%">Student Information</div>
					<br/>
					
				<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					
					
					
					
					
					<tr>
	                      <td class="label" >DOB</td>
						  <td class="data_field_width"><input type="text" id="admissionDob" name="dobStr" value="<fmt:formatDate pattern="dd-MMM-yyyy" value="${student.dob}" />" readonly="readonly" size="26" /></td>
						  
						  </td>
						  <td class="label"><span class="mandatory_mark">*</span> Gender</td>
						  <td class="data_field_width" style="vertical-align:top"><input type="radio" name="gender" value="Male" <c:if test="${'male' == student.gender}">checked</c:if>/>
                    Male
                    <input type="radio" name="gender" value="Female" <c:if test="${'female' == student.gender}">checked</c:if>/>
                    Female </td>
				   		  
						  <td class="label">Category</td>
						  <td class="data_field_width">
						  	<select class="data_field_width" name="studentCategory.id" style="width: 220px">
							  <option value="1" <c:if test="${'1' == student.studentCategory.id}">selected="selected"</c:if> >General</option>
							  <option value="2" <c:if test="${'2' == student.studentCategory.id}">selected="selected"</c:if> >OBC</option>
							  <option value="3" <c:if test="${'3' == student.studentCategory.id}">selected="selected"</c:if> >SC</option>
							  <option value="4" <c:if test="${'4' == student.studentCategory.id}">selected="selected"</c:if> >ST</option>
							  
							</select>
						</td>
					</tr>
					<tr>
	                      <td class="label" > Phone 1 #</td>
						  <td class="data_field_width"><input type="text" value="${student.phone1}" name="phone1" size="29" maxlength="12" class="numeric"/></td>
						  
						  </td>
						  <td class="label">Phone 2 #</td>
						  <td class="data_field_width"><input type="text" value="${student.phone2}" name="phone2" size="29" maxlength="12" class="numeric"/></td>
				   		  
						  <td class="label">Phone 3 #</td>
						  <td class="data_field_width"><input type="text" value="${student.phone3}" name="phone3" size="29" maxlength="12" class="numeric"/></td>
					</tr>
					<tr>
						<td class="label"> Email</td>
						<td class="data_field_width"><input type="text" value="${student.emailId}" name="emailId" size="29" maxlength="126"/></td>
						<td class="label"> Enrollment #</td>
						<td colspan="3"><input type="text" value="${student.enrollmentNo}" name="enrollmentNo" size="29" maxlength="126"/></td>
					</tr>
						</table>
					
					<br/>
					<div class="separatpr_line" style="width:100%">Student Address</div>
					<br/>
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
						<td class="label">Line 1</td>
						<td  colspan="5" >
							<input type="hidden" name="address.id" value="${student.getLocalAddress().id}"/>
							<input id="address_line1" name="address.line1" value="${student.getLocalAddress().line1}" type="text" size="85" maxlength="510"/></td>
					</tr>
					<tr>
						<td class="label">Colony/Town</td>
						<td  colspan="5" ><input id="address_line2" name="address.line2" value="${student.getLocalAddress().line2}" type="text" size="85" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label">City</td>
						<td class="data_field_width"><input id="address_city" name="address.city" value="${student.getLocalAddress().city}" type="text" size="29" maxlength="126"/></td>
						<td class="label">Teh. </td>
						<td class="data_field_width"><input id="address_teh" name="address.teh" value="${student.getLocalAddress().teh}" type="text" size="29" maxlength="126"/></td>
						<td class="label">Dist.</td>
						<td class="data_field_width"><input id="address_district" name="address.district" value="${student.getLocalAddress().district}" type="text" size="29" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label">State</td>
						<td class="data_field_width">
							  <select id="state_id" name="address.state.id" class="data_field_width" style="width: 220px">
								 <c:forEach var="state" items="${states}">
								  		<option value="${state.id}" <c:if test="${state.id == student.getLocalAddress().state.id}">selected="selected"</c:if>>${state.displayName}</option>
								  </c:forEach>
							  </select>
						
						</td>
						<td class="label">Country</td>
						<td class="data_field_width">
							 <select id="country_id" name="address.country.id" class="data_field_width" style="width: 220px">
								  <c:forEach var="country" items="${countries}">
								  		<option value="${country.id}" <c:if test="${country.id == student.getLocalAddress().country.id}">selected="selected"</c:if>  >${country.name}</option>
								  </c:forEach>
							  </select>
						</td>
						<td class="label">Zip / Postal</td>
						<td class="data_field_width"><input type="text" size="29" name="address.zipCode" value="${student.address.zipCode}"/></td>
					</tr>
				</table>
					
					
<br/>

					<div class="separatpr_line" style="width:100%"></div>
					<br/>
					
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							
							<td class="label">
								<c:if test="${instituteSetting.admissionSettings.busStopMandatory==true}">
									<span class="mandatory_mark">*</span>
								</c:if>
								 Bus Stop
							</td>
							<td class="data_field_width">
								<c:choose>
									<c:when test="${ action=='add' ||  (4==student.studentStatus.id || 6==student.studentStatus.id) && (customized==null ||customized==false)}">
										<select class="data_field_width" id="busStopId" name="busStop.id" style="width: 220px">
											<option></option>
											<c:forEach var="busStop" items="${busStops}">
												<option value="${busStop.id}"  <c:if test="${busStop.id == student.busStop.id}">selected="selected"</c:if>  >${busStop.name}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<input type="hidden" id="busStopId" name="busStop.id" value="${student.busStop.id}"/>
										${student.busStop.name}
									</c:otherwise>
								</c:choose>
							</td>
							<td class="label"></td>
							<td class="data_field_width"></td>
							<td class="label"></td>
							<td class="data_field_width"></td>
						</tr>
						
					</table>
				
					<div class="separatpr_line" style="width:100%"></div>
					<br/>
                     <div style="text-align:center">     
                          <input type="button" value="Cancel" onClick="cancelAdmission(${activeAcademicYearId});" class="button"/>
                   		
                   		<c:choose>
                   			<c:when test="${action=='add'}">
                   				<input type="button" id="btnStudentSave" value="Admit Temporarily" onclick="saveStudent(${instituteSetting.admissionSettings.busStopMandatory},${instituteSetting.admissionSettings.formNoMandatory})" class="button"/>
                   			</c:when>
                   			<c:otherwise>
                   				<input type="button" id="btnStudentSave" value="Update Student Info" onclick="saveStudent(${instituteSetting.admissionSettings.busStopMandatory},${instituteSetting.admissionSettings.formNoMandatory})" class="button"/>
                   			</c:otherwise>
                   		</c:choose>
             				      		
                   			
                   		
                   		
                        </div>


            </div>

         
            </form>
			
		</div>
		
	</div>
</div>	
<jsp:include page="sams_footer.jsp"/>
<div id="popupBackground" class="popupBack" onclick="closeChangeStudentStatusPopup()"></div>
<div id="popupChangeStudentStatus" class="popup" style="height: 336px">
	<div class="popup_header">Change Student Status </div>	
	<div id="popupChangeStudentStatusContainer" class="working_area_spacer">
	</div>
</div>
<div id="popupChangeStudentClass" class="popup" style="height: 350px">
	<div class="popup_header">Change Student Class </div>	
	<div id="popupChangeStudentClassContainer" class="working_area_spacer">
	</div>
</div>
<div id="changeBusStopPopupBackground" class="popupBack" onclick="closeChangeBusStopPopup()"></div>
<div id="popupChangeBusStop" class="popup"></div>

<script type="text/javascript">

var course_url="<c:url value='/course' />";
var admission_base_url="<c:url value='/admission' />";
var student_activity_base_url="<c:url value='/studentactivity' />";
var admission_list_base_url="<c:url value='/admission/list' />";

var btnStudentSaveAction="${action}";
var ACTION_UPDATE="update";
var ACTION_ADD="add";
var image_base_url="<c:url value='/resources'/>";
var contextPath="<c:url value='/'/>";


$(function() {
	$( "#admissionDob" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	$("#popupChangeStudentStatus").draggable( { handle:"div.popup_header" } );
	$("#popupChangeStudentClass").draggable( { handle:"div.popup_header" } );
	$("#popupChangeBusStop").draggable( { handle:"div.popup_header" } );
	
	
	if(btnStudentSaveAction==ACTION_UPDATE){
		$('#std_image').click(function() {
		    $('input[type=file]').click();
		});	
	}
	
	
	
	$('#fileupload').fileupload({
        dataType: 'json',
        formData:[{name:'id',value:'${student.id}'},{name:'studentID',value:'${student.studentId}'}],
        done: function (e, data) {
          
        	$("#studentImage").attr('src',image_base_url+"/studentpics/${student.studentId}.jpeg?ver="+Math.random());
        }
    });	
});




</script>
        	 
       