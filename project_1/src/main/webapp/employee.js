function GetRequests(){
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
        	    var response = JSON.parse(xhr.response)
            	if(response.length!= 0){
            		for(i = 0; i < response.length; i++){
                		
                	}
            	}
            	 else{
                 	alert("Something went wrong");
                 }
            }
        }
    xhr.open("GET", "http://localhost:8080/project_1/EmployeeHomepage", true);
    xhr.send();

}