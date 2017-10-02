<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<div class="working_area_spacer">
	 
	 
	 <c:choose>
	 	<c:when test="${null != courses && fn:length(courses) > 0 }">
	 		<form id="reminingCourseForm">
				 <input type="hidden" name="academicYearId" value="${academicYearId}">
				 <input type="hidden" name="affiliationAuthorityId" value="${affiliationAuthorityId}">
				 <table width="400px">
					 <c:forEach var="course" items="${courses}" varStatus="rowCounter">
					 			<tr>
					 				<td width="30px"> <input type="checkbox" name="courseId" value="${course.id}"> </td>
					 				<td>${course.displayName}</td>
					 			</tr>
					 </c:forEach>
	 			</table>
	 			<div style="text-align: center;margin-top: 30px">
		  			<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
		  			<input type="button" value="Save" onclick="addCoursesInAcademicYear()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	  			</div>
			</form>	 
	 	</c:when>
	 	<c:otherwise>
	 		<div style="width: 400px">
	 			No course found to add
	 		</div>
	 		<div style="text-align: center;margin-top: 30px">
	 			<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/>
	 		</div>
	 	</c:otherwise>
	 </c:choose>
	 
	 
	 
	 
	  
</div>	
