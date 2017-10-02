<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
    		<div class="grid_info">
	             <c:if test="${ fn:length(students)!=0 }">
		             <span style=" float: left;">
		             	<input type="button" value="Promote" class="button" onclick="promoteStudents()">
				     </span>
	             </c:if>
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		<form id="promoteIdForm">
          	 <table id="admitted_students_table" class="grid grid_color_theme_border">
		          <thead>
			          <tr class="grid_heading grid_heading_theme">
				        <th class="grid_item">
				          <input type="checkbox" onchange="toggleAdmissionChecked(this.checked)"/>
				        </th>
			            <th class="grid_item">Student ID</th>
			            <th class="grid_item">Student Name</th>
			             <th class="grid_item">Father's Name</th>
			            <th class="grid_item">Enrollment #</th>
			            <th class="grid_item">Gender</th>
			            <th class="grid_item">City</th>
			          </tr>
		            </thead>
		          <tbody>
		          
		           <c:forEach var="student" items="${students}" varStatus="rowCounter">
			         	
			          	<tr class="grid_main_row">
				           	<td class="grid_item">
				            	<input type="checkbox" class="admissionCheckbox" name="studentIds" value="${student.id}"/>
				            </td>
			          	<td class="grid_item">
			          			${student.studentId}
			          	</td>
			            <td class="grid_item">${student.firstName} ${student.lastName}</td>
			            <td class="grid_item">${student.fatherName}</td>
			            <td class="grid_item">${student.enrollmentNo}</td>
			            
			            <td class="grid_item">${student.gender}</td>
			            <td class="grid_item">${student.address.city}</td>
		          </c:forEach>
		          </tbody>
		     </table>
		     </form>
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(students)!=0 }">
			         		Displaying 1-${fn:length(students)} of ${fn:length(students)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>					





