function SubmitRequest(){
	let submitButton = document.getElementById('submitButton')
	let fileSelect = document.getElementById('fileSelect');
	
	
	let files = fileSelect.files;
	let formData = new FormData();
	let imageCounter = 0;
	
	for (var i = 0; i < files.length; i++) {
		  var file = files[i];

		  // Check the file type.
		  if (!file.type.match('image.*')) {
		    continue;
		  }

		  // Add the file to the request.
		  formData.append('photo', file, file.name);
		  imageCounter++;
		}
	if(imageCounter == 0){
		alert("You didn't upload an image");
	}
	else{
		submitButton.innerHTML = 'Submitting...';
		MakeAjaxCalls(formData);
	}
	
}
function MakeAjaxCalls(formData){
	let xhr;
	const promise = new Promise((resolve, reject) => {
		  xhr = new XMLHttpRequest();
		  
		  xhr.open('POST', "http://localhost:8080/project_1/SubmitNewRequest");
		  xhr.onload = () => {
		    if (xhr.status === 200 && xhr.readyState == 4) {
		      resolve(xhr.response); // we got data here, so resolve the Promise
		    } 
		  };
		  xhr.send(formData);
	});
	promise.then((data) => {
		  if(data == "Failed Upload"){
			  alert("Failed to to submit request, receipt photo failed to upload")
		  }
		  else{
			  xhr = new XMLHttpRequest();
			  let reimbursementDropDown = document.getElementById("reinbursementType");
		      let reimbursementType = reimbursementDropDown.options[reimbursementDropDown.selectedIndex].text;
			  xhr.open('GET', "http://localhost:8080/project_1/SubmitNewRequest?fileName=" + data + "&reimbursementType=" + reimbursementType, true);
			  xhr.onload = () => {
				    if (xhr.status === 200 && xhr.readyState == 4) {
				      if(xhr.response == "Request has been submitted!"){
				    	  let requestSubmitted = document.getElementById("request_submitted");
				    	  requestSubmitted.style.visibility = "visible"
				    	  var millisecondsToWait = 1000;
				    	  setTimeout(function() {
				    	     window.location.replace("http://localhost:8080/project_1/employee.html");
				    	  }, millisecondsToWait);
				      }
				    } 
				  };
			 xhr.send(null) 
		  }
		});
}
function CheckIfLoggedIn(){
	const promise = new Promise((resolve, reject) =>{
		xhr = new XMLHttpRequest();
		xhr.open('GET', "http://localhost:8080/project_1/CheckedLoggedIn", true);
		xhr.onload = () => {
		    if (xhr.status === 200 && xhr.readyState == 4) {
		    	resolve(xhr.response)
		    }
		};
		xhr.send(null);
	});
	promise.then((data) =>{
		if(data == "Not Logged In"){
			alert("You are not logged in!")
			window.location.replace("http://localhost:8080/project_1");
		}
	});
}
