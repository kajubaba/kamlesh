<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Misc Activity</span></div>
              <div class="working_area_spacer">
                <div class="form_header_top form_header_top_bg"> <span class="form_heading">Misc Activity</span> </div>
                <div class="form_header_bottom form_header_bottom_border">
                  <div class="form_container">
                    <form id="miscActivityForm">
                    	<input type="hidden" name="id"  value="${miscActivity.id}">
                    	<input type="hidden" name="academicYearId"  value="${ayId}">
                      	<table width="100%" cellspacing="15" cellpadding="0">
	                        <tr>
	                          <td class="label">
	                          	<span class="mandatory_mark">*</span> Name
	                          </td>
	                          <td class="data_field_width">
	                          	<input type="text" name="name" size="50" value="${miscActivity.name}" maxlength="128"/>
	                          </td>
	                        </tr>
	                        <tr>
	                          <td class="label">
	                          	<span class="mandatory_mark">*</span>Fee
	                          </td>
	                          <td class="data_field_width">
	                          	<input type="text" name="fee" size="50" value="${miscActivity.fee}" maxlength="128"/> 
	                          </td>
	                        </tr>
                        <tr>
                          <td class="label">
                          	<span class="mandatory_mark">*</span> Fee Head
                          </td>
                          <td class="data_field_width">
                          	<table class="grid grid_color_theme_border">
                          		<thead class="grid_heading grid_heading_theme">
                          			<tr>
                          				<th class="grid_item">Head</th>
                          				<th class="grid_item">Fee</th>
                          			</tr>
                          		</thead>	
                          		<tbody>
                          			
                          			<c:choose>
                          				<c:when test="${null!=miscActivity}">
                          					<c:forEach var="head" items="${miscActivity.miscActivityHeads}" varStatus="rowCounter">
		                          				<tr>
		                          					<td class="grid_item">
		                          						<input type="hidden" 
		                          							   name="heads['${rowCounter.index}'].id" 
		                          							   value="${head.id}" />
		                          						<input type="text" 
		                          							   name="heads['${rowCounter.index}'].name" 
		                          							   value="${head.headName}" />
		                          					</td>
		                          					<td class="grid_item">
		                          						<input type="text" 
		                          							   maxlength="6" 
		                          							   name="heads['${rowCounter.index}'].fee" 
		                          							   value="${head.fee}" 
		                          							   class="misc_activity_head_fee numeric" 
		                          							   onkeyup="sumMiscActivityHeadFee()" />
		                          					</td>
		                          				</tr>
                          					</c:forEach>
                          				</c:when>
                          				<c:otherwise>
	                          				<tr>
	                          					<td class="grid_item">
	                          						<input type="hidden" 
	                          							   name="heads['0'].id" 
	                          							   value="" />
	                          						<input type="text" 
	                          							   name="heads['0'].name" 
	                          							   value="" />
	                          					</td>
	                          					<td class="grid_item">
	                          						<input type="text" 
	                          							   maxlength="6" 
	                          							   name="heads['0'].fee" 
	                          							   value="" 
	                          							   class="misc_activity_head_fee numeric" 
	                          							   onkeyup="sumMiscActivityHeadFee()" />
	                          					</td>
	                          				</tr>
	                          				
	                          				<%-- 
	                          				<tr>
	                          					<td class="grid_item">
	                          						<input type="hidden" 
	                          							   name="heads['1'].id" 
	                          							   value="" />
	                          						<input type="text" 
	                          							   name="heads['1'].name" 
	                          							   value="" />
	                          					</td>
	                          					<td class="grid_item">
	                          						<input type="text" 
	                          							   maxlength="6" 
	                          							   name="heads['1'].fee" 
	                          							   value="" 
	                          							   class="misc_activity_head_fee numeric" 
	                          							   onkeyup="sumMiscActivityHeadFee()" />
	                          					</td>
	                          				</tr>
	                          				--%>
                          				</c:otherwise>
                          			</c:choose>
                          			
                          			
                          		</tbody>
                          		<tfoot class="grid_heading grid_heading_theme">
                          			<tr>
                          				<td class="grid_item">Total</td>
                          				<td class="grid_item" id="misc_activity_head_fee_total"></td>
                          			</tr>
                          		</tfoot>
                          	</table>
                          </td>
                        </tr>
                        <tr>
                          <td class="label"><span class="mandatory_mark">*</span> Courses</td>
                          <td class="data_field_width">
                          		
                          		<table>
                          			<tr>
                          				<td>
                          					<b>Available Courses</b><br/>
                          					<select multiple="multiple" id="list_maavc" size="20" style="width: 300px">
                          						<c:forEach var="courseYearSetting" items="${courseYearSettings}">
                          							<option value="${courseYearSetting.id}">${courseYearSetting.courseYear.course.displayName} ${courseYearSetting.courseYear.name} Yr</option>
                          						</c:forEach>
                          					</select>
                          				
                          				</td>
                          				<td style='width:50px;text-align:center;vertical-align:middle;'>
                          					<input type='button' id='btnRight' value ='  >  ' onclick="assignCoursesToMiscActivity()"/>
        									<br/>
        									<input type='button' id='btnLeft' value ='  <  ' onclick="unAssignCoursesToMiscActivity()" />
                          				</td>
                          				<td>
                          					<b>Applied on Courses</b><br/>
                          					<select name="courseIds" multiple="multiple" id="list_maapc" size="20" style="width: 300px" >
                          						<c:forEach var="miscActivityCourseYear" items="${miscActivity.miscActivityCourseYears}">
                          							<option value="${miscActivityCourseYear.courseYearSetting.id}" selected="selected">${miscActivityCourseYear.courseYearSetting.courseYear.course.displayName} ${miscActivityCourseYear.courseYearSetting.courseYear.name} Yr</option>
                          						</c:forEach>
                          					</select>
                          				</td>
                          			
                          			</tr>
                          		</table>
                          		
                          		
                          </td>
                        </tr>
                       
                        <tr>
                          <td class="label"></td>
                          <td><input type="button" value="Cancel" onClick="listMiscActivities(${ayId});" class="button"/>
                            
                            <c:choose>
                            	<c:when test="${'update'==action}">
                            		<input type="button" id="courseSave" value="Update" onclick="updateMiscActivity();" class="button"/>
                            	</c:when>
                            	<c:otherwise>
                            		<input type="button" id="courseSave" value="Save" onclick="saveMiscActivity();" class="button"/>
                            	</c:otherwise>
                            </c:choose>
                            
                            
                            
                            </td>
                        </tr>
                      </table>
                    </form>
                  </div>
                </div>
              </div>
