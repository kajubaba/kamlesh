<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<div class="working_area_spacer">
	 
	 <div style="overflow: auto; height: 250px; width: 300px">
	 	<form id="busStopPopupForm">
	 	<input type="hidden" name="academicYearId" value="${academicYearId}">
	 	
		 <table width="200px">
			 
				 <c:forEach var="busStop" items="${busStops}" varStatus="rowCounter">
				 	<tr>
				 		<td width="30px"> <input type="checkbox" name="busStopIdArr" value="${busStop.id}"> </td>
				 		<td>${busStop.name}</td>
				 	</tr>
				 </c:forEach>
			 
		 </table>
	 </form>
	 </div>
	 
	 
	  <div style="text-align: center;margin-top: 30px">
	  			<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	  			<input type="button" value="Save" onclick="addBusStopsInAcademicYear()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	  </div>
</div>	
