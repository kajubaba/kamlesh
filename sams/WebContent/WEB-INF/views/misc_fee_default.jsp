<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<jsp:include page="sams_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Misc Program - Fee Payment</span></div>
		<div class="working_area_spacer">
			<div style="width: 100%; text-align: center;">
				<span>Student ID</span>
				<span><input type="text" id="studentAssignedId" value="${student.studentId}" style="width: 400px; height: 25px;font-size: 14px" /></span>
				<span> <input type="button" value="Search" class="button" onclick="getStudentMiscFeeInfo()" /></span>
			</div>
			
			<div id="student_fee_info_container">
				<jsp:include page="misc_fee_student.jsp"/>
			</div>
		</div>
	</div>
</div>	
<jsp:include page="sams_footer.jsp"/>
<div id="popupBackground" class="popupBack" onclick="closeStudentFeePopup()"></div>


<script type="text/javascript">
	var student_misc_fee_url="<c:url value='/miscfee'/>";
	var admission_base_url="<c:url value='/admission'/>";
	$(function() {
		
		 $( "#studentAssignedId" ).autocomplete({
			 source: function( request, response ) {
			 $.ajax({
			 	url: admission_base_url+"/quicksearch?searchStr="+$("#studentAssignedId").val(),
			 	dataType: "json",
				success: function( data ) {
					response( $.map( data, function( item ) {
			 		return {
			 			label: item.fn +" "+item.ln+" ["+item.sid+"]",
			 			value: item.sid
			 		};
			 }));
			 }
			 });
			 },
			 minLength: 1,
			 select: function( event, ui ) {
				 getStudentMiscFeeInfo();
			 }
			 });
		
		
		
	});
</script>

       