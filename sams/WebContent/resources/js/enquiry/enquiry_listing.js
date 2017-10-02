function activate(e, message)
{   
    if (isNotEnter(e))
    {   
        return true;
    }   
    else // ie. enter was pressed
    {   
        // only proceed to search() if message is not empty
        if (message.length >= 0)
        {   
        	searchEnquiries();
        }   
        return false;
    }   
}

function isNotEnter(e)
{   
    if (e.keyCode == 13) 
    {   
        return false;
    }   
    else
    {   
        return true;
    }   
}

function searchEnquiries(academicYearId){
	
	searchType="quick";
	var searchUrl=enqListUrl+"/search";
	var data = $("#enquirySearchForm").serialize();
	$.ajax({
		type : 'POST',
		url : searchUrl+"?academicYearId="+academicYearId,
		data : data,
		success : searchEnquiriesSuccess
	});
}

function searchEnquiriesSuccess(data){
	$("#enquiry_list_container").html(data);
	$("#searchStr").focus();
	bindEnqListWithDataTable();
}

function search(academicYearId){
	searchType="advance";
	var data = $("#advSearchFrm").serialize();
	$.ajax({
		type : 'POST',
		url : enqListUrl+"/advSearch"+"?academicYearId="+academicYearId,
		data : data,
		success : searchSuccess
	});
}

function searchSuccess(data){

	$("#enquiry_list_container").html(data);
	bindEnqListWithDataTable();
}

function enqExportToexcel(academicYearId){
	var advExportUrl=enqExportUrl+"/advance";
	if(searchType=="quick"){
		$("#enquirySearchForm").attr("action", enqExportUrl+"?academicYearId="+academicYearId);
		$("#enquirySearchForm").attr("method", "POST");
		$('#enquirySearchForm').submit();
		
	}else if(searchType=="advance"){
		$("#advSearchFrm").attr("action", advExportUrl+"?academicYearId="+academicYearId);
		$("#advSearchFrm").attr("method", "POST");
		$('#advSearchFrm').submit();
		
	}
	
}


function toggleAdvanceSearch(){
	 $("#advanceEnqSearch").slideToggle("slow");

}