<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Academic Year</span></div>
              <div class="working_area_spacer">
                
               
                  <div class="form_container">
	                  <form id="academicYearForm">
	                  	<input type="hidden" name="institute.id" value="${academicYear.institute.id}" />
		                  <table width="100%" cellspacing="15" cellpadding="0">
		                    
		                    <tr>
		                      <td class="label"><span class="mandatory_mark">*</span> From </td>
		                      <td class="data_field_width"><input type="text" id="fromDate" name="fromDate" readonly="readonly" size="21" value="<fmt:formatDate pattern="dd-MMM-yyyy" value='${academicYear.fromDate}' />" />
		                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="mandatory_mark">*</span> To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                        <input type="text" id="toDate" name="toDate" readonly="readonly" size="21" value="<fmt:formatDate pattern="dd-MMM-yyyy" value='${academicYear.toDate}' />" />
		                      </td>
		                    </tr>
							<tr>
		                      <td class="label"><span class="mandatory_mark">*</span> Name</td>
		                      <td class="data_field_width"><input type="text" id="name" name="name" readonly="readonly" size="65" value="${academicYear.name}" maxlength="124"/></td>
		                    </tr>
									                    
		                    <tr>
		                      <td class="label">Description</td>
		                      <td><textarea id="description" name="description" cols="62" rows="10" maxlength="1024">${academicYear.description}</textarea></td>
		                    </tr>
		                   
		                    <tr>
		                      <td class="label"></td>
		                      <td colspan="2" style="text-align: center;"><input type="button" value="Cancel"
							onClick="getAcademicYearListView();" class="button"/>
		                        
		                       <c:choose>
			                        <c:when test="${'update'==action}">
	                            		<input type="hidden" name="id" value="${academicYear.id}">
	                            		<input type="button" value="Update" onclick="updateAcademicYear();" class="button"/>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<input type="button" value="Save" onclick="addAcademicYear();" class="button"/>
	                            	</c:otherwise>
		                        </c:choose>
		                        </td>
		                    </tr>
		                  </table>
	                </form>
                  </div>
              
              </div>
