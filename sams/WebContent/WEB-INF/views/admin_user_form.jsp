<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


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
                      	
                      	<c:if test="${'update'==action}">
                      	
                      		  <tr>
                        	<td>Member of</td>
                        	<td style="width: 300px">
                        		<div style="background-color: #FDFDFD;overflow: auto;height: 150px;padding: 20px;">
			                        <c:forEach var="role" items="${roles}" varStatus="rowCounter">
			                    		<input type="checkbox" value="${role.id}" name="roleIds"  <c:if test="${fn:contains(user.roles, role)}">checked</c:if>   > ${role.name}</br>
			                    	</c:forEach>
                        	</div>
                        	</td>
                        </tr>
                      
                      	
                      	</c:if>
                      
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel" onClick="getUserListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="userSave" value="Update" onclick="saveUser();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="userSave" value="Save" onclick="saveUser();" class="button"/>
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
