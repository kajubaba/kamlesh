<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
			        
<div>			      
				   <form id="EnquiryActivityForm" name="EnquiryActivityForm">
				  	 	<input type="hidden" id="followupActivity" name="followupActivity" />
			        	<input type="hidden" id="newStatusId" name="newStatusId" />
			        	<input type="hidden" id="newOwnerId" name="newOwnerId" />
			        	<input type="hidden" id="newAssigneeId" name="newAssigneeId" />
			        	<input type="hidden" id="comments" name="comments" />
			       	<table  id="enq_table" class="table table-striped table-bordered table-hover">
				    
			          <thead>
			          <tr>
			            <th><input type="checkbox" class="grid_item" onclick="toggleChecked(this.checked)">  </th>
			            <th>Student Name</th>
			            <th>Gender</th>
			            <th>Class</th>
			            <th>City</th>
			            <th>Phone #</th>
			            <th>Owner</th>
						<th>Status</th>
					
					 <security:authorize access="hasRole('ROLE_ENQUIRY_VIEW_ACTIVITY_LOG')">
				    	<td></td>
			        </security:authorize>
			          </tr>
			         </thead> 
			        <tbody>
			         <c:forEach var="enquiry" items="${enquiries}" varStatus="rowCounter">
          			<tr>
			          	<td><input type="checkbox" name="enquiryIds"  value="${enquiry.id}" class="checkbox"> </td>
			            <td><a href="<c:url value='/enquiry/view?enquiryId=${enquiry.id}' />">${enquiry.studentFirstName} ${enquiry.studentLastName}</a></td>
			            <td>${enquiry.studentGender}</td>
			            <td>${enquiry.academicYearClass.displayName}</td>
			            <td>${enquiry.address.city} </td>
			            <td>${enquiry.studentPhone1}</td>
			            <td>${enquiry.owner.firstName} ${enquiry.owner.lastName}</td>
						<td>${enquiry.enquiryStatus.name}</td>
					 <security:authorize access="hasRole('ROLE_ENQUIRY_VIEW_ACTIVITY_LOG')">
				    	<td> <a href="<c:url value='/enquiry/activity/log?enquiryId=${enquiry.id}' />">Log</a> </td>
			        </security:authorize>
						
						
			          </tr>
			         </c:forEach>
			         </tbody>
			         
			        </table>
			         </form>
			       
</div>
<div id="enq_activity_popup" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
</div> 			       
