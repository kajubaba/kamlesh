<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Manage Academic Year '${academicYear.name}' Courses</span></div>
<div class="working_area_spacer">
       
       
       <form id="searchAYCourseForm">
       	   <input type="hidden" name="academicYearId" value="${academicYearId}">	
	       
	       <table>
	       		<tr>
	       			<td>Affiliation Authority</td>
	       			<td>
	       				
	       				<select class="data_field_width" name="affiliationAuthorityId">
	                   		<option value=""></option>
			                   	<c:forEach var="affiliationAuthority" items="${affiliationAuthorities}">
			                   		<option value="${affiliationAuthority.id}"  <c:if test="${affiliationAuthority.id==course.affiliatedTo.id}">selected</c:if>">${affiliationAuthority.displayName}</option>
			                    </c:forEach>
	             		</select>
	            		
	       			</td>
	       			<td>Type</td>
	       			<td>
	       				<select name="courseType" class="data_field_width">
	       					<option value="1">Individual</option>
	       					<option value="2">Group</option>
	       				</select>
	       			</td>
	       			<td> <input type="button" class="button" value="Search" onclick="ayGetActiveCourses()"/> </td>
	       			<td><span style="text-align: right;"><a href="javascript:void(0)" onclick="getReminingCourseListPopup()">Add Courses</a></span></td>
	       		</tr>
	       		<tr>
	       			
	       		</tr>
	       </table>
	       
      </form>
      
      <div id="ayCourseList"></div>
      
</div>
      