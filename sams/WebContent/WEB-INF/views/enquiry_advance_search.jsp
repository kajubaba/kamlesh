 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div onclick="toggleAdvanceSearch()" style="cursor: pointer;margin-bottom: 5px;color: blue;">Advance Search</div>
<form id="advSearchFrm">
<input type="hidden" id="academicYearId" value="${academicYearId}">
 <div id="advanceEnqSearch" style="background-color:#FCFCFC;border:1px solid #DDDDDD;display: none;">
       		
       		
       		
       		<div style="width:100%;font-weight: bold;">Enquiry Info</div><br/>
        	<div>
        		<table width="100%" cellpadding="0" cellspacing="10"  border="0">
        			<tr>
	                     <td class="label" > University</td>
						  <td class="data_field_width">
						  	<select id="drpDwnAffiliationAuth" name="affiliationAuthoritId" class="data_field_width" onchange="findCourses()" style="height: 22px">
							 	<option value=""></option>
							  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
							  		<option value="${affiliationAuth.id}">${affiliationAuth.displayName}</option>
							  </c:forEach>
							</select>
						  </td>
						  <td class="label"> Class</td>
						  <td class="data_field_width">
						  	<select id="drpDwnCourse" name="courseId" class="data_field_width" onchange="findActiveClasses()" style="height: 22px">
							  <option value=""></option>
							</select>
						  </td>
				   		  
						  <td class="label"> Yr/Sem</td>
						  <td class="data_field_width">
						  <select id="drpDwnClass" name="academicYearClassId" class="data_field_width" style="height: 22px">
							  
							  <option value=""></option>
							 
							</select>
						  </td>
						  
					</tr>
        			<tr>
        				<td class="label">Status</td>
        				<td class="data_field_width"> 
        					<select class="data_field_width" name="enquiryStatus.id">
        						<option value="-1" selected="selected"></option>
	  							<c:forEach var="enqStatus" items="${enqStatusList}">
	  								<option value="${enqStatus.id}">${enqStatus.name}</option>
	  							</c:forEach>
