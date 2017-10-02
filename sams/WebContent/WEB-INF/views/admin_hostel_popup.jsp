<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<div class="working_area_spacer">
	 
	 <div style="overflow: auto; height: 250px; width: 300px">
	 	<form id="hostelPopupForm">
	 	<input type="hidden" name="academicYearId" value="${academicYearId}">
		 <table width="200px">
			 
				 <c:forEach var="hostel" items="${hostels}" varStatus="rowCounter">
				 	<tr>
				 		<td width="30px"> <input type="checkbox" name="hostelIdArr" value="${hostel.id}"> </td>
				 		<td>${hostel.name}</td>
				 	</tr>
				 </c:forEach>
			 
		 </table>
	 </form>
	 </div>
	  <div style="text-align: center;margin-top: 30px">
	  			<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	  			<input type="button" value="Save" onclick="addHostelsInAcademicYear()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	  </div>
</div>	
