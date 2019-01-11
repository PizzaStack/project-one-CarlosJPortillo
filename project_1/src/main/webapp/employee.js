function getRequests(){
	let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            if(xhr.responseText == "success" ){
            	
            }
            else{
            	alert("Something went wrong");
            }
        }
    }
    xhr.open("GET", "", true);
    xhr.send();

}