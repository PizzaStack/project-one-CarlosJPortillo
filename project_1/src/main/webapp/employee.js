function GetRequests(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			let pTable = document.getElementById("pendingTable");
			let aTable = document.getElementById("resolvedTable");
			let pendingRequestsCounter = 0;
			let resolvedRequestsCounter = 0;
			if(xhr.response!= "Not Logged In"){
				let response = JSON.parse(xhr.response)
				if(response.length!= 0){
					let requestID;
					let requestType;
					let dateSubmitted;
					aTable.style.visibility = 'visible';
					pTable.style.visibility = 'visible';
					for(i = 0; i < response.length; i++){
						if(response[i].accepted == "pending"){
							pendingRequestsCounter++;
							addRow(response, pendingRequestsCounter, requestID, requestType, dateSubmitted, pTable);

						}
						if(response[i].accepted != "pending"){
							resolvedRequestsCounter++;
							addRow(response, resolvedRequestsCounter, requestID, requestType, dateSubmitted, aTable);
						}
					}
				}
				checkRequestsFound(pendingRequestsCounter, resolvedRequestsCounter, pTable, aTable);
			}
			else if (xhr.response == "Not Logged In"){
				alert("You are not logged in");
				window.location.replace("http://localhost:8080/project_1");
				//checkRequestsFound(pendingRequestsCounter, acceptedRequestsCounter, pTable, aTable);
			}
		}
	}
    xhr.open("GET", "http://localhost:8080/project_1/EmployeeHomepage", true);
    xhr.send();
}
function addRow(response, requestsCounter, requestID, requestType, dateSubmitted, table ){
	requestID = response[i].requestID;
	requestType = response[i].requestType;
	dateSubmitted = response[i].dateSubmitted;
	employeeUsername = response[i].employeeUsername;
	status = response[i].accepted;

	let welcomeText = document.getElementById("welcomeDisplay").textContent;
	if(welcomeText == "Welcome Employee"){
		if(requestsCounter%2 ==0)
		{
			if(table.rows[0].cells.length == 3){
				table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted 
				+ "</th></tr>";
			}
			else{
				table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted 
				+ "</th><th>" + status + "</tr>";
			}

		}                                              
		else{
			if(table.rows[0].cells.length == 3){
				table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted 
				+ "</th></tr>";
			}
			else{
				table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted 
				+ "</th><th>" + status + "</tr>";
			}	
		}	  
	}
	else{
		if(requestsCounter%2 ==0)
		{
			if(table.rows[0].cells.length == 4){
				table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
				+ "</th><th>" + employeeUsername + "</th></tr>";
			}
			else{
				table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
				+ "</th><th>" + employeeUsername + "</th><th>" + response[i].managerUsername + "</th><th>" + response[i].accepted + "</tr>";
			}
		}
		else{
			if(table.rows[0].cells.length == 4){
				table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
				+ "</th><th>" + employeeUsername + "</th></tr>"
			}
			else{
				table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
				+ "</th><th>" + employeeUsername + "</th><th>" + response[i].managerUsername + "</th><th>" + response[i].accepted + "</tr>";
			}

		}
	}
}
function checkRequestsFound(pRequestCounter, aRequestCounter, pTable, aTable){
	if(pRequestCounter == 0){
		pTable.innerHTML = "<H2>No Matches Found</H2>";
	}
	if(aRequestCounter == 0){
		aTable.innerHTML = "<H2>No Matches Found</H2>";
	}
}

