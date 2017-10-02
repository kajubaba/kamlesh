<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Bus Stop</span></div>
              <div class="working_area_spacer">
                
               
                  <div class="form_container">
                    <form id="busStopForm">
                    	<input type="hidden" id="busStopId" name="id"  value="${busStop.id}">
                    	<input type="hidden" name="institute.id"  value="${busStop.institute.id}">
                      <table width="100%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Name</td>
                          <td class="data_field_width"><input type="text" id="name" name="name" size="50" value="${busStop.name}" maxlength="128"/></td>
                        </tr>
                         <tr>
                          <td class="label">Distance (km)</td>
                          <td class="data_field_width"><input type="text" id="distance" name="distance" size="50" value="${busStop.distance}" maxlength="10" class="numeric"/></td>
                        </tr>
                       
                        <tr>
                          <td class="label">Active</td>
                          <td class="data_field_width"> <input type="checkbox" value="true" id="active" name="active"  <c:if test="${true == busStop.active}">checked</c:if>  > </td>
                        </tr>
                       <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel"
					onClick="getBusStopListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="busStopSave" value="Update" onclick="saveBusStop();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="busStopSave" value="Save" onclick="saveBusStop();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
              
              </div>
