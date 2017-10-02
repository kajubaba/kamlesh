<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Affiliation Authority</span></div>
              <div class="working_area_spacer">
                
               
                  <div class="form_container">
                    <form id="aaForm">
                    	
                      <table width="100%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Name</td>
                          <td class="data_field_width"><input type="text" id="name" name="name" size="50" value="${affiliationAuthority.name}" maxlength="128"/></td>
                        </tr>
                        
                        
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Display Name</td>
                          <td class="data_field_width"><input type="text" id="displayName" name="displayName" size="50" value="${affiliationAuthority.displayName}" maxlength="128"/></td>
                        </tr>
                        
                        <tr>
                          <td class="label">Active</td>
                          <td class="data_field_width"> <input type="checkbox" value="true" id="active" name="active"  <c:if test="${true == affiliationAuthority.active}">checked</c:if>  > </td>
                        </tr>
                        <tr>
                          <td class="label">Description</td>
                          <td class="data_field_width"><textarea id="description" name="description" rows="5" cols="47" maxlength="1024">${affiliationAuthority.description}</textarea></td>
                        </tr>
                       
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel"
					onClick="getAffiliationAuthorityListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="hidden" name="id" value="${affiliationAuthority.id}">
                            		<input type="button" value="Update" onclick="updateAffiliationAuthority();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" value="Save" onclick="addAffiliationAuthority();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
               
              </div>
