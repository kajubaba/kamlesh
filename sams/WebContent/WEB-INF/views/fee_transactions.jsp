<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Fee Transactions</span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<form action="<c:url value='/fee/transaction' />" method="post">
					<table width="700px">
						<tr>
							<td class="bold">Academic Year : </td>
							<td> <select name="academicYearId">
									<option value="" ></option>
									<c:forEach var="academicYear" items="${academicYears}">
										<option value="${academicYear.id}" <c:if test="${selectedAcademicYear==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
									</c:forEach>
								</select>  </td>
							<td class="bold">From : </td>
							<td> <input id="fromDate" name="fromDate" type="text" readonly="readonly" value="${fromDate}" > </td>
							<td class="bold">To : </td>
							<td> <input id="toDate" name="toDate" type="text" readonly="reasdonly" value="${toDate}"> </td>
							<td> <input type="submit" class="button" value="Search"> </td>
						</tr>	
					</table>
				</form>
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(feeTransactions)!=0 }">
			         		Displaying 1-${fn:length(feeTransactions)} of ${fn:length(feeTransactions)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="fee_tr_table" class="grid grid_color_theme_border">
		         <thead>
		          <tr class="grid_heading grid_heading_theme">
		            <th class="grid_item">Tr #</th>
		            <th class="grid_item">Reciept #</th>
		            <th class="grid_item">Student ID</th>
		            <th class="grid_item">Student Name</th>
		            <th class="grid_item">Class (Yr.)</th>
		            <th class="grid_item">Rs.</th>
		            <th class="grid_item">Date/Time</th>
		            <th class="grid_item">User</th>
		          </tr>
		          </thead>
		           <tbody>
		           <c:forEach var="feeTransaction" items="${feeTransactions}" varStatus="rowCounter">
			         	<tr class="grid_main_row">
				           	<td class="grid_item"><a href="<c:url value="/fee/transaction/tr/${feeTransaction.id}" />" >${feeTransaction.transactionId}</a></td>
				            <td class="grid_item">${feeTransaction.recieptNo}</td>
				            <td class="grid_item">${feeTransaction.student.studentId}</td>
				            <td class="grid_item">${feeTransaction.student.firstName} ${feeTransaction.student.lastName}</td>
				            <td class="grid_item">${feeTransaction.studentClass.displayName}</td>
				            <td class="grid_item">
				        		<c:set var="grandSum" value="${grandSum+feeTransaction.feeSum}"></c:set>
				        		${feeTransaction.feeSum}
				            </td>
				            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${feeTransaction.transactionTime}" /></td>
				            <td class="grid_item">${feeTransaction.user.firstName} ${feeTransaction.user.lastName}</td>
		           		</tr>
		           		</c:forEach>
		           	</tbody>
		           	<tfoot>	
			           	<tr class="grid_heading grid_heading_theme">
				            <td class="grid_item"></td>
				            <td class="grid_item"></td>
				            <td class="grid_item"></td>
				             <td class="grid_item"></td>
				             <td class="grid_item"></td>
				            <td class="grid_item">${grandSum}</td>
				            <td class="grid_item"></td>
				            <td class="grid_item"></td>
			          </tr>
		          </tfoot>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(feeTransactions)!=0 }">
			         		Displaying 1-${fn:length(feeTransactions)} of ${fn:length(feeTransactions)}
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
	
	$('#fee_tr_table').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false
    } );
	
});

</script>
        	 
       