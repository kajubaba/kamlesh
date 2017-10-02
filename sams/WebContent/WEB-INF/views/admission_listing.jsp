<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
    		
	    	<div style="width: 100px;float: left;"  class="action-button" onclick="$('#popupChangeStudentStatus, #popupBackground').show();$('#popupChangeStudentStatus').draggable( { handle:'div.popup_header' } );$('#sttscmts').val('')">Change Status</div>			          			
			
			<br/>
    		
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
    		<form id="changeStatusForm">
    			<input type="hidden" id="newStatusId" name="newStatusId"/>
				 <input type="hidden" id="changeStatusComments" name="changeStatusComments"/>
	          	 <table id="admitted_students_table" class="grid grid_color_theme_border">
			          <thead>
				          <tr class="grid_heading grid_heading_theme">
				            
				            	<th class="grid_item" style="width: 25px;"><input type="checkbox" class="grid_item" onclick="toggleChecked(this.checked)">  </th>
				            	
				            <th class="grid_item">ID</th>
				            <th class="grid_item">Student Name</th>
				            <th class="grid_item">Father's Name</th>
				            <th class="grid_item">Class</th>
				            <th class="grid_item">Status</th>
				            <th class="grid_item">City</th>
				          </tr>
			            </thead>
			          <tbody>
				           
				           		
				           <c:forEach var="student" items="${students}" varStatus="rowCounter">
						      <tr class="grid_main_row">
						          	
				            			<td class="grid_item" style="width: 25px;">
				            				<input type="checkbox" name="studclasids" class="checkbox"  value="${student.id}-${student.getActiveClassHistory(academicYearId).academicYearClass.id}">
				            			</td>
				            			
						          	<td class="grid_item">
					          			<a href="<c:url value='/admission/edit/student/${student.studentId}' />">${student.studentId}</a>
						          	</td>
						            <td class="grid_item">${student.firstName} ${student.lastName}</td>
						            <td class="grid_item">${student.fatherName}</td>
						            <td class="grid_item">${student.getActiveClassHistory(academicYearId).academicYearClass.displayName}</td>
						            <td class="grid_item">${student.getActiveClassHistory(academicYearId).studentStatus.name}</td>
						            <td class="grid_item">${student.getLocalAddress().city}</td>
						     </tr>       
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





