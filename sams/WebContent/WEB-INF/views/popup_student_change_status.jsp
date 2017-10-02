<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div >
	<form id="changeStatusForm">
	<table cellspacing="10px">
		<tr>
			<td>Current Status :</td>
			<td> ${student.studentStatus.name} </td>
		</tr>
		<tr>
			<td>New Status :</td>
			<td>
				<select name="newStatusId" style="width: 278px">
					<option value=""></option>
					<option value="7">Admission withdraw</option>
					<option value="1">Cancel Addmission</option>
					<option value="3">Degree Completed</option>
					<option value="2">Terminate Addmission</option>
					<%-- 
					<c:forEach var="studentStatus" items="${studentStatusList}">
						<option value="${studentStatus.id}">${studentStatus.name}</option>
					</c:forEach>
					--%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Comments :</td>
			<td>
				<textarea name="changeStatusComments" rows="5" cols="50"></textarea>
			</td>
		</tr>
	</table>
	</form>
	<div style="width: 100%;text-align: center;margin-top: 25px"> 
		<div style="width: 60px; float: left;"  class="action-button" onclick="closeChangeStudentStatusPopup()">Cancel</div>
		<div style="width: 100px; float: left;"  class="action-button" onclick="changeStatus()">Change Status</div> 
	</div>
</div>