d        					</select>
        				 </td>
        				 <td class="label">Owner</td>
        				<td class="data_field_width"> 
        					<select class="data_field_width" name="owner.id">
        						<option value="-1" selected="selected"></option>
        						<c:forEach var="user" items="${users}">
								  		<option value="${user.id}">${user.firstName} ${user.lastName}</option>
							 	</c:forEach>
        					</select>
        				 </td>
        				<td class="label">Assignee</td>
        				<td class="data_field_width"> 
        					<select class="data_field_width" name="assignee.id">
        						<option value="-1" selected="selected"></option>
        						<c:forEach var="user" items="${users}">
								  		<option value="${user.id}">${user.firstName} ${user.lastName}</option>
							 </c:forEach>
        					</select>
        				</td>
        			</tr>
        			
        			<%-- 
        			
        			
					
					--%>
        				<c:if test="${instituteSetting.enquirySettings.enableRegistered==true}">
        				<tr>
        					<td class="label">Registration No</td>
        					<td> <input type="text" size="31" name="registrationNo" maxlength="10"> </td>
        					<td class="label">Registered ?</td>
        					<td class="data_field_width"> 
        						<select class="data_field_width" name="registered">
        							<option value="" selected="selected"></option>
        							<option value="true">Yes</option>
        							<option value="false">No</option>
        						</select>
        					 </td>
        					 <td colspan="2"></td>
        				</tr>
        				</c:if>
        			</table>
        		</div>
       		<div style="width:100%;font-weight: bold;">Student Information</div><br/>
       		<div>
       			<table width="100%" cellpadding="0" cellspacing="10"  border="0">
	       			<tr>
	       				<td class="label">Student Name</td>
	       				<td> <input type="text" size="31" name="studentFullName"> </td>
	       				<td class="label">Gender</td>
	       				<td style="vertical-align:top">
	       					<input type="radio" name="studentGender" value="Male" /> Male
	                    	<input type="radio" name="studentGender" value="Female"/> Female
	                    </td>	
	       				<td class="label">Category</td>
	       				<td> 
	       				
	       					<select class="data_field_width" name="studentCategory.id">
								  <option value="-1" selected="selected"></option>
								  <option value="1">General</option>
								  <option value="2">OBC</option>
								  <option value="3">SC</option>
								  <option value="4">ST</option>
								  
								</select>
	       				
	       				 </td>
	       			</tr>
	       			<tr>
		                <td class="label" >Phone 1 #</td>
						<td class="data_field_width"><input type="text" name="studentPhone1" size="31" maxlength="12" class="numeric"/></td>
						<td class="label">Phone 2 #</td>
						<td class="data_field_width"><input type="text" name="studentPhone2" size="31" maxlength="12" class="numeric"/></td>
              		     <td class="label">Phone 3 #</td>
						<td class="data_field_width"><input type="text" name="studentPhone3" size="31" maxlength="12" class="numeric"/></td>
					</tr>
	       			<tr>
						<td class="label">Colony/Town</td>
						<td class="data_field_width"><input name="address.line2" type="text" size="31" maxlength="126"/></td>
						<td class="label">City</td>
						<td class="data_field_width"><input name="address.city" type="text" size="31" maxlength="126"/></td>
						<td class="label">Teh.</td>
						<td class="data_field_width"><input name="address.teh" type="text" size="31" maxlength="126"/></td>
					</tr>
					<tr>
						<td class="label">Dist.</td>
						<td class="data_field_width"><input name="address.district" type="text" size="31" maxlength="126"/></td>
						<td class="label">State</td>
						<td class="data_field_width">
							<select class="data_field_width" name="state.id">
								 <option value="-1" selected="selected"></option>
								 <option value="1">MP</option>
								 <option value="2">Gujrat</option>
								
							  </select>
						</td>
						<td class="label">Country</td>
						<td class="data_field_width">
							<select name="country.id" class="data_field_width">
								 <option value="-1" selected="selected"></option>
								  <option value="1">India</option>
								
							  </select>
						</td>
					</tr>
	       		</table>
       		</div>
      		<c:if test="${instituteSetting.enquirySettings.enablePreviousClass==true}">
      		<div style="width:100%;font-weight: bold;">Previous Class</div><br/>
       		<div>
	       		<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							 <td class="label">Class Name</td>
							 <td class="data_field_width" ><input type="text" name="studentPrevClass.className" size="31" maxlength="126"/></td>
							 <td class="label">School/College</td>
							 <td class="data_field_width"><input type="text" name="studentPrevClass.instituteName" size="31" maxlength="126"/></td>
							 <td class="label">City/Town</td>
							 <td class="data_field_width"><input type="text" name="studentPrevClass.city" size="31" maxlength="126"/></td>
							 
						</tr>
						<tr>
							<td class="label">Univ/Board</td>
							<td class="data_field_width"> <input name="studentPrevClass.board" type="text" size="31" maxlength="126"/> </td>
							<td class="label">Roll No #</td>
							<td class="data_field_width"> <input name="studentPrevClass.rollNo" type="text" size="31" maxlength="20"/> </td>
							<td class="label">Status</td>
							<td class="data_field_width">
								<select class="data_field_width" name="studentPrevClass.studentStatus">
									<option value="" selected="selected"></option>
									<option value="pursuing">Pursuing</option>
									<option value="result awaited">Result Awaited</option>
									<option value="result declared">Result Declared </option>
									<option value="fail">Fail </option>
									
								</select>
							
							</td>
						</tr>
						
					</table>
				</div>
       		</c:if>
       		<div style="width:100%;font-weight: bold;">Action Taken</div><br/>
       		
       		<div>
	       		<table width="100%" cellpadding="0" cellspacing="10"  border="0">
						<tr>
							 <td class="label">Activity Type</td>
							 <td class="data_field_width" >
							 	<select name="activityType">
							 		<option value="" selected="selected"></option>
							 		<option value="change_assignee">Change enquiry assignee</option>
							 		<option value="change_owner">Change enquiry owner</option>
							 		<option value="change_status">Change enquiry status</option>
							 		<option value="followup">Enquiry Followup</option>
							 		
							 	</select>
							 </td>
							 <td class="label">From Date</td>
							 <td class="data_field_width" ><input type="text" id="activityFromDateStr" name="activityFromDateStr" size="28" readonly="readonly"/></td>
							 <td class="label">To Date</td>
							 <td class="data_field_width" ><input type="text" id="activityToDateStr" name="activityToDateStr" size="28" readonly="readonly"/></td>
						</tr>			
					</table>
				</div>
       		<c:if test="${instituteSetting.enquirySettings.enableInternalExam==true}">
	       		<div style="width:100%;font-weight: bold;">Internal Exam</div><br/>
	       		<div>
		       		<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
								 <td class="label">Roll No#</td>
								 <td class="data_field_width" ><input type="text" size="28" name="studentInternalExamInfo.rollNo"/></td>
								 <td class="label">&nbsp;</td>
								 <td class="data_field_width" >&nbsp;</td>
								 <td class="label">&nbsp;</td>
								 <td class="data_field_width" >&nbsp;</td>
							</tr>			
						</table>
					</div>
			</c:if>
			<c:if test="${instituteSetting.enquirySettings.enableCompetitiveExam==true}">		
	       		<div style="width:100%;font-weight: bold;">Competitive Exam</div><br/>
	       		<div>
		       		<table width="100%" cellpadding="0" cellspacing="10"  border="0">
							<tr>
								 <td class="label">Roll No#</td>
								 <td class="data_field_width" ><input type="text" size="28" name="studentCompetitiveExamInfo.rollNo"/></td>
								 <td class="label">&nbsp;</td>
								 <td class="data_field_width" >&nbsp;</td>
								 <td class="label">&nbsp;</td>
								 <td class="data_field_width" >&nbsp;</td>
							</tr>			
						</table>
					</div>
			</c:if>		
       		<div style="text-align: center;;margin-top: 10px;margin-bottom: 15px"><input type="button" value="Advance Search" class="button" onclick="search(${academicYearId});"></div>
       		
       		</div>
</form>       		