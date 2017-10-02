<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="uuc_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="working_area_spacer">
		       
		        <security:authorize access="hasRole('ROLE_NEW_USER')">
			        <div class="add_new">
			          <input type="button" value="New User" class="button" onclick="window.location='<c:url value="/user/new"/>'" />
			        </div>
		       </security:authorize>
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
			          </tr>
		          </thead>
		         <tbody>
		         <c:forEach var="user" items="${users}">
		         	<tr class="grid_main_row">
			          	<td class="grid_item">
			          		<security:authorize access="hasRole('ROLE_MANAGE_USER_ROLE')">
			          			<a href="<c:url value='/user/get/${user.id}'/>">
			          		</security:authorize>
			          		 ${user.firstName} ${user.lastName} 
			          		 <security:authorize access="hasRole('ROLE_NEW_USER')">
			          			</a>
			          		</security:authorize> </td>
			            <td class="grid_item">${user.userName}</td>
			            <td class="grid_item">
			            	<c:forEach var="role" items="${user.roles}" varStatus="rowCounter">
			            		<security:authorize access="hasRole('ROLE_MANAGE_ROLE_PERMISSIONS')"><a href="<c:url value='/role/get/${role.id}'/>"></security:authorize>
			            			&nbsp; ${role.name},
			            		<security:authorize access="hasRole('ROLE_MANAGE_ROLE_PERMISSIONS')"></a></security:authorize>
			            	</c:forEach>
			            </td>
			            <td class="grid_item">
			            	<c:choose>
			            		<c:when test="${user.active=='true'}">Active</c:when>
			            		<c:otherwise>In Active</c:otherwise>
			            	</c:choose>
			            
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
	</div>
</div>
<jsp:include page="uuc_footer.jsp"/>
<script type="text/javascript">
<!--


$(function() {
	bindUserListingWithDataTable();
});

function bindUserListingWithDataTable(){
	
	$('#user_listing_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      { "bSortable": false },
	                      null
	                     ]
	    } );
}
      

//-->


</script>
