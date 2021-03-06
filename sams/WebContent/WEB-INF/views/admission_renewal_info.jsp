<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	<form id="studentForm">
			
          
            
            		<input type="hidden" id="studentId" name="id" value="${student.id}">
            		<input type="hidden" id="academicYearId" name="id" value="${activeAcademicYearId}">
            		<input type="hidden" name="studentId" value="${student.studentId}">
            		
            		
            	<div class="form_container">
					<div class="separatpr_line" style="width:100%">Current Class</div>
					<br/>
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
			                      <td class="label">University : </td>
								  <td class="data_field_width"> ${student.academicYearClass.courseYear.course.affiliatedTo.displayName}</td>
								  <td class="label">Class : </td>
								  <td class="data_field_width">${student.academicYearClass.courseYear.course.displayName}</td>
						   		  
								  <td class="label">Yr/Sem : </td>
								  <td class="data_field_width">${student.academicYearClass.displayName}</td>
								  <td>&nbsp;   </td>
							</tr>
						</table>    				
    				<div class="separatpr_line" style="width:100%">Renewal Class</div>
					<br/>
						
						<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
			                      <td class="label"><span class="mandatory_mark">*</span> University</td>
								  <td class="data_field_width">
								  	<select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" class="data_field_width" onchange="findCourses()" style="height: 22px">
									 <option value=""></option>
									  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
									  		<option value="${affiliationAuth.id}" >${affiliationAuth.displayName}</option>
									  </c:forEach>
									</select>
								  </td>
								  <td class="label"><span class="mandatory_mark">*</span> Class</td>
								  <td class="data_field_width">
								  	<select id="drpDwnCourse" name="drpDwnCourse" class="data_field_width" onchange="findActiveClasses()" style="height: 22px">
									  <option value=""></option>
									  
									</select>
								  </td>
						   		  
								  <td class="label"><span class="mandatory_mark">*</span> Yr/Sem</td>
								  <td class="data_field_width">
								  <select id="drpDwnClass" name="academicYearClass.id"  class="data_field_width" style="height: 22px" >
									  <option value=""></option>
									 
									</select>
								  </td>
								  <td>&nbsp;   </td>
							</tr>
						</table>
					
			
					<br/>
					
					<div class="separatpr_line" style="width:100%">Student Information</div>
					<br/>
					
				<table width="100%" cellpadding="0" cellspacing="10"  border="0">
					<tr>
	                      <td class="label" ><span class="mandatory_mark">*</span> First Name</td>
						  <td class="data_field_width"><input type="text" id="firstName" name="firstName" size="29" value="${student.firstName}" maxlength="126"/></td>
						  
						  
						  <td class="label">Middle Name</td>
						  <td class="data_field_width"><input type="text" name="middleName" value="${student.middleName}" size="29" maxlength="126"/></td>
				   		  
						  <td class="label"><span class="mandatory_mark">*</span> Last Name</td>
						  <td class="data_field_width"><input type="text" id="lastName" name="lastName" value="${student.lastName}" size="29" maxlength="126"/></td>
					</tr>
					
					<tr>
						<td class="label"> S/O D/O</td>
						<td class="data_field_width"><input type="text" name="guardianName" value="${student.fatherName}" size="29" maxlength="126"/></td>
						<td class="label"> Mother's Name</td>
						<td colspan="3" class="data_field_width"><input type="text" name="motherName" value="${student.motherName}" size="29" maxlength="126"/></td>
					</tr>
					
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
						  	<select class="data_field_width" name="studentCategory.id">
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
						<td  colspan="5" ><input id="address_line1" name="address.line1" value="${student.getLocalAddress().line1}" type="text" size="85" maxlength="510"/></td>
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
							  <select id="state_id" name="address.state.id" class="data_field_width">
								 <c:forEach var="state" items="${states}">
								  		<option value="${state.id}" <c:if test="${state.id == student.getLocalAddress().state.id}">selected="selected"</c:if>>${state.displayName}</option>
								  </c:forEach>
							  </select>
						
						</td>
						<td class="label">Country</td>
						<td class="data_field_width">
							 <select id="country_id" name="address.country.id" class="data_field_width">
								  <c:forEach var="country" items="${countries}">
								  		<option value="${country.id}" <c:if test="${country.id == student.getLocalAddress().country.id}">selected="selected"</c:if>  >${country.name}</option>
								  </c:forEach>
							  </select>
						</td>
						<td class="label">Zip / Postal</td>
						<td class="data_field_width"><input type="text" size="29" name="address.zipCode" value="${student.getLocalAddress().zipCode}"/></td>
					</tr>
				</table>
					
					
<br/>

					<div class="separatpr_line" style="width:100%"></div>
					<br/>
					
					<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							<td class="label">Bus</td>
							<td class="data_field_width" style="text-align: left;"> <input type="checkbox"> </td>
							<td>
								<c:if test="${instituteSetting.admissionSettings.busStopMandatory==true}">
									<span class="mandatory_mark">*</span>
								</c:if>
							Bus Stop</td>
							<td>
								<select class="data_field_width" id="busStopId" style="height: 22px;" name="busStop.id">
									<option></option>
									<c:forEach var="busStop" items="${busStops}">
										<option value="${busStop.id}"  <c:if test="${busStop.id == student.busStop.id}">selected="selected"</c:if>  >${busStop.name}</option>
									</c:forEach>
								</select>
							
							</td>
							<td class="label"><c:if test="${instituteSetting.admissionSettings.formNoMandatory==true}">
										<span class="mandatory_mark">*</span> 
									</c:if> Form No#</td>
							<td class="data_field_width"> <input type="text" id="admissionFormNo" name="admissionFormNo" />
							 </td>
						</tr>
						
					</table>
				
					<div class="separatpr_line" style="width:100%"></div>
					<br/>
                     <div style="text-align:center">     
                          <input type="button" value="Cancel" onClick="cancelRenewal(${activeAcademicYearId});" class="button"/>
                   		  <security:authorize access="hasRole('ROLE_RENEW_ADMISSION')">
                   					<input type="button" id="btnTempRenew" value="  Temporary Renew " onclick="confirmAddmissionRenewal(${instituteSetting.admissionSettings.busStopMandatory},${instituteSetting.admissionSettings.formNoMandatory});" class="button"/>
                   			</security:authorize>	
                   			<security:authorize access="hasRole('ROLE_FEE') and hasRole('ROLE_RENEW_ADMISSION')">
                   				<c:set var="flag" value="true"/>
                   				<input type="button" id="btnTempRenewNPay" value="  Temporary Renew & Pay Fee  " onclick="confirmAddmissionRenewalAndPayFee(${instituteSetting.admissionSettings.busStopMandatory},${instituteSetting.admissionSettings.formNoMandatory});" class="button"/>
                   			</security:authorize>
                   		
                   			
                        </div>
            </div>

         
            </form>


        	 
       