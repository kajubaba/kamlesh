<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New User" class="button" onclick="getNewUserView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(users)!=0 }">
		         		Displaying 1-${fn:length(users)} of ${fn:length(users)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="user_listing_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">User Name</th>
	            <th class="grid_item">Login Name</th>
	            <th class="grid_item">Group(s)</th>
	             <th class="grid_item">Status</th>
	            <th class="grid_item">Created By</th>
	            <th></th>
	            <th class="grid_item">Created Date</th>
	          </tr>
          </thead>
         <tbody>
         <c:forEach var="user" items="${users}">
         	<tr class="grid_main_row">
	          	<td class="grid_item"><a href="javascript:void(0)" onclick="loadUserToUpdate(${user.id})"> ${user.firstName} ${user.lastName} </a> </td>
	            <td class="grid_item">${user.userName}</td>
	            <td class="grid_item">
	            	<c:forEach var="role" items="${user.roles}" varStatus="rowCounter">
	            		&nbsp;${role.name},
	            	</c:forEach>
	            </td>
	            <td class="grid_item">
	            	<c:choose>
	            		<c:when test="${user.active=='true'}">Active</c:when>
	            		<c:otherwise>In Active</c:otherwise>
	            	</c:choose>
	            
	            </td>
	            <td class="grid_item">${user.createdBy.firstName} ${user.createdBy.lastName}</td>
	            <td>${user.createdDate.time}</td>
	            <td class="grid_item">
	            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${user.createdDate}" />
	            </td>
          </tr>
         </c:forEach>
         </tbody>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(users)!=0 }">
		         		Displaying 1-${fn:length(users)} of ${fn:length(users)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      