<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Misc Program - Fee Transactions</span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<form action="<c:url value='/miscfee/report/tr' />" method="get">
					<table width="950px">
						<tr>
							<td class="bold">Academic Year : </td>
							<td> 
								<select id="academicYearId" name="ayid" onchange="getMiscActivities()">
									<option value="" ></option>
									<c:forEach var="academicYear" items="${academicYears}">
										<option value="${academicYear.id}" 
												<c:if test="${sayid==academicYear.id}">selected="selected" </c:if>>
											${academicYear.name}
										</option>
									</c:forEach>
								</select>
							</td>
							<td class="bold">Misc Program : </td>
							<td> 
									<select id="miscActivityId"  name="maid" style="width:180px;height: 22px">
										<option value=""></option>
										<c:forEach var="miscActivity" items="${miscActivities}">
											<option <c:if test="${smaid==miscActivity.id}"> selected="selected" </c:if>  value="${miscActivity.id}"> ${miscActivity.name}</option>
										</c:forEach>
										
									</select> 
							</td>
							<td class="bold">From : </td>
							<td> <input id="fromDate" name="from" type="text" readonly="readonly" value="${sfromdate}" > </td>
							<td class="bold">To : </td>
							<td> <input id="toDate" name="to" type="text" readonly="reasdonly" value="${stodate}"> </td>
							<td> <input type="submit" class="button" value="Search"> </td>
						</tr>	
					</table>
				</form>
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(miscFeeTransactions)!=0 }">
			         		Displaying 1-${fn:length(miscFeeTransactions)} of ${fn:length(miscFeeTransactions)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="fee_tr_table" class="grid grid_color_theme_border">
		         <thead>
		          <tr class="grid_heading grid_heading_theme">
		            
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
		           <c:forEach var="feeTransaction" items="${miscFeeTransactions}" varStatus="rowCounter">
			         	<tr class="grid_main_row">
				           	
				            <td class="grid_item">
				            	<a href="<c:url value="/miscfee/transaction/${feeTransaction.id}"/>">${feeTransaction.recieptNo}</a>
				            </td>
				            <td class="grid_item">
				            	<a href="<c:url value="/miscfee/${feeTransaction.student.studentId}"/>">${feeTransaction.student.studentId}</a>
				            </td>
				            <td class="grid_item">${feeTransaction.student.firstName} ${feeTransaction.student.lastName}</td>
				            <td class="grid_item">${feeTransaction.studentClass.courseYear.course.displayName}, ${feeTransaction.studentClass.courseYear.name} Yr.</td>
				            <td class="grid_item">
				        	
				        		<c:set var="grandSum" value="${grandSum+feeTransaction.paidFee}"></c:set>
				        		${feeTransaction.paidFee}
				        	
				            </td>
				            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${feeTransaction.transactionTime}" /></td>
				            <td class="grid_item">${feeTransaction.transactionBy.firstName} ${feeTransaction.transactionBy.lastName}</td>
		           		</tr>
		           		</c:forEach>
		           	</tbody>
		           	<tfoot>	
			           	<tr class="grid_heading grid_heading_theme">
				           
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
			         	<c:when test="${ fn:length(miscFeeTransactions)!=0 }">
			         		Displaying 1-${fn:length(miscFeeTransactions)} of ${fn:length(miscFeeTransactions)}
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

var misc_activity_url='<c:url value="/miscfee/report" />';
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
        	 
       