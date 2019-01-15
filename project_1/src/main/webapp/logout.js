function LogUserOut(){
	xhr = new XMLHttpRequest();
	xhr.open('GET', "http://localhost:8080/project_1/LogOutUser");
	xhr.onload = () => {
		    if (xhr.status === 200 && xhr.readyState == 4) {
		    	alert("You are now logged out");
		    	window.location.replace("http://localhost:8080/project_1/");
		    }
	}
	xhr.send(null);
}