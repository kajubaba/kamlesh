<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Course</span></div>
              <div class="working_area_spacer">
                
                
                  <div class="form_container">
                    <form id="courseForm">
                    	<input type="hidden" id="courseId" name="id"  value="${course.id}">
                      <table width="100%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Course Name</td>
                          <td class="data_field_width"><input type="text" id="name" name="name" size="50" value="${course.name}" maxlength="128"/></td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span>Display Name</td>
                          <td class="data_field_width"><input type="text" id="displayName" name="displayName" size="50" value="${course.displayName}" maxlength="128"/> </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Duration (years)</td>
                          <td class="data_field_width">
                          	<select class="data_field_width" id="duration" name="duration" onchange="changeLabel()" >
                          			<option value=""></option>
                          			<option value="1"  <c:if test="${1==course.duration}">selected="selected"</c:if>>1</option>
                          			<option value="2" <c:if test="${2==course.duration}">selected="selected"</c:if>>2</option>
                          			<option value="3" <c:if test="${3==course.duration}">selected="selected"</c:if>>3</option>
                          			<option value="4" <c:if test="${4==course.duration}">selected="selected"</c:if>>4</option>
                          			<option value="5" <c:if test="${5==course.duration}">selected="selected"</c:if>>5</option>
                          		</select>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Affiliated to</td>
                          <td class="data_field_width">
                          		
                          		<select class="data_field_width" id="affiliatedTo" name="affiliatedTo.id">
                          			<option value=""></option>
                          			<c:forEach var="affiliationAuthority" items="${affiliationAuthorities}">
                          				<option value="${affiliationAuthority.id}"  <c:if test="${affiliationAuthority.id==course.affiliatedTo.id}">selected="selected"</c:if>">${affiliationAuthority.displayName}</option>
                          			</c:forEach>
                          		</select>
                          </td>
                        </tr>
                       
                       <c:if test="${'update'!=action}">
                       		<tr>
                        	<td class="label">&nbsp;</td>
                        	<td> <input type="checkbox" checked="checked" name="autoCreation" disabled="disabled"> <span id="ccl">Create course year(s) automatically</span>  </td>
                        </tr>
                       </c:if>
                       
                        
                        <tr>
                          <td class="label">Description</td>
                          <td class="data_field_width"><textarea id="description" name="description" rows="5" cols="47" maxlength="1024">${course.description}</textarea></td>
                        </tr>
                
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel" onClick="getCourseListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="courseSave" value="Update" onclick="saveCourse();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="courseSave" value="Save" onclick="saveCourse();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
                
              </div>
