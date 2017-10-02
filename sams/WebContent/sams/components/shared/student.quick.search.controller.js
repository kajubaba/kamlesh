
samsApp.controller('student.quick.search.controller', function($location){
	
	this.init = function(){
		
		$( "#txtBoxsearchStud" ).autocomplete({
			 source: function( request, response ) {
			 $.ajax({
			 	url: _appContextPath+"/ws/student/quicksearch?searchStr="+$("#txtBoxsearchStud").val(),
			 	dataType: "json",
				success: function( data ) {
					response( $.map( data, function( item ) {
			 		return {
			 			label: item.name +" ["+item.studentId+"]",
			 			value: item.id
			 		}
			 }));
			 }
			 });
			 },
			 minLength: 1,
			 select: function( event, ui ) {
				 $("#txtBoxsearchStud").val(ui.item.value);
				 window.location="#/admissions/studentdetails/"+ui.item.value;
			 }
		});
		
	}
	
	this.initForFeeCollection = function(){
		
		$( "#txtBoxsearchStud" ).autocomplete({
			 source: function( request, response ) {
			 $.ajax({
			 	url: _appContextPath+"/ws/student/quicksearch?searchStr="+$("#txtBoxsearchStud").val(),
			 	dataType: "json",
				success: function( data ) {
					response( $.map( data, function( item ) {
			 		return {
			 			label: item.name +" ["+item.studentId+"]",
			 			value: item.id
			 		}
			 }));
			 }
			 });
			 },
			 minLength: 1,
			 select: function( event, ui ) {
				 $("#txtBoxsearchStud").val(ui.item.value);
				 window.location="#/fee/studentfee/"+ui.item.value+"/null";
			 }
		});
		
	}
	
	this.initAutoComplete = function(){
		
		$( "#autoCompleteTxtBox" ).autocomplete({
			 source: function( request, response ) {
				 console.log("1234...");
				 
				 var currURL = $location.path();
				 var URL = _appContextPath+"/ws/student/quicksearch?searchStr="+$("#autoCompleteTxtBox").val();
				 var module = currURL.split("/")[1];
				 
				 if(module=="enquiry"){
					 URL = _appContextPath+"/ws/enquiry/list/search?searchString="+$("#autoCompleteTxtBox").val();
				 }
				 
				 
			 $.ajax({
			 	url: URL,
			 	dataType: "json",
				success: function( data ) {
					response( $.map( data, function( item ) {
			 		return {
			 			label: item.name,
			 			value: item.id
			 		}
			 }));
			 }
			 });
			 },
			 minLength: 1,
			 select: function( event, ui ) {
				 $("#autoCompleteTxtBox").val("");
				 var currURL = $location.path();
				 
				 var module = currURL.split("/")[1];
				 console.log(module);
				 if(module=="fee"){
					 window.location="#/fee/studentfee/"+ui.item.value+"/null";
				 }else if(module=="admission" || module=="admissions" || module == undefined){
					 window.location="#/admissions/studentdetails/"+ui.item.value;
				 }else if(module=="academics"){
					 window.location="#/academics/student/scorecard/"+ui.item.value;
				 }else if(module=="transportation"){
					 window.location="#/transportation/student/"+ui.item.value;
				 }else if(module=="enquiry"){
					 window.location="#/enquiry/"+ui.item.value;
				 }
				 
			 }
		});
		
	}
	
});






