<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Courses</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Course" class="button" onclick="getNewCourseView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(courses)!=0 }">
		         		Displaying 1-${fn:length(courses)} of ${fn:length(courses)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="course_listing_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Course Name</th>
	            <th class="grid_item">Display Name</th>
	            <th class="grid_item">Duration (years)</th>
	            <th class="grid_item">Affiliated with</th>
	          </tr>
         </thead>
         <tbody>
	         <c:forEach var="course" items="${courses}" varStatus="rowCounter">
	         	<tr class="grid_main_row">
		          	<td class="grid_item"><a href="javascript:void(0)" onclick="loadCourseToUpdate(${course.id})"> ${course.name}</a> </td>
		            <td class="grid_item">${course.displayName}</td>
		            <td class="grid_item">${course.duration}</td>
		            <td class="grid_item">${course.affiliatedTo.displayName}</td>
	          </tr>
	         </c:forEach>
	        </tbody> 
        </table>
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		       <c:choose>
		         	<c:when test="${ fn:length(courses)!=0 }">
		         		Displaying 1-${fn:length(courses)} of ${fn:length(courses)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      