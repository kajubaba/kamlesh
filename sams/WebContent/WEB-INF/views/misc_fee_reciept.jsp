<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ page import="com.narendra.sams.core.util.AmountInWords" %>
<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		
		<div class="working_area_spacer">
			
			<div id="printRecieptDiv" class="feeRecieptDuplicate">
				<jsp:include page="misc_fee_reciept_content.jsp"/>
			</div>	
			<div style="width: 100%;text-align: center;margin-top: 13px;">
				<input type="button" value="Print" class="button" onclick="$('#printRecieptDiv').jqprint();" />
			</div>
			
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>

