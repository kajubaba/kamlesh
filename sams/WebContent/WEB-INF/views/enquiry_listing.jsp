<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
			        <div class="grid_info">
				         <span style=" float: right;">
				         <c:choose>
				         	<c:when test="${totalEnquiries!=0}">
				         		Displaying 1-${totalEnquiries} of ${totalEnquiries}
				         	</c:when>
				         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
				         </c:choose> 
				         </span>
			        </div>
			        
			      
				   <form id="EnquiryActivityForm" name="EnquiryActivityForm">
				  	 	<input type="hidden" id="followupActivity" name="followupActivity" />
			        	<input type="hidden" id="newStatusId" name="newStatusId" />
			        	<input type="hidden" id="newOwnerId" name="newOwnerId" />
			        	<input type="hidden" id="newAssigneeId" name="newAssigneeId" />
			        	<input type="hidden" id="comments" name="comments" />
			       	
				    <table id="enq_table" class="grid grid_color_theme_border">
			          <thead>
			          <tr class="grid_heading grid_heading_theme">
			            <th class="grid_item" style="width: 25px;"><input type="checkbox" class="grid_item" onclick="toggleChecked(this.checked)">  </th>
			            <th class="grid_item">Student Name</th>
			            <th class="grid_item">Gender</th>
			            <th class="grid_item">Class</th>
			            <th class="grid_item">City</th>
			            <th class="grid_item">Phone #</th>
			            <th class="grid_item">Owner</th>
						<th class="grid_item">Status</th>
					
					 <security:authorize access="hasRole('ROLE_ENQUIRY_VIEW_ACTIVITY_LOG')">
				    	<td class="grid_item"></td>
			        </security:authorize>
			          </tr>
			         </thead> 
			        <tbody>
			         <c:forEach var="enquiry" items="${enquiries}" varStatus="rowCounter">
          			<tr class="grid_main_row">
			          	<td class="grid_item" style="width: 25px;"><input type="checkbox" name="enquiryIds"  value="${enquiry.id}" class="checkbox"> </td>
			            <td class="grid_item"><a href="<c:url value='/enquiry/view?enquiryId=${enquiry.id}' />">${enquiry.studentFirstName} ${enquiry.studentLastName}</a></td>
			            <td class="grid_item">${enquiry.studentGender}</td>
			            <td class="grid_item">${enquiry.academicYearClass.displayName}</td>
			            <td class="grid_item">${enquiry.address.city} </td>
			            <td class="grid_item">${enquiry.studentPhone1}</td>
			            <td class="grid_item">${enquiry.owner.firstName} ${enquiry.owner.lastName}</td>
						<td class="grid_item">${enquiry.enquiryStatus.name}</td>
					 <security:authorize access="hasRole('ROLE_ENQUIRY_VIEW_ACTIVITY_LOG')">
				    	<td class="grid_item"> <a href="<c:url value='/enquiry/activity/log?enquiryId=${enquiry.id}' />">Log</a> </td>
			        </security:authorize>
						
						
			          </tr>
			         </c:forEach>
			         </tbody>
			         
			        </table>
			         </form>
			        <br/>
			        <div class="grid_info">
			        	<span style=" float: right;">
			        		<c:choose>
					         	<c:when test="${totalEnquiries!=0}">
					         		Displaying 1-${totalEnquiries} of ${totalEnquiries}
					         	</c:when>
					         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
				        	 </c:choose>
				         </span>
				       </div>
