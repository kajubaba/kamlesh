<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Fee (Head wise)</span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<table style="width: 50%">
					<tr>
						<td>Academic Year : </td>
						<td>${selectedAcademicYear.name}</td>
						<td>From : </td>
						<td>${fromDate}</td>
						<td>To : </td>
						<td>${toDate}</td>
					</tr>
				</table>
			</div>
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(paidFees)!=0 }">
			         		Displaying 1-${fn:length(paidFees)} of ${fn:length(paidFees)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="paid_fee_table" class="grid grid_color_theme_border">
          	 	<thead>
          	 		<tr class="grid_heading grid_heading_theme">
			            <th class="grid_item">Student ID</th>
			            <th class="grid_item">Student Name</th>
			            <th class="grid_item">Current Class</th>
			            <th class="grid_item">Fee (Rs.)</th>
		            </tr>
          	 	</thead>
				<tbody>
					<c:forEach var="paidFee" items="${paidFees}" varStatus="rowCounter">
						<tr class="grid_main_row">
				        	<td class="grid_item">${paidFee.studentAssignedId}</td>
					        <td class="grid_item">${paidFee.studentFirstName} ${paidFee.studentLastName}</td>
					        <td class="grid_item">${paidFee.className}</td>
					        <td class="grid_item"><c:set var="grandSum" value="${grandSum+paidFee.amountPaid}"></c:set>${paidFee.amountPaid}</td>
			           	</tr>
		        	</c:forEach>
				</tbody>
				<tfoot>
					<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item"></td>
			            <td class="grid_item"></td>
			            <td class="grid_item"></td>
			            <td class="grid_item">${grandSum}</td>
	                </tr>
				</tfoot>
		           	
		     </table>
		
		     <div class="grid_info" style="margin-top: 12px">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(paidFees)!=0 }">
			         		Displaying 1-${fn:length(paidFees)} of ${fn:length(paidFees)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>					
			
		
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>
<script type="text/javascript">


$(function() {
	
	$( "#fromDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	
	$( "#toDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
	
	$('#paid_fee_table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false
    } );
	
});

</script>

        	 
       