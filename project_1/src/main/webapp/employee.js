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
				checkRequestsFound(pendingRequestsCounter, acceptedRequestsCounter, pTable, aTable);
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
	
	let welcomeText = document.getElementById("welcomeDisplay").textContent;
	if(welcomeText == "Welcome Employee"){
		if(requestsCounter%2 ==0)
		{
			table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted 
			+ "</th></tr>";
		}                                              
		else{
			table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
			+ "</th></tr>";			
		}	  
	}
	else{
		if(requestsCounter%2 ==0)
		{
			table.innerHTML += "<tr style = \"background-color:#E857EE;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
			+ "</th><th>" + employeeUsername + "</th></tr>";
		}
		else{
			table.innerHTML += "<tr style = \"background-color:#4EE9FA;\"><th>" + requestID + "</th><th>" + requestType + "</th><th>" + dateSubmitted
			+ "</th><th>" + employeeUsername + "</th></tr>";
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
		getRequestFromEmployee(userName);
	}
}
function getRequestsFromEmployee(username){
	const promise = new Promise((resolve, reject) =>{
		xhr = new XMLHttpRequest();
		xhr.open('GET', "http://localhost:8080/project_1/SeeEmployees?username = " + username, true);
		xhr.onload = () => {
		    if (xhr.status === 200 && xhr.readyState == 4) {
		    	resolve(xhr.response)
		    }
		};
		xhr.send(null);
	});
	promise.then((data)=>{
		
	})
}
