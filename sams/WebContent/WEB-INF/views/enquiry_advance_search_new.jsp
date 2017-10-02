 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div id="enq_advance_search_popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >


	<div class="modal-dialog modal-lg" role="document" >
	<div class="modal-content">
		    <div class="modal-header">
	        		<h3 class="modal-title"> <span class="glyphicon glyphicon-plus-sign"></span> 
	        			Enquiry Advance Search
	        		</h3>
	      	</div>
	      	<div class="modal-body" style="max-height: calc(100vh - 210px);overflow-y: auto;">
	      		<form id="advSearchFrm">
					<input type="hidden" id="academicYearId" value="${academicYearId}">
	      		<h4>Enquiry Information</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">University</td>
							<td>
								<select id="drpDwnAffiliationAuth" name="affiliationAuthoritId" class="form-control" onchange="findCourses()">
							 	<option value=""></option>
								  <c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
								  		<option value="${affiliationAuth.id}">${affiliationAuth.displayName}</option>
								  </c:forEach>
								</select>
							
							</td>
							<td class="profile-label col-md-1">Class</td>
							<td>
								<select id="drpDwnCourse" name="courseId" class="form-control" onchange="findActiveClasses()">
								  <option value=""></option>
								</select>
							</td>
							<td class="profile-label col-md-1">Yr/Sem</td>
							<td>
								<select id="drpDwnClass" name="academicYearClassId" class="form-control">
							  		<option value=""></option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="profile-label col-md-1">Status</td>
							<td>
								<select class="form-control" name="enquiryStatus.id">
		        						<option value="-1" selected="selected"></option>
			  							<c:forEach var="enqStatus" items="${enqStatusList}">
			  								<option value="${enqStatus.id}">${enqStatus.name}</option>
			  							</c:forEach>
		       					</select>
							
							</td>
							<td class="profile-label col-md-1">Owner</td>
							<td>
								<select class="form-control" name="owner.id">
        						<option value="-1" selected="selected"></option>
        						<c:forEach var="user" items="${users}">
								  		<option value="${user.id}">${user.firstName} ${user.lastName}</option>
							 	</c:forEach>
        					</select>
							</td>
							<td class="profile-label col-md-1">Assignee</td>
							<td>
								<select class="form-control" name="assignee.id">
	        						<option value="-1" selected="selected"></option>
	        						<c:forEach var="user" items="${users}">
									  		<option value="${user.id}">${user.firstName} ${user.lastName}</option>
								 </c:forEach>
	        					</select>
							</td>
						</tr>
						<c:if test="${instituteSetting.enquirySettings.enableRegistered==true}">
							<tr>
								<td class="profile-label col-md-1">Registration No</td>
								<td>
									<input type="text" class="form-control" name="registrationNo" maxlength="10">
								
								</td>
								<td class="profile-label col-md-1">Registered</td>
								<td>
									<select class="form-control" name="registered">
	        							<option value="" selected="selected"></option>
	        							<option value="true">Yes</option>
	        							<option value="false">No</option>
	        						</select>
	        					</select>
								</td>
								<td colspan="2"></td>
								
							</tr>
						</c:if>	
	      			</tbody>
	      		</table>
	      		<h4>Student Information</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">Student Name</td>
							<td>
								<input type="text" class="form-control" name="studentFullName">
							
							</td>
							<td class="profile-label col-md-1">Gender</td>
							<td>
								<input type="radio" name="studentGender" value="Male" /> Male
	                    		<input type="radio" name="studentGender" value="Female"/> Female
							</td>
							<td class="profile-label col-md-1">Category</td>
							<td>
								<select class="form-control" name="studentCategory.id">
								  <option value="-1" selected="selected"></option>
								  <option value="1">General</option>
								  <option value="2">OBC</option>
								  <option value="3">SC</option>
								  <option value="4">ST</option>
								  
								</select>
							</td>
						</tr>
						<tr>
							<td class="profile-label col-md-1">Phone #1</td>
							<td>
								<input type="text" name="studentPhone1" maxlength="12" class="form-control numeric"/>
							
							</td>
							<td class="profile-label col-md-1">Phone #2</td>
							<td>
								<input type="text" name="studentPhone2" maxlength="12" class="form-control numeric"/>
							</td>
							<td class="profile-label col-md-1">Phone #3</td>
							<td>
								<input type="text" name="studentPhone3" maxlength="12" class="form-control numeric"/>
							</td>
						</tr>
						<tr>
							<td class="profile-label col-md-1">Colony/Town</td>
							<td>
								<input name="address.line2" type="text" class="form-control" maxlength="126"/>
							
							</td>
							<td class="profile-label col-md-1">City</td>
							<td>
								<input name="address.city" type="text"  class="form-control" maxlength="126"/>
							</td>
							<td class="profile-label col-md-1">Teh.</td>
							<td>
								<input name="address.teh" type="text"  class="form-control" maxlength="126"/>
							</td>
						</tr>
						<tr>
							<td class="profile-label col-md-1">Dist.</td>
							<td>
								<input name="address.district" type="text" class="form-control" maxlength="126"/>
							
							</td>
							<td class="profile-label col-md-1">State</td>
							<td>
								<select class="form-control" name="state.id">
								 <option value="-1" selected="selected"></option>
								 <option value="1">MP</option>
								 <option value="2">Gujrat</option>
								
							  </select>
							</td>
							<td class="profile-label col-md-1">Country</td>
							<td>
								<select name="country.id" class="form-control">
								 <option value="-1" selected="selected"></option>
								  <option value="1">India</option>
								
							  </select>
							</td>
						</tr>
	      			</tbody>
	      		</table>	
	      		<c:if test="${instituteSetting.enquirySettings.enablePreviousClass==true}">
	      		<h4>Previous Class</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">Class Name</td>
							<td>
								<input type="text" name="studentPrevClass.className" class="form-control" maxlength="126"/>
							
							</td>
							<td class="profile-label col-md-1">School/College</td>
							<td>
								<input type="text" name="studentPrevClass.instituteName" class="form-control" maxlength="126"/>
							</td>
							<td class="profile-label col-md-1">City/Town</td>
							<td>
								<input type="text" name="studentPrevClass.city" class="form-control" maxlength="126"/>
								  
								
							</td>
						</tr>
						<tr>
							<td class="profile-label col-md-1">Univ/Board</td>
							<td>
								<input name="studentPrevClass.board" type="text" class="form-control" maxlength="126"/>
							
							</td>
							<td class="profile-label col-md-1">Roll No #</td>
							<td>
								<input name="studentPrevClass.rollNo" type="text" class="form-control" maxlength="20"/> 
							</td>
							<td class="profile-label col-md-1">Status</td>
							<td>
								<select class="form-control" name="studentPrevClass.studentStatus">
									<option value="" selected="selected"></option>
									<option value="pursuing">Pursuing</option>
									<option value="result awaited">Result Awaited</option>
									<option value="result declared">Result Declared </option>
									<option value="fail">Fail </option>
									
								</select>
							</td>
						</tr>
					</tbody>
				</table>	
				</c:if>
				<h4>Activity</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">Activity Type</td>
							<td>
								<select name="activityType" class="form-control">
							 		<option value="" selected="selected"></option>
							 		<option value="change_assignee">Change enquiry assignee</option>
							 		<option value="change_owner">Change enquiry owner</option>
							 		<option value="change_status">Change enquiry status</option>
							 		<option value="followup">Enquiry Followup</option>
							 		
							 	</select>
							
							</td>
							<td class="profile-label col-md-1">From Date</td>
							<td>
								<input type="text" id="activityFromDateStr" name="activityFromDateStr" class="form-control" readonly="readonly"/>
							</td>
							<td class="profile-label col-md-1">To Date</td>
							<td>
								<input type="text" id="activityToDateStr" name="activityToDateStr" class="form-control" readonly="readonly"/>
								  
								
							</td>
						</tr>
					</tbody>
				</table>	
				<c:if test="${instituteSetting.enquirySettings.enableInternalExam==true}">
				<h4>Internal Exam</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">Roll No#</td>
							<td colspan="3">
								<input type="text" class="form-control" name="studentInternalExamInfo.rollNo"/>
							
							</td>
							
						</tr>
					</tbody>
				</table>		
				</c:if>
				<c:if test="${instituteSetting.enquirySettings.enableCompetitiveExam==true}">	
				<h4>Competitive Exam</h4>
	      		<table class="table-responsive table tbl-profile">
	      			<tbody>
	      				<tr>
							<td class="profile-label col-md-1">Roll No#</td>
							<td colspan="3">
								<input type="text" class="form-control" name="studentCompetitiveExamInfo.rollNo"/>
							
							</td>
							
						</tr>
					</tbody>
				</table>		
				</c:if>
	      	</form>	
	      	</div>
	      	<div class="modal-footer">
		  		<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-plus-sign" ></span> Cancel</button>
		  		<button type="button" class="btn btn-primary"  onclick="search(${academicYearId});"><span class="glyphicon glyphicon-plus-sign" ></span> Search</button>
	      	</div>
	</div>  	
	</div> 

</div>

   		