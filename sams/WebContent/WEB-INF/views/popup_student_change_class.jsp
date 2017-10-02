<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div style="margin-top: 0px">
	<form id="changeClassForm">
	<table>
		<tr>
			<td>University :</td>
			<td>
				<select id="drpDwnAffiliationAuth" name="drpDwnAffiliationAuth" class="data_field_width" onchange="findCourses()" style="height: 22px">
					<option value="" selected="selected"></option>
						<c:forEach var="affiliationAuth" items="${affiliationAuthorities}">
							<option value="${affiliationAuth.id}" >${affiliationAuth.displayName}</option>
						</c:forEach>
				</select>
			</td>
		</tr> 
		<tr>
			<td>Course :</td>
			<td>
				<select id="drpDwnCourse" name="drpDwnCourse" class="data_field_width" onchange="findActiveClasses()" style="height: 22px"></select>
			</td>
		</tr>
		<tr>
			<td>Yr/Sem</td>
			<td>
				<select id="drpDwnClass" name="academicYearClassId"  class="data_field_width" style="height: 22px"></select>
			</td>
		</tr>
		<tr>
			<td>Comments :</td>
			<td> <textarea name="changeClassComments"  rows="5" cols="55"></textarea> </td>
		</tr>
	</table>
	</form>
	<div style="width: 100%;text-align: center;margin-top: 25px"> 
		<div style="width: 60px; float: left;"  class="action-button" onclick="closeChangeStudentStatusPopup()">Cancel</div>
		<div style="width: 100px; float: left;"  class="action-button" onclick="changeClass()">Change Class</div> 
	</div>
</div>