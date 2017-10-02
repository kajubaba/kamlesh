<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="uuc_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">

              <div class="working_area_spacer">
                <div class="form_header_top form_header_top_bg"> <span class="form_heading">User</span> </div>
                <div class="form_header_bottom form_header_bottom_border">
                  <div class="form_container">
                    
                    <div>
                   		<form id="userForm">
                      <input type="hidden" id="userId" name="id"  value="${user.id}">
                      <table width="60%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> First Name</td>
                          <td class="data_field_width"><input type="text" id="firstName" name="firstName" size="37" value="${user.firstName}" maxlength="128"/></td>
                        </tr>
                          <tr>
                          <td class="label">Middle Name</td>
                          <td class="data_field_width"><input type="text" id="middleName" name="middleName" size="37" value="${user.middleName}" maxlength="128"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Last Name</td>
                          <td class="data_field_width"><input type="text" id="lastName" name="lastName" size="37" value="${user.lastName}" maxlength="128"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> User Name</td>
                          <td class="data_field_width"><input type="text" id="userName" name="userName" size="37" value="${user.userName}" maxlength="56"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Password</td>
                          <td class="data_field_width"><input type="text" id="password" name="password" size="37" value="${user.password}" maxlength="56"/></td>
                        </tr>
                        <tr>
                          <td class="label">Active</td>
                          <td class="data_field_width"> <input type="checkbox" value="true" id="active" name="active"  <c:if test="${true == user.active}">checked</c:if>  > </td>
                        </tr>
                     </table>
                      	<c:if test="${'update'==action}">
                     		<table width="100%">
                     			<tr>
                     				<td class="label">Role(s):</td>
                     				<td>
                     						
							                      <table id="user_roles_table" class="grid grid_color_theme_border">
							                      		<thead>
								                      		<tr class="grid_heading grid_heading_theme"> 
								                      			<th class="grid_item">Select</th>
								                      			<th class="grid_item">Group</th>
								                      			<th class="grid_item">Application</th>
								                      		</tr>
							                      		</thead>
							                      		<tbody>
							                      		<c:forEach var="role" items="${roles}" varStatus="rowCounter">
								                      		<tr class="grid_main_row">
								                      			<td class="grid_item"><input type="checkbox" value="${role.id}" name="roleIds"  <c:if test="${fn:contains(user.roles, role)}">checked</c:if>   ></td>
								                      			<td class="grid_item">${role.name}</td>
								                      			<td class="grid_item">${role.application.displayName}</td>
								                      		</tr>
							                      		</c:forEach>
							                      		</tbody>
							                      </table>
                        					
                     				</td>
                     			</tr>
                     		</table> 	
                      	</c:if>
                      <table style="margin-top: 15px">
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel" onClick="window.location='<c:url value="/user/list"/>'" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<security:authorize access="hasRole('ROLE_MANAGE_USER_ROLE')">
                            			<input type="button" id="userSave" value="Update" onclick="saveUser('${action}');" class="button"/>
                            		</security:authorize>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="userSave" value="Save" onclick="saveUser('${action}');" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                        </table>
                      
                    </form>
                   		 
                    </div>
                    

                    
                   </div>
                </div>
              </div>
	</div>
</div>
<jsp:include page="uuc_footer.jsp"/>
<script type="text/javascript">
<!--
var ACTION_ADD="add";
var ACTION_UPDATE="update";
var user_base_url="<c:url value='/user'/>";


$(function() {
	$('#user_roles_table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
        "aoColumns": [
					  { "bSortable": false },
                      null,
                      null
                      
                     ]
    } );
});

//-->
</script>