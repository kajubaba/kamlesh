<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Group" class="button" onclick="getNewRoleView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(roles)!=0 }">
		         		Displaying 1-${fn:length(roles)} of ${fn:length(roles)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="role_listing_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Group</th>
	            <th class="grid_item">Member(s)</th>
	            <th class="grid_item">Permission(s)</th>
	            <th class="grid_item">Status</th>
	            <th class="grid_item">User</th>
	            <th></th>
	            <th class="grid_item">Date</th>
	          </tr>
	      </thead>
         <tbody>
         <c:forEach var="role" items="${roles}">
         	<tr class="grid_main_row">
	          	<td class="grid_item"><a href="javascript:void(0)" onclick="loadRoleToUpdate(${role.id})"> ${role.name}</a> </td>
	            <td class="grid_item"><a href="javascript:void(0)" onclick="getUserListOfRole(${role.id})">   ${fn:length(role.users)} </a></td>
	            
	            <td class="grid_item">
	            
	            <c:forEach var="privilege" items="${role.privileges}" varStatus="rowCounter">
	            	${privilege.displayName}&nbsp;,
	            </c:forEach>
	            </td>
	            <td class="grid_item"> 
	            	<c:choose>
	            		<c:when test="${role.active=='true'}">Active</c:when>
	            		<c:otherwise>In Active</c:otherwise>
	            	</c:choose>
	            </td>
	            <td class="grid_item">${role.createdBy.firstName} ${role.createdBy.lastName}</td>
	            <td>${role.createdDate.time}</td>
	            <td class="grid_item">
	            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${role.createdDate}" />
	            </td>
          </tr>
         </c:forEach>
         </tbody>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		        <c:choose>
		         	<c:when test="${ fn:length(roles)!=0 }">
		         		Displaying 1-${fn:length(roles)} of ${fn:length(roles)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      