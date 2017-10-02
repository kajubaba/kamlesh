<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


              <div class="working_area_spacer">
                <div class="form_header_top form_header_top_bg"> <span class="form_heading">Group</span> </div>
                <div class="form_header_bottom form_header_bottom_border">
                  <div class="form_container">
                    <form id="roleForm">
                    	<input type="hidden" id="roleId" name="id"  value="${role.id}">
                      <table width="100%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Name</td>
                          <td class="data_field_width"><input type="text" id="name" name="name" size="50" value="${role.name}" maxlength="128"/></td>
                        </tr>
                        <tr>
                          <td class="label">Active</td>
                          <td class="data_field_width"> <input type="checkbox" value="true" id="active" name="active"  <c:if test="${true == role.active}">checked</c:if>  > </td>
                        </tr>
                        <tr>
                          <td class="label">Description</td>
                          <td class="data_field_width"><textarea id="description" name="description" rows="5" cols="47" maxlength="1024">${role.description}</textarea></td>
                        </tr>
                                	<c:if test="${'update'==action}">
                      	
                      		  <tr>
                        	<td class="label">Permissions</td>
                        	<td style="width: 300px">
                        		<div style="background-color: #FDFDFD;overflow: auto;height: 150px;padding: 20px;">
			                     
			                    	<c:forEach var="privilege" items="${privileges}" varStatus="rowCounter">
			                    		<input type="checkbox" value="${privilege.id}" name="privilegeIds"  <c:if test="${fn:contains(role.privileges, privilege)}">checked</c:if>   > ${privilege.displayName}</br>
			                    	</c:forEach>
                        	</div>
                        	</td>
                        </tr>
                      
                      	
                      	</c:if>
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel"
					onClick="getRoleListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="roleSave" value="Update" onclick="saveRole();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="roleSave" value="Save" onclick="saveRole();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
                </div>
              </div>
