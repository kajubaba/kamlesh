<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="uuc_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="working_area_spacer">
		       <security:authorize access="hasRole('ROLE_NEW_ROLE')">
		       		<div class="add_new">
		          		<input type="button" value="New Group" class="button" onclick="window.location='<c:url value="/role/new"/>'" />
		        	</div>
		       </security:authorize>
		        <div>
		          Application :<select id="appId" onchange="getAppRoles()">
		          					<option value="0">All</option>
		          					<c:forEach var="application" items="${applications}">
		          						<option value="${application.id}"  <c:if test="${application.id==applicationId}">selected=selected"</c:if>>${application.displayName}</option>
		          					</c:forEach>
		          			   </select>
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
			            <th class="grid_item">User(s)</th>
			            <th class="grid_item">Permission(s)</th>
			            <th class="grid_item">Application</th>
			            <th class="grid_item">Status</th>
			          </tr>
			      </thead>
		         <tbody>
		         <c:forEach var="role" items="${roles}">
		         	<tr class="grid_main_row">
			          	<td class="grid_item">
			          		 <security:authorize access="hasRole('ROLE_MANAGE_ROLE_PERMISSIONS')"><a href="<c:url value='/role/get/${role.id}'/>"></security:authorize>
			          		 ${role.name}
			          		 <security:authorize access="hasRole('ROLE_MANAGE_ROLE_PERMISSIONS')"></a></security:authorize>
			          	  </td>
			            <td class="grid_item"><a href="<c:url value='/user/role/${role.id}'/>" >   ${fn:length(role.users)} </a></td>
			            <td class="grid_item">
				            <c:forEach var="privilege" items="${role.privileges}" varStatus="rowCounter">
				            	${privilege.displayName}&nbsp;,
				            </c:forEach>
			            </td>
			            <td class="grid_item">${role.application.displayName}</td>
			            <td class="grid_item">
			            	<c:choose>
			            		<c:when test="${role.active==true}">Active</c:when>
			            		<c:otherwise>In-Active</c:otherwise>
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
				         	<c:when test="${ fn:length(roles)!=0 }">
				         		Displaying 1-${fn:length(roles)} of ${fn:length(roles)}
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
var role_base_url="<c:url value='/role'/>";

$(function() {
	bindRoleListingWithDataTable();
});
function bindRoleListingWithDataTable(){
	
	$('#role_listing_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      { "bSortable": false },
	                      null,
	                      null
	                     ]
	        
	    } );
}      

//-->


</script>