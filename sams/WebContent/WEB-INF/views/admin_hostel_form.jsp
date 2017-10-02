<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Hostel</span></div>
              <div class="working_area_spacer">
                <div class="form_header_top form_header_top_bg"> <span class="form_heading">Hostel</span> </div>
                <div class="form_header_bottom form_header_bottom_border">
                  <div class="form_container">
                    <form id="hostelForm">
                    	<input type="hidden" id="hostelId" name="id"  value="${hostel.id}">
                      <table width="100%" cellspacing="15" cellpadding="0">
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Name</td>
                          <td class="data_field_width"><input type="text" id="name" name="name" size="50" value="${hostel.name}" maxlength="128"/></td>
                        </tr>
                        <tr>
                          <td class="label">Active</td>
                          <td class="data_field_width"> <input type="checkbox" value="true" id="active" name="active"  <c:if test="${true == hostel.active}">checked</c:if>  > </td>
                        </tr>
                       <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel"
					onClick="getHostelListView();" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="hostelSave" value="Update" onclick="saveHostel();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="hostelSave" value="Save" onclick="saveHostel();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
                </div>
              </div>
