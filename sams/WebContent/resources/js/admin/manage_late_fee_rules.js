function listLateFeeRules() {
	$.ajax({
		type : 'GET',
		url : admin_late_fee_rule_url + "/list",
		success : listLateFeeRulesSuccess
	});
}

function listLateFeeRulesSuccess(response) {
	$("#adminContainer").html(response);
}

function newLateFeeRule() {
	$.ajax({
		type : 'GET',
		url : admin_late_fee_rule_url + "/new",
		success : newLateFeeRuleSuccess
	});
}

function newLateFeeRuleSuccess(response) {
	$("#adminContainer").html(response);
	$(".numeric").numeric(false, true);
}

function getLateFeeRule(id) {
	$.ajax({
		type : 'GET',
		url : admin_late_fee_rule_url + "/view?lateFeeRuleId="+id,
		success : getLateFeeRuleSuccess
	});
}

function getLateFeeRuleSuccess(response) {
	$("#adminContainer").html(response);
	$(".numeric").numeric(false, true);
}

function AddMoreRule() {
	var rowCount = $("#ruleTable tr").length;

	$('#ruleTable')
			.append(
					'<tr><td >From</td><td><input type="text" id="from'
							+ rowCount
							+ '" name="lateFeeRules['
							+ rowCount
							+ '].from" size="5" class="numeric"/></td><td >To</td><td><input type="text" id="to'
							+ rowCount
							+ '" name="lateFeeRules['
							+ rowCount
							+ '].to" size="5" class="numeric"/></td><td>Days</td><td ><input type="text" id="fineAmount'
							+ rowCount
							+ '" name="lateFeeRules['
							+ rowCount
							+ '].fineAmount" size="5" class="numeric"/></td><td><select id="fineRule'
							+ rowCount
							+ '" name="lateFeeRules['
							+ rowCount
							+ '].fineRule"><option value="rs">Rs</option></select></td><td><select id="finePeriod'
							+ rowCount
							+ '" name="lateFeeRules['
							+ rowCount
							+ '].finePeriod"><option value="per_day">Per Day</option><option value="period">For Entire Period</option></select></td><td><input type="button" value="&nbsp;&nbsp;add&nbsp;&nbsp;" class="button" onclick="AddMoreRule()"/></td></tr>');
	$(".numeric").numeric(false, true);
}

function saveRule() {

	var rowCount = $("#ruleTable tr").length;

	for ( var i = 0; i < rowCount; i++) {
		var from = $("#from" + i + "").val();
		var to = $("#to" + i + "").val();
		var fineAmount = $("#fineAmount" + i + "").val();

		if (i == rowCount - 1) {
			if (from == "" || fineAmount == "") {
				alert("Late Fee Rule is incomplete. Please complete the rule");
				return false;
			}
		} else {
			if (from == "" || to == "" || fineAmount == "") {
				alert("Late Fee Rule is incomplete. Please complete the rule");
				return false;
			}
		}

	}

	var nextFrom = null;
	var prevTo = null;

	for ( var i = 0; i < rowCount; i++) {
		var from = parseInt($("#from" + i + "").val());
		if (i != rowCount - 1) {
			var to = parseInt($("#to" + i + "").val());
			if (to < from) {
				alert("Late Fee Rule is not correct. Please correct the rule");
				return false;
			}
		} else if ($("#to" + i + "").val() != "") {
			var to = parseInt($("#to" + i + "").val());
			if (to < from) {
				alert("Late Fee Rule is not correct. Please correct the rule");
				return false;
			}
		}

		if (i > 0) {
			nextFrom = from;
			prevTo = parseInt($("#to" + (i - 1) + "").val());
			if (nextFrom <= prevTo) {
				alert("Late Fee Rule is not correct. Please correct the rule");
				return false;
			}
		}
	}

	var lateFeeFormData = $("#lateFeeRuleForm").serialize();
	alert(lateFeeFormData);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : admin_late_fee_rule_url + "/add",
		data : lateFeeFormData,
		success : saveRuleSuccess
	});
}

function saveRuleSuccess(response) {
	if("OK"==response.status){
		$("#lateFeeRuleId").val(response.id);
	}else if("DUPLICATE"==response.status){
		alert("Late Fee Rule name already exists. Please choose different name");
	}
}