function directToSubmissionPage(){
	window.location.replace("http://localhost:8080/project_1/submit_new_request.html");
}
function directToManagerViewEmployeesPage(){
	window.location.replace("http://localhost:8080/project_1/ManagerViewEmployeesPage.html");
}
function getEmployees(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
				let employees = JSON.parse(xhr.response);
				addEmployeeRow(employees);
			}
		}
	xhr.open("POST", "http://localhost:8080/project_1/SeeEmployees");
	xhr.send();
}
function addEmployeeRow(employees){
	let employeesTable = document.getElementById("employeesTable");
	for(i = 0; i < employees.length; i++){
		if(i%2 ==1)
		{
			employeesTable.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + employees[i].employeeID + "</th><th>" + employees[i].username + "</th><th>" + 
			employees.password + "</th></tr>";
		}
		else{
			employeesTable.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + employees[i].employeeID + "</th><th>" + employees[i].username + "</th><th>" + 
			employees[i].password + "</th></tr>";
		}
	}
}
function validateInformation(){
	let userName = document.getElementById("username").value;

	if(userName.length == 0){
		alert("Username field as empty");
	}
	else{
	    document.getElementById("welcomeDisplay").style.display = 'none';
		getRequestsFromEmployee(userName);
	}
}
function getRequestsFromEmployee(username){
	const promise = new Promise((resolve, reject) =>{
		xhr = new XMLHttpRequest();
		xhr.open('GET', "http://localhost:8080/project_1/SeeEmployees?username=" + username, true);
		xhr.onload = () => {
		    if (xhr.status === 200 && xhr.readyState == 4) {
		    	let requests = JSON.parse(xhr.response);
		    	resolve(requests)
		    }
		};
		xhr.send(null);
	});
	promise.then((data)=>{
		showRequests(data);
	})
}
function showRequests(requests){
	let requestsTable = document.getElementById("employeesTable");
	requestsTable.innerHTML = "";
	let tableTitle = document.getElementById("tableTitle");
	tableTitle.innerHTML = "Requests From Employee";
	requestsTable.innerHTML = "<tr class =\"rowHeader\">" +
			"<th><H5>Employee Username</H5></th><th><H5>Request ID</H5>" +
			"</th><th><H5>Status</H5></th><th><H5>Request Type</H5></th><th><H5>Date Submitted</H5></th>" +
			"<th><H5>Manager Whom Resolved</H5></th>" +
			"<th style = \"display:none;\"><H5>File Name</H5></th></tr> ";
	
	for(i = 0; i < requests.length; i++){
		if(requests[i].managerUsername== ""){
			requests[i].managerUsername = "None";
		}
		if(i%2 ==1)
		{
			requestsTable.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requests[i].employeeUsername + "</th><th>" + requests[i].requestID + 
			"</th><th>" + requests[i].accepted + "</th><th>" + requests[i].requestType + "</th><th>" + requests[i].dateSubmitted + "</th><th>" 
			 + requests[i].managerUsername + "</th><th style = \"display:none;\">" + requests[i].fileName + "</th></tr>";
		}
		else{
			requestsTable.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requests[i].employeeUsername + "</th><th>" + requests[i].requestID + 
			"</th><th>" + requests[i].accepted + "</th><th>" + requests[i].requestType + "</th><th>" + requests[i].dateSubmitted + "</th><th>" 
			 + requests[i].managerUsername + "</th><th style = \"display:none;\">" + requests[i].fileName + "</th></tr>";
		}
		
	}
	giveRowsOnClickFunction(requestsTable);
	
}
function giveRowsOnClickFunction(requestTable){
	if (requestTable) {
	  for (var i = 0; i < requestTable.rows.length; i++) {
	    requestTable.rows[i].onclick = function() {
	      displayRequestImage(this);
	    };
	  }
	}
}
function displayRequestImage(tableRow){
	if(tableRow.childNodes[2].innerHTML == "pending"){
		let requestID = tableRow.childNodes[1].innerHTML;
		let requestType = tableRow.childNodes[3].innerHTML;
		let dateSubmitted = tableRow.childNodes[4].innerHTML;
		console.log("Yay");
		const promise = new Promise((resolve, reject) =>{
			let fileName = tableRow.childNodes[6].innerHTML;
			let pathToFile = "http://localhost:8080/project_1/receipt_images/" + fileName;

			let requestsTable = document.getElementById("employeesTable");
			requestsTable.innerHTML = "<tr class =\"rowHeader\">" +
			"<th><H5>Request ID</H5></th>" + "<th><H5>Request Type</H5></th><th><H5>Date Submitted</H5></th>" +
			"<th><H5>Image</H5></th></tr>" ;


			requestsTable.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + 
			requestType + "</th><th>" + dateSubmitted + "</th><th><img width = 400, " +
			"height = 400 src = \""+ pathToFile + "\"></th></tr>";
			resolve();

		});
		promise.then(()=>{
			console.log("It worked!");	
			approve = document.getElementById("approve");
			reject = document.getElementById("reject");
			approve.style.display = 'inline';
			reject.style.display = 'inline';
			approve.addEventListener("click", approveRequest);
			reject.addEventListener("click", rejectRequest);
		});
	}
}
function approveRequest(){
	makeAjaxCallToApproveRejectService('approve');
	let approve = document.getElementById("approve");
	let reject = document.getElementById("reject");
	approve.style.display = "none"
	reject.style.display = "none"
	
	
}
function rejectRequest(){
	makeAjaxCallToApproveRejectService('reject');
	let approve = document.getElementById("approve");
	let reject = document.getElementById("reject");
	approve.style.display = 'none';
	reject.style.display = 'none';
}
function makeAjaxCallToApproveRejectService(action){
	let table = document.getElementById("employeesTable")
	let requestID = table.rows[1].childNodes[0].innerHTML + '';
	let xhr = new XMLHttpRequest();
	let data = new FormData();
	data.append('action', action);
	data.append('requestID', requestID);
	xhr.open("POST", "http://localhost:8080/project_1/ApproveRejectRequest", true);
	xhr.onload = () => {
		if (xhr.status === 200 && xhr.readyState == 4) {
			if(action == "approve"){
				alert("Request has been approved");
			}
			else{
				alert("Request has been rejected");
			}
		}

	};
	xhr.send(data);
}
