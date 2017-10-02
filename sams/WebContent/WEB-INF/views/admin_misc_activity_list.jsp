<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Misc Activities</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Misc Activity" class="button" onclick="newMiscActivities(${ayId})" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(miscActivities)!=0 }">
		         		Displaying 1-${fn:length(miscActivities)} of ${fn:length(miscActivities)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="tbl_list_miscactivities" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Activity Name</th>
	            <th class="grid_item">Fee</th>
	            <th class="grid_item">Classes</th>
	            <th class="grid_item">Modified By</th>
	            <th class="grid_item">Modified On</th>
	          </tr>
         </thead>
         <tbody>
	         <c:forEach var="miscActivity" items="${miscActivities}" varStatus="rowCounter">
	         	<tr class="grid_main_row">
		          	<td class="grid_item"><a href="javascript:void(0)" onclick="viewMiscActivity(${miscActivity.id})">${miscActivity.name}</a></td>
		            <td class="grid_item">${miscActivity.fee}</td>
		            <td class="grid_item"></td>
		            <td class="grid_item">${miscActivity.createdBy.firstName} ${miscActivity.createdBy.lastName}</td>
		            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${miscActivity.modifiedDate}" /></td>
	          </tr>
	         </c:forEach>
	        </tbody> 
        </table>
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		       <c:choose>
		         	<c:when test="${ fn:length(miscActivities)!=0 }">
		         		Displaying 1-${fn:length(miscActivities)} of ${fn:length(miscActivities)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      